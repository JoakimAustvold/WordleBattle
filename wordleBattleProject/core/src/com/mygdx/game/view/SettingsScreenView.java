package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.model.states.State;

public class SettingsScreenView extends View {
    private MainMenuController controller;
    private int width;
    private int height;
    private Stage stage;
    private Skin skin;
    public SettingsScreenView(MainMenuController mainMenuController) {
        this.controller = mainMenuController;
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
    }


    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        // Create menu buttons
        TextButton backToMainButton = new TextButton("backToMainButton", skin);

        backToMainButton.setTransform(true);
        backToMainButton.setScale(4);

        // Add the buttons to the table
        table.row().pad(50, 0, 50, 0);
        table.add(backToMainButton).fill().uniform();

        backToMainButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.changeScreen(MainMenuController.MENU);
            }
        });

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    //TODO implement the rezise function
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
