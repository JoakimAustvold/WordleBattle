package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SettingsState extends State{
    private static SettingsState instance = new SettingsState(GameStateManager.getInstance());

    private SettingsState(GameStateManager gsm) {
        super(gsm);
    }

    public static SettingsState getInstance() {
        return instance;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
