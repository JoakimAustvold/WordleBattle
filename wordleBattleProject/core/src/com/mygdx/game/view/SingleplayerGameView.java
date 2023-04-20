package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.states.State;

public class SingleplayerGameView extends GameView {


    public SingleplayerGameView() {
        super();
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(keyboardStage);
        super.setup();
    }





}
