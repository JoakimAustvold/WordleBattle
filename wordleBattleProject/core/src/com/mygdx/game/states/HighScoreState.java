package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HighScoreState extends State {
    private int[] highscores;
    private static GameStateManager gsm;
    private static HighScoreState instance = new HighScoreState(gsm);

    public HighScoreState(GameStateManager gsm) {
        super(gsm);
    }

    public static HighScoreState getInstance() {
        return instance;
    }

    @Override
    public void update(float dt) {

    }


}
