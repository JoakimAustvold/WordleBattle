package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.State;
import com.mygdx.game.view.View;

/**
 * @author Marcus Birkeland
 * @version 13.03.2023
 *
 * Template abstract class for a controller.
 * A controller should only handle user-input.
 * Controller contains a state and a view.
 */
public abstract class Controller {

    protected State state;
    protected View view;


    /**
     * Run the render function on the view.
     */
    public void render(SpriteBatch spriteBatch){
        view.render(state, spriteBatch);
    }

    /**
     * Run the update function in the model.
     */
    public void update(float deltaTime){
        state.update(deltaTime);
    }

    /**
     * Handle input.
     */
    public abstract void handleInput();
}
