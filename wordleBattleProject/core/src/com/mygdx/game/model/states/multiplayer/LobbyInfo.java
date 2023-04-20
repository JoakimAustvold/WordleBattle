package com.mygdx.game.model.states.multiplayer;

import com.mygdx.game.model.states.State;

import java.util.ArrayList;

/**
 * A state used for lobbies.
 */
public class LobbyInfo extends State {

    private static final LobbyInfo lobbyInfoInstance = new LobbyInfo();

    public static LobbyInfo getInstance() {
        return lobbyInfoInstance;
    }

    private CurrentPlayer currentPlayer;
    private String playerOne;
    private String playerTwo;
    private ArrayList<String> playerOneWordlist;
    private ArrayList<String> playerTwoWordlist;
    private String code;

    private LobbyStatus lobbyStatus;

    private LobbyInfo() {
        lobbyStatus = LobbyStatus.UNKNOWN;
        playerOneWordlist = new ArrayList<String>();
        playerTwoWordlist = new ArrayList<String>();
        System.out.println("****" + playerTwoWordlist.size());

    }
    public void setCurrentPlayer(CurrentPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public CurrentPlayer getCurrentPlayer() {
        return currentPlayer;
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

    public void setPlayerOneWordlist(ArrayList<String> playerOneWordlist) {
        this.playerOneWordlist = playerOneWordlist;
    }

    public void setPlayerTwoWordlist(ArrayList<String> playerTwoWordlist) {
        this.playerTwoWordlist = playerTwoWordlist;
    }

    public ArrayList<String> getPlayerOneWordlist() {
        return new ArrayList<String>(playerOneWordlist);
    }

    public ArrayList<String> getPlayerTwoWordlist() {
        System.out.println("Krasj?: " + playerTwoWordlist.size());
        return new ArrayList<String>(playerTwoWordlist);
    }
}
