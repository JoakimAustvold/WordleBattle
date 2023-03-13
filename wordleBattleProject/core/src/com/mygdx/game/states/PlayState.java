package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PlayState extends State { // Called WordleGame in first draft of diagrams
    private static PlayState instance = new PlayState(GameStateManager.getInstance());
    private boolean isDuel;
    private boolean isGameOver;
    private int turn;
    private String inviteCode;
    private String[] guessesPlayer1;
    private String[] guessesPlayer2;
    //private Player player1;
    //private Player player2;


    private PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public static PlayState getInstance() {
        return instance;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
