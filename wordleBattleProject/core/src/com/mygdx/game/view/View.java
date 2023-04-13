package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.model.states.State;

/**
 * @author Marcus Birkeland
 * @version 13.03.2023
 *
 * Abstract template for views.
 * A view-subclass should only handle rendering and visual elements of a given screen.
 */
public abstract class View {

    protected Stage stage;
    protected Skin skin;
    private TextButton backButton;
    //protected Table table;

    public View() {
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        this.skin.getFont("default-font").getData().setScale(4f,4f);

        //table = new Table();
        //setup();
    }

    public abstract void setup();

    /**
     * Render the view based on state.
     */
    public void render(State state, SpriteBatch spriteBatch) {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    };

    public void createBackButton() {
        backButton = new TextButton("Back", skin);
        stage.addActor(backButton);
        backButton.setPosition(50, (float) (Gdx.graphics.getHeight() * 0.90));
        backButton.setSize((float) (Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.05));
    }

    public void createBackButtonWithDefaultListener() {
        createBackButton();
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
            }
        });
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

}
