package com.mygdx.game.model.states;

public class SettingsState extends State {
    private static SettingsState instance = new SettingsState();
    private int volume;
    private boolean isDarkMode;

    private SettingsState() {

    }

    public static SettingsState getInstance() {
        return instance;
    }


    @Override
    public void update(float dt) {

    }

}
