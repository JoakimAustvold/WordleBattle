package com.mygdx.game.controller;

import com.mygdx.game.model.states.TutorialState;
import com.mygdx.game.view.TutorialScreenView;

public class TutorialController extends Controller {

    public TutorialController() {
        view = new TutorialScreenView(new TutorialState());
        state = new TutorialState();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void resetView() {

    }
}
