package com.mygdx.game.model.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


public class TutorialState extends State{

    @Override
    public void update(float dt) {

    }

    public void setHasPlayed() {
        Preferences prefs = Gdx.app.getPreferences("wordleBattle");
        prefs.putBoolean("hasPlayed", true);
        prefs.flush();
    }
}
