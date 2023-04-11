package com.mygdx.game.model;

import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.multiplayer.LobbyCode;

import java.util.List;

/**
 * FirbaseAPI is an interface which defines the methods used to read and write to the database
 * The methods are implemented in the class AndroidAPI
 */
public interface FirebaseAPI {

    // public FirebaseAPI getInstance();
    public void getHighscoreList(List<Score> dataholder);
    public void submitHighscore(Score score);
    public void createLobby(LobbyCode lobbyCode);
    public void removeLobby(LobbyCode lobbyCode);
    public void addUserToLobby(LobbyCode lobbyCode, String username);
}
