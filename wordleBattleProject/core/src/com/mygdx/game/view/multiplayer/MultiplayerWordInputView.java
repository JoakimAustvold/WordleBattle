package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.states.SingleplayerGameState;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.SingleplayerGameView;

public class MultiplayerWordInputView extends SingleplayerGameView {

    public MultiplayerWordInputView() {
        Gdx.input.setInputProcessor(stage);
        setupKeyboard();
    }
    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        //TODO: render word
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void setup() {

    }

    @Override
    public void dispose() {

    }
}
