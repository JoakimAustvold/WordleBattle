package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.multiplayer.MultiplayerMenuController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.model.states.TutorialState;
import com.mygdx.game.view.MainMenuView;

/**
 * Sets the corresponding view for the main menu and sets up listeners for the buttons
 */
public class MainMenuController extends Controller {

    public MainMenuController() {
        this.state = null;
        this.view = new MainMenuView(new TutorialState());
        MainMenuView mainMenuView = (MainMenuView) view;

        mainMenuView.singleplayerButton.addListener(new ChangeListener() {
            Preferences prefs = Gdx.app.getPreferences("wordleBattle");
            final boolean hasPlayed = prefs.getBoolean("hasPlayed");

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new SingleplayerGameController());
                if (!hasPlayed) {
                    ControllerManager.getInstance().push(new TutorialController(true));
                }
            }
        });

        mainMenuView.multiplayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new MultiplayerMenuController());
            }
        });

        mainMenuView.tutorialButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new TutorialController(false));
            }
        });

        mainMenuView.highscoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new HighscoreController());
            }
        });
    }
}
