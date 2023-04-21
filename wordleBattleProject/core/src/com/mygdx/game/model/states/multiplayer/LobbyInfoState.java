package com.mygdx.game.model.states.multiplayer;

import com.mygdx.game.model.states.State;

import java.util.ArrayList;

/**
 * A state used for lobbies.
 */
public class LobbyInfoState extends State {

    private static final LobbyInfoState lobbyInfoInstance = new LobbyInfoState();

    public static LobbyInfoState getInstance() {
        return lobbyInfoInstance;
    }

    private CurrentPlayer currentPlayer;
    private String code;



    /*
    private List<Player> players;
    private List<String> wordList;
    private List<Integer> scores;
     */

    private String playerOne;
    private String playerTwo;
    private ArrayList<String> playerOneWordlist;
    private ArrayList<String> playerTwoWordlist;
    private Integer playerOneScore;
    private Integer playerTwoScore;

    private LobbyStatus lobbyStatus;

    private LobbyInfoState() {
        lobbyStatus = LobbyStatus.UNKNOWN;
        playerOneWordlist = new ArrayList<>();
        playerTwoWordlist = new ArrayList<>();

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
        return new ArrayList<String>(playerTwoWordlist);
    }

    public Integer getPlayerOneScore() {
        return playerOneScore;
    }

    public Integer getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerOneScore(Integer playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public void setPlayerTwoScore(Integer playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }
}
