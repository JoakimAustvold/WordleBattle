package com.mygdx.game.controller;

import static com.mygdx.game.WordleBattleGame.WORD_LENGTH;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.input.KeyboardInput;
import com.mygdx.game.model.input.WordStatus;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.view.GameView;

import java.util.Collection;

public class GameController extends Controller{

    protected KeyboardInput keyboardInput;
    protected GameState gameState;
    protected GameView gameView;

    public GameController(GameView gameView) {
        this(gameView, new GameState());
    }

    public GameController(GameView gameView, GameState gameState) {
        this.state = gameState;
        this.view = gameView;

        if (!(this.state instanceof GameState)) {
            throw new StateException("Please provide a SingleplayerGameState to this controller");
        }
        this.gameState = ((GameState) this.state);
        this.gameView = ((GameView) this.view);
    }




    protected void addPauseButtonListener() {
        gameView.pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new PauseMenuController());
            }
        });
    }

    protected void setupKeyboard() {
        keyboardInput = gameState.getKeyboardInput();
        TextButton[][] buttons = gameView.getButtons();
        for (TextButton[] rowButtons : buttons) {
            for (TextButton button : rowButtons) {
                button.addListener(new KeyboardInputListener(
                        button
                ));
            }
        }
    }


    protected void setupView() {
       // this.view = new GameView();

        setupKeyboard();
    }

    private void disableButtons(Collection<Character> disabledLetters) {
      //  SingleplayerGameView singleplayerView = (SingleplayerGameView) view;
        gameView.updateKeyboardStyle(disabledLetters);
    }


    /**
     * Handles input events for the keyboard-view.
     */
    protected class KeyboardInputListener extends InputListener {
        private final String buttonValue;
        private final TextButton textButton;

        public KeyboardInputListener(TextButton button) {
            this.textButton = button;
            this.buttonValue = button.getLabel().getText().toString();
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (this.textButton.isDisabled())
                return false;

            switch (buttonValue) {
                case "Enter":
                    handleEnter();
                    break;
                case "<--":
                    keyboardInput.deleteLastChar();
                    break;
                default:
                    if (keyboardInput.getCurrentText().length() < WORD_LENGTH)
                        keyboardInput.appendChar(buttonValue);
                    break;
            }
            return true;
        }

        /**
         * Handle input word in state. Clear input and disable incorrectly guessed letters if input
         * word was valid.
         */
        private void handleEnter() {
            WordStatus wordStatus = gameState.getWordInputHandler().handleInput(keyboardInput);
            if (!wordStatus.equals(WordStatus.INVALID)) {
                keyboardInput.clear();
                disableButtons(gameState.getDisabledChars());
                // Pass control back to state
                gameState.handleSubmit(wordStatus);
            }
        }
    }
}
