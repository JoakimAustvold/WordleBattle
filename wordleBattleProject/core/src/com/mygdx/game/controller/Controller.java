package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.State;
import com.mygdx.game.view.View;

public abstract class Controller {

    private State state;
    private View view;

    /**
     * Run the render function on the view.
     */
    public void render(SpriteBatch spriteBatch){
        view.render(state, spriteBatch);
    }

    /**
     * Run the update function in the model.
     */
    public void update(){
        state.update();
    }

    /**
     * Handle input.
     */
    public void handleInput(){

    }
}
