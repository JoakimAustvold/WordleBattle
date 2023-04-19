package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.view.multiplayer.MultiplayerMenuView;

public class MultiplayerMenuController extends Controller {

    public MultiplayerMenuController() {
        this.state = null;
        this.view = new MultiplayerMenuView();
        
        addBackButtonListener();

        MultiplayerMenuView multiplayerMenuView = (MultiplayerMenuView) view;
        multiplayerMenuView.hostGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new HostGameController());
            }
        });

        multiplayerMenuView.joinGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new JoinGameController());
            }
        });
    }
    

    @Override
    public void resetView() {

    }
}
