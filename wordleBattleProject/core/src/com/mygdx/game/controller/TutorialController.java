package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.model.states.TutorialState;

import com.mygdx.game.view.TutorialView;

public class TutorialController extends Controller {

    public TutorialController(final boolean checkPrefs) {
        state = new TutorialState();
        view = new TutorialView((TutorialState) state);

        //TutorialView pauseMenuView = ((TutorialView) view);

        view.backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (checkPrefs){
                    ControllerManager.getInstance().set(new SingleplayerGameController());
                } else{
                    ControllerManager.getInstance().pop();
                }
            }
        });




        Preferences prefs = Gdx.app.getPreferences("wordleBattle");
        boolean hasPlayed = prefs.getBoolean("hasPlayed");

        System.out.println("HASPLAYED: " + hasPlayed);

        // Skip if setup if already played
        if(hasPlayed && checkPrefs){
            ControllerManager.getInstance().push(new SingleplayerGameController());
        }

        ((TutorialState) state).setHasPlayed();
    }
}
