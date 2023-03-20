package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TutorialState extends State{

    private static GameStateManager gsm;

    private static TutorialState instance = new TutorialState(gsm);

    public TutorialState(GameStateManager gsm) {
        super(gsm);
    }


    @Override
    public void update(float dt) {

    }

}
