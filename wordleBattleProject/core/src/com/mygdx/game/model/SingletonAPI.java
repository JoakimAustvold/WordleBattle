package com.mygdx.game.model;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.multiplayer.LobbyCode;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Makes AndroidAPI methods accessible.
 */
public class SingletonAPI implements FirebaseAPI{

    private static final SingletonAPI SingletonAPIInstance = new SingletonAPI();
    private FirebaseAPI firebaseAPI;
   // static Semaphore semaphore = new Semaphore(10);

    //TODO: Remove these fields???
    /*
    private String code;
    private String playerOne;
    private String playerTwo;

    public String getCode() {
        return code;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }
     */

    public static SingletonAPI getInstance() {
        return SingletonAPIInstance;
    }

    public void setFirebaseAPI(FirebaseAPI firebaseAPI) {
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void getHighscoreList(List<Score> dataholder) {

    }

    @Override
    public void submitHighscore(Score score) {

    }

    public void createLobby(LobbyCode lobbyCode) {
        if(firebaseAPI != null ) {
//            code = String.valueOf(lobbyCode.getCode());
            firebaseAPI.createLobby(lobbyCode);
        }
    }

    @Override
    public void removeLobby(String code) {
        if(firebaseAPI != null ) {
            firebaseAPI.removeLobby(code);
        }
    }

    @Override
    public void addUserToLobby(LobbyCode lobbyCode, String username) {
        if(firebaseAPI != null ) {
//            code = String.valueOf(lobbyCode.getCode());
//            playerOne = username;
            firebaseAPI.addUserToLobby(lobbyCode, username);
        }
    }

    @Override
    public void addPlayerTwoToLobby(String code, String username) {
        if(firebaseAPI != null ) {
//            playerTwo = username;
            firebaseAPI.addPlayerTwoToLobby(code, username);
        }
    }

    @Override
    public void addPlayerTwoToLobby(String code, String username, Controller controller) {
        if(firebaseAPI != null ) {
//            playerTwo = username;
            firebaseAPI.addPlayerTwoToLobby(code, username, controller);
        }
    }

    @Override
    public void addPlayerOneToLobby(String code, String username) {
        if(firebaseAPI != null ) {
//            this.code = code;
//            playerOne = username;
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
       // semaphore.tryAcquire();
        if (firebaseAPI != null) {
            firebaseAPI.viewAllLobbies(dataholder);
        }
        //semaphore.release();
    }


}
