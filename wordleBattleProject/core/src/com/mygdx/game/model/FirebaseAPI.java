package com.mygdx.game.model;

import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.states.multiplayer.CurrentPlayer;
import com.mygdx.game.model.states.multiplayer.LobbyStatus;

import java.util.List;

/**
 * FirebaseAPI is an interface which defines the methods used to read and write to the database.
 * The methods are implemented in the class AndroidAPI.
 */
public interface FirebaseAPI {

    void getHighscoreList(List<Score> dataholder);
    void submitHighscore(Score score);
    void createLobby(String code);
    void removeLobby(String code);
    void addPlayerTwoToLobby(String code, String username);
    void addPlayerOneToLobby(String code, String username);
    void createPlayerTwoListener(String code);
    void createPlayerOneListener(String code);
    void removePlayerTwoFromLobby(String code);
    void viewAllLobbies(List<String> dataholder);
    void submitWord(String code, String player, String word);
    void getWordSubmitted (String code);
    void submitMultiplayerScore(String code, CurrentPlayer player, Integer score);
    void getMultiplayerScore(String code);
    void setOnlineLobbyStatus(String code, LobbyStatus lobbyStatus);
    void getOnlineLobbyStatus(String code);

}
