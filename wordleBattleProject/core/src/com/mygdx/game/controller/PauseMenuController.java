package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.model.states.PauseState;
import com.mygdx.game.view.PauseMenuView;

public class PauseMenuController extends Controller {

    public PauseMenuController() {
        view = new PauseMenuView();
        state = new PauseState();

        PauseMenuView pauseMenuView = ((PauseMenuView) view);

        pauseMenuView.backToMainButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().pop();
            }
        });

        pauseMenuView.backToGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
            }
        });

        pauseMenuView.newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().push(new SingleplayerGameController());

            }
        });

        pauseMenuView.tutorialButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                //TODO add call to controllermanager to add tutorial to stack

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
