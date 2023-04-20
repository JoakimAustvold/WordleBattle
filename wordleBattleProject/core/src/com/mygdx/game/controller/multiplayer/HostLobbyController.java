package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfoState;
import com.mygdx.game.view.multiplayer.HostLobbyView;

public class HostLobbyController extends Controller {

    public HostLobbyController() {
        this.state = LobbyInfoState.getInstance();
        this.view = new HostLobbyView();

        HostLobbyView hostLobbyView = (HostLobbyView) view;
        final LobbyInfoState lobbyInfo = (LobbyInfoState) state;

        hostLobbyView.startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO: Add a multiplayer game screen
                if (lobbyInfo.getPlayerTwo() != null) {
                    System.out.println("The multiplayer game would be starting now");
                    ControllerManager.getInstance().push(new MultiplayerWordInputController());
                } else {
                    System.out.println("Missing player two!!!!!");
                }
            }
        });

       hostLobbyView.backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Removes lobby from database as it is no longer in use
                SingletonAPI.getInstance().removeLobby(lobbyInfo.getCode());
                ControllerManager.getInstance().pop();
            }
        });

    }
}
