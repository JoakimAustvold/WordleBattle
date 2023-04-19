package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.states.State;
import com.mygdx.game.model.states.multiplayer.MultiplayerWordInputState;
import com.mygdx.game.view.SingleplayerGameView;

public class MultiplayerWordInputView extends SingleplayerGameView {

    public MultiplayerWordInputView() {
        Gdx.input.setInputProcessor(stage);
        setupKeyboard();
    }
    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        //TODO: render word
        MultiplayerWordInputState currentState = (MultiplayerWordInputState) state;
        String currentText = currentState.getKeyboardInput().getCurrentText();
        for (int i = 0; i < currentText.length(); i++) {
            spriteBatch.draw(letterMap.getTexture(currentText.charAt(i)+""),
                    (Gdx.graphics.getWidth() / (WORD_POS_X_DIVISOR + 1.5f)) + (i * 150), (Gdx.graphics.getHeight() - 110.0f - WORD_DELTA_Y) - WORD_DELTA_Y);
        }
        stage.draw();
    }

    @Override
    public void setup() {

    }

    @Override
    public void dispose() {

    }
}
