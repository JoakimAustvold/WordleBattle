package com.mygdx.game.model.states;


public class PlayState extends State { // Called WordleGame in first draft of diagrams
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


    @Override
    public void update(float dt) {

    }

}
