package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.SettingsController;
import com.mygdx.game.model.states.State;

public class SettingsView extends View {

    //private BitmapFont font;
    /*
    private Stage stage;
    private Skin skin;
    */

    public SettingsView() {
       // font = new BitmapFont();
       super();
       /*
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        setup();
        */
    }

    public void setup() {
        Gdx.input.setInputProcessor(stage);

        /*
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        // Create menu buttons
        TextButton backButton = new TextButton("Back", skin);
        backButton.setTransform(true);
        backButton.setScale(4);

        // Add the buttons to the table
        //table.row().pad(50, 0, 50, 0);
        table.add(backButton).fill().uniform();

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
            }
        });
        */
        createBackButtonWithDefaultListener();
    }

    /*
    @Override
    public void render(State state, SpriteBatch spriteBatch) {
       // font.draw(spriteBatch, "Settings view", Gdx.graphics.getWidth()/2 , Gdx.graphics.getWidth()/2);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }
    */

    /*
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    */
}