package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {
    private static MenuState instance = new MenuState(GameStateManager.getInstance());

    private MenuState(GameStateManager gsm) {
        super(gsm);
    }

    public static MenuState getInstance() {
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
