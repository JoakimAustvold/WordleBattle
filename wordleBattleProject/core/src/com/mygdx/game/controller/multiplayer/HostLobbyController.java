package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.multiplayer.LobbyCode;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.HostLobbyView;

public class HostLobbyController extends Controller {
    private LobbyCode lobbyCode;

    public HostLobbyController() {
        this.state = LobbyInfo.getInstance();
        this.view = new HostLobbyView();
        lobbyCode = new LobbyCode();
    }

    /**
    *  Creates a new lobby and adds the creator as player one
    */
    public void createLobby(String username) {
        // Updates local state
        LobbyInfo lobbyState = (LobbyInfo) state;
        lobbyState.setUsername(username);
        lobbyState.setCode(lobbyCode.getCode());
        // Updates firebase
        SingletonAPI.getInstance().createLobby(lobbyCode);
        SingletonAPI.getInstance().addPlayerOneToLobby(lobbyCode.getCode(), username);
        // With listener
        SingletonAPI.getInstance().createPlayerTwoListener(lobbyCode.getCode());
    }

    /**
     * Removes the lobby from the database
     */
    public void destroyLobby() {
        SingletonAPI.getInstance().removeLobby(lobbyCode.getCode());
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void resetView() {

    }
}
