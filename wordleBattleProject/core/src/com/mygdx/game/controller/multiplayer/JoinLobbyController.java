package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfoState;
import com.mygdx.game.model.states.multiplayer.LobbyStatus;
import com.mygdx.game.view.multiplayer.JoinLobbyView;

public class JoinLobbyController extends Controller {

    public JoinLobbyController() {
        this.state = LobbyInfoState.getInstance();
        this.view = new JoinLobbyView();

        final LobbyInfoState lobbyState = (LobbyInfoState) state;
        //lobbyState.setLobbyStatus(LobbyStatus.UNKNOWN);

        JoinLobbyView lobbyView = (JoinLobbyView) view;

        SingletonAPI.getInstance().getOnlineLobbyStatus(lobbyState.getCode());

        /**
        * Removes the second player from the lobby and goes back to the previous view
        */
        lobbyView.backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                SingletonAPI.getInstance().removePlayerTwoFromLobby(lobbyState.getCode());
                SingletonAPI.getInstance().setOnlineLobbyStatus(lobbyState.getCode(), LobbyStatus.UNKNOWN);
                ControllerManager.getInstance().pop();
            }
        });
    }
}
