package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.MainMenuView;

/**
 * @author Marcus Birkeland
 * @version 13.03.2023
 * Empty example implementation of a Controller.
 */
public class MainMenuController extends Controller{


    public MainMenuController() {
        // Set corresponding state and view here!!!
        this.state = null;
        this.view = new MainMenuView();
    }

/*
    @Override
    public void render(SpriteBatch spriteBatch) {
        //super.render(spriteBatch);
    }

    @Override
    public void update(float deltaTime) {
        //super.update();
    }
*/
    @Override
    public void handleInput() {

    }
}
