package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.HostGameView;

public class HostGameController extends Controller {

    public HostGameController() {
        this.state = LobbyInfo.getInstance();
        this.view = new HostGameView();
        addBackButtonListener();

        final HostGameView hostGameView = (HostGameView) view;

        hostGameView.createLobbyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            System.out.println(hostGameView.usernameTextField.getText());
            HostLobbyController hostLobbyController = new HostLobbyController();
            hostLobbyController.createLobby(hostGameView.usernameTextField.getText());
            ControllerManager.getInstance().push(hostLobbyController);
            // ControllerManager.getInstance().push(new HostLobbyController(usernameTextField.getText()));
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
