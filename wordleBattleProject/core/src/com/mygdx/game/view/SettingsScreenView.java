package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;


public class SettingsScreenView extends View {
    
    public SettingsScreenView() {
        super();
        setup();
    }

    @Override
    public void setup() {
      Gdx.input.setInputProcessor(stage);
      createBackButton();
    }
}
