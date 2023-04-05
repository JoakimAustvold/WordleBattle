package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.model.input.KeyboardInput;
import com.mygdx.game.model.states.SingleplayerGameState;
import com.mygdx.game.view.SingleplayerGameView;

public class SingleplayerGameController extends Controller {

    private KeyboardInput keyboardInput;

    public SingleplayerGameController() {
        this.state = new SingleplayerGameState();
        this.view = new SingleplayerGameView();

        SingleplayerGameView singleplayerView = (SingleplayerGameView) view;

        if(this.state instanceof SingleplayerGameState){
            keyboardInput = ((SingleplayerGameState) this.state).getKeyboardInput();
        }

        final TextButton[][] buttons = singleplayerView.getButtons();

        for (TextButton[] rowButtons : buttons) {
            for (TextButton button : rowButtons) {
                button.addListener(new KeyboardInputListener(button.getLabel().getText().toString()));
            }
        }
    }

    @Override
    public void handleInput() {
        // This method is empty because input is handled by the KeyboardInputListener class
    }

    /**
     * Handles input events for the keyboard-view.
     */
    private class KeyboardInputListener extends InputListener {
        private final String buttonValue;
        public KeyboardInputListener(String buttonValue) {
            this.buttonValue = buttonValue;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            switch (buttonValue) {
                case "Enter":
                    // We currently have no logic for checking/submitting word, so a linebreak is used
                    keyboardInput.appendChar("\n");
                    break;
                case "<--":
                    keyboardInput.deleteLastChar();
                    break;
                default:
                    keyboardInput.appendChar(buttonValue);
                    break;
            }
            return true;
        }
    }
}
