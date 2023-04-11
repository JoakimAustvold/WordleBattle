package com.mygdx.game.model.states;

import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.multiplayer.LobbyCode;

public class HostLobbyState extends State {

    private LobbyCode lobbyCode;
    private String username;

    public HostLobbyState() {
        lobbyCode = new LobbyCode();
        SingletonAPI.getInstance().createLobby(lobbyCode);
    }

    public void setUsername(String username) {
        this.username = username;
        SingletonAPI.getInstance().addUserToLobby(lobbyCode, username);
    }


    public int getCode() {
        return lobbyCode.getCode();
    }


    @Override
    public void update(float deltaTime) {
        
    }
}
