package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.view.multiplayer.MultiplayerGameView;

public class MultiplayerGameController extends GameController {

    public MultiplayerGameController(String word) {
        super(new MultiplayerGameView(), new GameState(word));
        setupView();
    }

    @Override
    protected void setupView() {
        super.setupView();

        final MultiplayerGameView multiplayerGameView = (MultiplayerGameView) view;

        multiplayerGameView.rematchButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().pop();
            }
        });

        multiplayerGameView.leaveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().pop();
            }
        });
    }

    @Override
    public void resetView() {
        
    }
}
