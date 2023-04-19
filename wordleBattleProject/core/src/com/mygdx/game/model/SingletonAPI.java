package com.mygdx.game.model;

import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.multiplayer.LobbyCode;

import java.util.List;

/**
 * Makes AndroidAPI methods accessible.
 */
public class SingletonAPI implements FirebaseAPI{

    private static final SingletonAPI SingletonAPIInstance = new SingletonAPI();
    private FirebaseAPI firebaseAPI;
    
    public static SingletonAPI getInstance() {
        return SingletonAPIInstance;
    }

    public void setFirebaseAPI(FirebaseAPI firebaseAPI) {
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void getHighscoreList(List<Score> dataholder) {
        if(firebaseAPI != null ) {
            firebaseAPI.getHighscoreList(dataholder);
        }
    }

    @Override
    public void submitHighscore(Score score) {
        if(firebaseAPI != null ) {
            firebaseAPI.submitHighscore(score);
        }
    }

    public void createLobby(String code) {
        if(firebaseAPI != null ) {
            firebaseAPI.createLobby(code);
        }
    }

    @Override
    public void removeLobby(String code) {
        if(firebaseAPI != null ) {
            firebaseAPI.removeLobby(code);
        }
    }
    

    public void addPlayerTwoToLobby(String code, String username) {
        if(firebaseAPI != null ) {
            firebaseAPI.addPlayerTwoToLobby(code, username);
        }
    }

    @Override
    public void addPlayerOneToLobby(String code, String username) {
        if(firebaseAPI != null ) {
            firebaseAPI.addPlayerOneToLobby(code, username);
        }
    }

    @Override
    public void createPlayerTwoListener(String code) {
        if(firebaseAPI != null ) {
            firebaseAPI.createPlayerTwoListener(code);
        }
    }

    @Override
    public void createPlayerOneListener(String code) {
        if(firebaseAPI != null ) {
            firebaseAPI.createPlayerOneListener(code);
        }
    }

    @Override
    public void removePlayerTwoFromLobby(String code) {
        if(firebaseAPI != null ) {
            firebaseAPI.removePlayerTwoFromLobby(code);
        }
    }

    @Override
    public void viewAllLobbies(List<String> dataholder) {
        if (firebaseAPI != null) {
            firebaseAPI.viewAllLobbies(dataholder);
        }
    }


}
