package com.mygdx.game.model;

import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.multiplayer.LobbyCode;

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
    public void createLobby(LobbyCode lobbyCode) {

    }

    @Override
    public void removeLobby(LobbyCode lobbyCode) {

    }

    @Override
    public void addUserToLobby(LobbyCode lobbyCode, String username) {
        
    }
}
