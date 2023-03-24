package com.mygdx.game.model.states;


import com.mygdx.game.model.words.WordGenerator;

public class PlayState extends State { // Called WordleGame in first draft of diagrams
    private boolean isDuel;
    private boolean isGameOver;
    private int turn;
    private String inviteCode;
    private String[] guessesPlayer1;
    private String[] guessesPlayer2;
    private String word;
    //private Player player1;
    //private Player player2;


    public PlayState() {
        WordGenerator wg = new WordGenerator(WordGenerator.Language.ENGLISH);
        word = wg.generateWord();
    }


    @Override
    public void update(float dt) {

    }

    public String getWord(){
        return this.word;
    }

}
