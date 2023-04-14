package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.controller.multiplayer.MultiplayerMenuController;
import com.mygdx.game.controller.SettingsController;
import com.mygdx.game.model.states.State;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.SettingsController;
import com.mygdx.game.controller.SingleplayerGameController;
import com.mygdx.game.model.states.State;

public class MainMenuView extends View {
    
    private Stage stage;
    private Skin skin;
    
    public MainMenuView() {
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        setup();
    }

    public void setup() {
        Gdx.input.setInputProcessor(stage);
        
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        // Create menu buttons
        TextButton singleplayerButton = new TextButton("Singleplayer", skin);
        TextButton multiplayerButton = new TextButton("Multiplayer", skin);
        TextButton settingsButton = new TextButton("Settings", skin);
        TextButton tutorialButton = new TextButton("Tutorial", skin);

        singleplayerButton.setTransform(true);
        singleplayerButton.setScale(4);
        multiplayerButton.setTransform(true);
        multiplayerButton.setScale(4);
        settingsButton.setTransform(true);
        settingsButton.setScale(4);
        tutorialButton.setTransform(true);
        tutorialButton.setScale(4);

        // Add the buttons to the table
        table.row().pad(50, 0, 50, 0);
        table.add(singleplayerButton).fill().uniform();
        table.row().pad(50, 0, 50, 0);
        table.add(multiplayerButton).fill().uniform();
        table.row().pad(50, 0, 50, 0);
        table.add(settingsButton).fill().uniform();
        table.row().pad(50, 0, 50, 0);
        table.add(tutorialButton).fill().uniform();
        
        singleplayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new SingleplayerGameController());
            }
        });
        
        multiplayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new MultiplayerMenuController());
            }
        });

        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().push(new SettingsController());
            }
        });

        
    }
    

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    //TODO implement the rezise function
/*    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }*/


}
