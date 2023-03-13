package com.mygdx.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.State;

public abstract class View {

    /**
     * Render the view based on state.
     */
    public abstract void render(State state, SpriteBatch spriteBatch);
}
