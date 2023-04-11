package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;

/**
 * @author Marcus Birkeland
 * @version 13.03.2023
 *
 * Template abstract class for a controller.
 * A controller should only handle user-input, and pass between state and view.
 * Controller contains a state and a view responsible for update() and render() methods respectively.
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
        // TODO: should a state be able to be null?
          if (state != null) {
                   state.update(deltaTime);
               }
    }

    /**
     * Handle input.
     */
    public abstract void handleInput();

     public View getView() {
            return view;
        }
}
