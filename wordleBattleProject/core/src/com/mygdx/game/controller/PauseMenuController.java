package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.view.PauseMenuView;

public class PauseMenuController extends Controller {

    public PauseMenuController() {
        view = new PauseMenuView();

        PauseMenuView pauseMenuView = ((PauseMenuView) view);

        pauseMenuView.backToMainButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new MainMenuController());
            }
        });

        pauseMenuView.backToGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
                 SingleplayerGameController sgc =
                         (SingleplayerGameController) ControllerManager.getInstance().peek();

                 sgc.resetView();
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
                ControllerManager.getInstance().push(new TutorialController(false));
            }
        });
    }
}
