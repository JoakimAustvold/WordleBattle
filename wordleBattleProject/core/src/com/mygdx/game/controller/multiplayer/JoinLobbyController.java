package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.LobbyView;


public class JoinLobbyController extends Controller {

    public JoinLobbyController() {
        this.state = LobbyInfo.getInstance();
        this.view = new LobbyView();
    }

    /**
     * Adds the second player to the lobby
     */
    public void addPlayerTwoToLobby(String code, String username) {
        //TODO: Does the lobby exists?
        //TODO: Are there available space in the lobby?
        //TODO: Updates the state if Singleton runs successfully
        LobbyInfo lobbystate = (LobbyInfo) state;
        lobbystate.setPlayerTwo(code, username);
        // Updates firebase
        SingletonAPI.getInstance().addPlayerTwoToLobby(code, username);
        // Adds player one locally from firebase
        SingletonAPI.getInstance().createPlayerOneListener(code);
    }

    /**
     * Removes the second player from the lobby.
     */
    public void removePlayerTwoFromLobby() {
        LobbyInfo lobbystate = (LobbyInfo) state;
        SingletonAPI.getInstance().removePlayerTwoFromLobby(lobbystate.getCode());
    }

    @Override
    public void resetView() {

    }
}
