package com.mygdx.game.controller;

import static com.mygdx.game.WordleBattleGame.WORD_LENGTH;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.input.KeyboardInput;
import com.mygdx.game.model.input.WordStatus;
import com.mygdx.game.model.states.SingleplayerGameState;
import com.mygdx.game.view.SingleplayerGameView;

import java.util.Collection;

public class SingleplayerGameController extends Controller {

    private KeyboardInput keyboardInput;
    private final SingleplayerGameState gameState;

    public SingleplayerGameController() {
        this.state = new SingleplayerGameState();
        this.view = new SingleplayerGameView();

        if (!(this.state instanceof SingleplayerGameState)) {
            throw new StateException("Please provide a SingleplayerGameState to this controller");
        }
        this.gameState = ((SingleplayerGameState) this.state);

        setupView();

    }

    /**
     * Needs to reset view in order to be responsive after a stack-pop.
     */
    public void resetView() {
        setupView();
    }

    private void setupView() {
        this.view = new SingleplayerGameView();
        final SingleplayerGameView singleplayerView = (SingleplayerGameView) view;

        keyboardInput = gameState.getKeyboardInput();
        TextButton[][] buttons = singleplayerView.getButtons();
        for (TextButton[] rowButtons : buttons) {
            for (TextButton button : rowButtons) {
                button.addListener(new KeyboardInputListener(
                        button
                ));
            }
        }

        singleplayerView.pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new PauseMenuController());
            }
        });

        // add listeners to the buttons
        singleplayerView.addHighscore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                // update username
                gameState.getScore().setUsername(singleplayerView.usernameTextField.getText());
                // register the highscore in the firebase database
                SingletonAPI.getInstance().submitHighscore(gameState.getScore());

                // new game
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().push(new SingleplayerGameController());
            }
        });

        singleplayerView.newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().push(new SingleplayerGameController());
            }
        });

    }

    private void disableButtons(Collection<Character> disabledLetters) {
        SingleplayerGameView singleplayerView = (SingleplayerGameView) view;
        singleplayerView.updateKeyboardStyle(disabledLetters);
    }


    /**
     * Handles input events for the keyboard-view.
     */
    private class KeyboardInputListener extends InputListener {
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
