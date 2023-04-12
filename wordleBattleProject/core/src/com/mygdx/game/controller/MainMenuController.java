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
    public final static int SINGLEPLAYER = 2;

    public final static int MULTIPLAYER = 3;

    public final static int TUTORIAL = 4;

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

            case SINGLEPLAYER:
                ControllerManager.getInstance().push(new SingleplayerGameController(this));
                break;

            //TODO implement call to Multiplayer Controller here
            case MULTIPLAYER:
                break;

            case TUTORIAL:
                ControllerManager.getInstance().push(new TutorialController(this));
                break;
        }
    }

    @Override
    public void handleInput() {

    }
}
