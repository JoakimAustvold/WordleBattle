package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.multiplayer.MultiplayerMenuController;
import com.mygdx.game.view.MainMenuView;
import com.mygdx.game.view.View;

/**
 * Sets the corresponding view for the main menu
 */
public class MainMenuController extends Controller {
    
     public MainMenuController() {
        // Set corresponding state and view here!!!
        this.state = null;
        this.view = new MainMenuView();

        MainMenuView mainMenuView = (MainMenuView) view;

        mainMenuView.singleplayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new SingleplayerGameController());
            }
        });

        mainMenuView.multiplayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new MultiplayerMenuController());
            }
        });

        mainMenuView.settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new SettingsController());
            }
        });
        mainMenuView.highscoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new HighscoreController());
            }
        });


    }
        
    @Override
    public void handleInput() {

    }

    @Override
    public void resetView() {

    }
}
