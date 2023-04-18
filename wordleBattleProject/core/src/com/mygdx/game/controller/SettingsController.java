package com.mygdx.game.controller;

import com.mygdx.game.model.states.SettingsState;
import com.mygdx.game.view.SettingsScreenView;

public class SettingsController extends Controller {

    public SettingsController() {
        view = new SettingsScreenView();
        state = new SettingsState();

        addBackButtonListener();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void resetView() {

    }
}
