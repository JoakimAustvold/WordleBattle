package com.mygdx.game.controller;

import com.mygdx.game.model.states.MenuState;
import com.mygdx.game.view.MainMenuView;

/**
 * @author Marcus Birkeland
 * @version 13.03.2023
 * Empty example implementation of a Controller.
 */
public class MainMenuController extends Controller {


    public final static int MENU = 0;
    public final static int SETTINGS = 1;
    public final static int GAMESCREEN = 2;
    public final static int ENDGAME = 3;

    private final SettingsController settingsController;

    public MainMenuController() {
        settingsController = new SettingsController(this);

        view = new MainMenuView(this);
        state = new MenuState();
    }

    public void changeScreen(int screen) {
        switch (screen) {
            case MENU:
                ControllerManager.getInstance().push(this);
                break;
            case SETTINGS:
                ControllerManager.getInstance().push(settingsController);
                break;
            case GAMESCREEN:
                ControllerManager.getInstance().push(new SingleplayerGameController(this));

             break;

            /**
            case ENDGAME:
                if(endScreen == null) endScreen = new EndScreen(this);  // added (this)
                this.setScreen(endScreen);
                break;
             */
        }
    }

    @Override
    public void handleInput() {

    }
}
