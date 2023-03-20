package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameSetupState extends State {
    private static GameSetupState instance = new GameSetupState(GameStateManager.getInstance());

    private GameSetupState(GameStateManager gsm) {
        super(gsm);
    }

    public static GameSetupState getInstance() {
        return instance;
    }

    @Override
    public void update(float dt) {

    }

}
