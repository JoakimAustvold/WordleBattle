package com.mygdx.game.model.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


public class TutorialState extends State{
    private boolean hasPlayed = false;
    Preferences prefs = Gdx.app.getPreferences("WordleBattleGame Preferences");


    public TutorialState() {
        prefs.putBoolean("hasPlayed", hasPlayed);
        prefs.flush();
    }

    @Override
    public void update(float dt) {

    }

    public boolean getHasPlayed() {
        return hasPlayed;
    }

    public void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }
}
