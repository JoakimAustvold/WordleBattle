package com.mygdx.game.model;

import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.states.multiplayer.CurrentPlayer;
import com.mygdx.game.model.states.multiplayer.LobbyStatus;
import java.util.List;

/**
 * Can be used to test the functionality
 */
public class CoreAPI implements FirebaseAPI {

    @Override
    public void getHighscoreList(List<Score> dataholder) {

    }

    @Override
    public void submitHighscore(Score score) {

    }

    @Override
    public void createLobby(String code) {

    }

    @Override
    public void removeLobby(String code) {

    }
    

    @Override
    public void addPlayerTwoToLobby(String code, String username) {

    }

    @Override
    public void addPlayerOneToLobby(String code, String username) {

    }

    @Override
    public void createPlayerTwoListener(String code) {

    }

    @Override
    public void createPlayerOneListener(String code) {

    }

    @Override
    public void removePlayerTwoFromLobby(String code) {

    }

    @Override
    public void viewAllLobbies(List<String> dataholder) {

    }

    @Override
    public void submitWord(String code, String player, String word) {

    }

    @Override
    public void getWordSubmitted(String code) {

    }

    @Override
    public void submitMultiplayerScore(String code, CurrentPlayer player, Integer score) {

    }

    @Override
    public void getMultiplayerScore(String code) {

    }


    @Override
    public void setOnlineLobbyStatus(String code, LobbyStatus lobbyStatus) {

    }

    @Override
    public void getOnlineLobbyStatus(String code) {

    }


}
