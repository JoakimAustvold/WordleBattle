package com.mygdx.game.model;

import com.mygdx.game.model.multiplayer.LobbyCode;

public class SingletonAPI {

    private static final SingletonAPI SingletonAPIInstance = new SingletonAPI();
    private FirebaseAPI firebaseAPI;

    public static SingletonAPI getInstance() {
        return SingletonAPIInstance;
    }

    public void setFirebaseAPI(FirebaseAPI firebaseAPI) {
        this.firebaseAPI = firebaseAPI;
    }

    public void createLobby(LobbyCode lobbyCode) {
        if(firebaseAPI != null ) {
            firebaseAPI.createLobby(lobbyCode);
        }
    }

    public void addUserToLobby(LobbyCode lobbyCode, String username) {
        if(firebaseAPI != null ) {
            firebaseAPI.addUserToLobby(lobbyCode, username);
        }
    }


}
