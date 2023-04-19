package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.states.State;

/**
 * Abstract template for views.
 * A view-subclass should only handle rendering and visual elements of a given screen.
 * Initiates a common stage, skin and background textures
 * Contains useful methods for all subc-lasses
 */
public abstract class View {

    protected Stage stage;
    protected Skin skin;
    protected Texture backgroundTexture;
    public TextButton backButton;

    public View() {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        skin.getFont("default-font").getData().setScale(4f, 4f);
        // TODO: Pick a nice background ^^
        backgroundTexture = new Texture(Gdx.files.internal("textures/blackboard_background.jpg"));
        backButton = new TextButton("Back", skin);
    }

    /**
     * A method used to add and position elements to the stage
     * Used to make the view responsive again after a stack-pop
     */
    // TODO: RestView vs. setup. Both used to make the view responsive again
    public abstract void setup();

    /**
     * Render the view based on state.
     * Draws the background and all elements added to the stage
     */
    public void render(State state, SpriteBatch spriteBatch) {
        // Draws the background
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0 ,  (int)  (Gdx.graphics.getWidth()), (int)  (Gdx.graphics.getHeight()));
        stage.getBatch().end();

        // Renders the rest of the stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Creates a button with the text "back" in the upper left corner.
     * A listener has to be added through the controller
     */
    public void createBackButton() {
        stage.addActor(backButton);
        backButton.setPosition(50, (float) (Gdx.graphics.getHeight() * 0.90));
        backButton.setSize((float) (Gdx.graphics.getWidth() * 0.2), (float) (Gdx.graphics.getHeight() * 0.05));
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

}
