package com.mygdx.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.State;

/**
 * @author Marcus Birkeland
 * @version 13.03.2023
 *
 * Abstract template for views.
 * A view-subclass should only handle rendering and visual elements of a given screen.
 */
public abstract class View {

    /**
     * Render the view based on state.
     */
    public abstract void render(State state, SpriteBatch spriteBatch);
}
