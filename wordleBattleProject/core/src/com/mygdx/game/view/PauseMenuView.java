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
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.SingleplayerGameController;
import com.mygdx.game.model.states.State;

public class PauseMenuView extends View {
    private int width;
    private int height;
    private Stage stage;
    private Skin skin;

    public TextButton backToGameButton;
    public TextButton newGameButton;
    public TextButton backToMainButton;
    public TextButton tutorialButton;
    
    public PauseMenuView() {
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        // Create menu buttons
        backToGameButton = new TextButton("Back to Game", skin);
        tutorialButton = new TextButton("Tutorial", skin);
        backToMainButton = new TextButton("End Game", skin);
        newGameButton = new TextButton("Restart Game", skin);
    }
    
    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    @Override
    public void setup() {
      Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        backToMainButton.setTransform(true);
        backToMainButton.setScale(4);
        
        backToGameButton.setTransform(true);
        backToGameButton.setScale(4);

        newGameButton.setTransform(true);
        newGameButton.setScale(4);

        tutorialButton.setTransform(true);
        tutorialButton.setScale(4);

        // Add the buttons to the table
        table.row().pad(50, 0, 50, 0);
        table.add(backToGameButton).fill().uniform();
        table.row().pad(50, 0, 50, 0);
        table.add(tutorialButton).fill().uniform();
        table.row().pad(50, 0, 50, 0);
        table.add(newGameButton).fill().uniform();
        table.row().pad(50, 0, 50, 0);
        table.add(backToMainButton).fill().uniform();
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }

    //TODO implement the rezise function
    /*
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    */
}