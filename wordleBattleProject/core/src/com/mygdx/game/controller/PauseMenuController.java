package com.mygdx.game.controller;

import com.mygdx.game.model.states.PauseState;
import com.mygdx.game.view.PauseMenuView;

public class PauseMenuController extends Controller {

    public PauseMenuController() {
        view = new PauseMenuView();
        state = new PauseState();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void resetView() {

    }
}
