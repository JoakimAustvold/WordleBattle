package com.mygdx.game.model.states;


public class PlayState extends State { // Called WordleGame in first draft of diagrams
    private static PlayState instance = new PlayState();
    private boolean isDuel;
    private boolean isGameOver;
    private int turn;
    private String inviteCode;
    private String[] guessesPlayer1;
    private String[] guessesPlayer2;
    //private Player player1;
    //private Player player2;


    private PlayState() {
    }

    public static PlayState getInstance() {
        return instance;
    }



    @Override
    public void update(float dt) {

    }

}
