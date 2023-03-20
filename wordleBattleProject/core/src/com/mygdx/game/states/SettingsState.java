package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SettingsState extends State {
    private static SettingsState instance = new SettingsState(GameStateManager.getInstance());
    private int volume;
    private boolean isDarkMode;

    private SettingsState(GameStateManager gsm) {
        super(gsm);
    }

    public static SettingsState getInstance() {
        return instance;
    }


    @Override
    public void update(float dt) {

    }

}
