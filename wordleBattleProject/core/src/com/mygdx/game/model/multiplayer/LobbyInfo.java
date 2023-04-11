package com.mygdx.game.model.multiplayer;

import com.mygdx.game.model.states.State;

public class LobbyInfo extends State {

    private static final LobbyInfo lobbyInfoInstance = new LobbyInfo();

    public static LobbyInfo getInstance() {
        return lobbyInfoInstance;
    }

    private String playerOne;
    private String playerTwo;
    private int code;

    private LobbyInfo() {
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }


    @Override
    public void update(float deltaTime) {

    }
}
