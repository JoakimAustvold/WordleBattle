package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;

/**
 * Template abstract class for a controller.
 * A controller should only handle user-input, and pass between state and view.
 * Controller contains a state and a view responsible for update() and render() methods respectively.
 */
public abstract class Controller {

    protected State state;
    protected View view;

    public void addBackButtonListener() {
        view.backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
            }
        });
    }

    /**
     * Run the render function on the view.
     */
    public void render(SpriteBatch spriteBatch){
        view.render(state, spriteBatch);
    }
    

    public abstract void resetView();

    public View getView() {
            return view;
        }


}
