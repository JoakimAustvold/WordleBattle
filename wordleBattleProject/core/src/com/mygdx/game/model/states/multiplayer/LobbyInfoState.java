package com.mygdx.game.model.states.multiplayer;

import com.mygdx.game.model.states.State;

/**
 * A state used for lobbies.
 */
public class LobbyInfoState extends State {

    private static final LobbyInfoState lobbyInfoInstance = new LobbyInfoState();

    public static LobbyInfoState getInstance() {
        return lobbyInfoInstance;
    }

    private String playerOne;
    private String playerTwo;
    private String code;

    private LobbyStatus lobbyStatus;

    private LobbyInfoState() {
        lobbyStatus = LobbyStatus.UNKNOWN;
    }


    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    /// Methods from other states
     public void setUsername(String username) {
        this.playerOne = username;
        //SingletonAPI.getInstance().addPlayerOneToLobby(this.code, username);
    }

    public void setPlayerTwo(String code, String username) {
       // SingletonAPI.getInstance().addPlayerTwoToLobby(code, username);
        this.code = code;
        this.playerTwo = username;
    }

    public void setLobbyStatus(LobbyStatus lobbyStatus) {
        this.lobbyStatus = lobbyStatus;
    }

    public LobbyStatus getLobbyStatus() {
        return lobbyStatus;
    }
}