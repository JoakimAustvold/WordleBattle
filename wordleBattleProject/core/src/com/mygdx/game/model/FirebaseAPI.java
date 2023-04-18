package com.mygdx.game.model;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.multiplayer.LobbyCode;

import java.util.List;

/**
 * FirebaseAPI is an interface which defines the methods used to read and write to the database.
 * The methods are implemented in the class AndroidAPI.
 */
public interface FirebaseAPI {

    public void getHighscoreList(List<Score> dataholder);
    public void submitHighscore(Score score);
    public void createLobby(LobbyCode lobbyCode);
    public void removeLobby(String code);
    public void addUserToLobby(LobbyCode lobbyCode, String username);
    public void addPlayerTwoToLobby(String code, String username);
    public void addPlayerTwoToLobby(String code, String username, Controller controller);
    public void addPlayerOneToLobby(String code, String username);
    public void createPlayerTwoListener(String code);
    public void createPlayerOneListener(String code);
    public void removePlayerTwoFromLobby(String code);
    public void viewAllLobbies(List<String> dataholder);
}
