package com.mygdx.game.model.states;

public class HighScoreState extends State {
    private int[] highscores;
    private static HighScoreState instance = new HighScoreState();

    public HighScoreState() {

    }

    public static HighScoreState getInstance() {
        return instance;
    }

    @Override
    public void update(float dt) {

    }


}
