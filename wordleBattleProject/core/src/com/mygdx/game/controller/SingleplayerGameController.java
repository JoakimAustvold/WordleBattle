package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.model.keyboard.KeyboardInput;
import com.mygdx.game.model.states.PlayState;
import com.mygdx.game.view.SingleplayerGameView;

public class SingleplayerGameController extends Controller {

    private final KeyboardInput keyboardInput;
    private final TextButton[][] buttons;

    public SingleplayerGameController() {
        this.state = new PlayState();
        this.view = new SingleplayerGameView();

        keyboardInput = ((SingleplayerGameView) view).keyboardInput;
        buttons = ((SingleplayerGameView) view).buttons;

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

    private class KeyboardInputListener extends InputListener {

        private final String buttonValue;

        public KeyboardInputListener(String buttonValue) {
            this.buttonValue = buttonValue;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            switch (buttonValue) {
                case "Enter":
                    keyboardInput.appendText("\n");
                    break;
                case "<--":
                    keyboardInput.deleteLastChar();
                    break;
                default:
                    keyboardInput.appendText(buttonValue);
                    break;
            }
            return true;
        }
    }
}
