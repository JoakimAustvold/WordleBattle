package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuView extends View {

    public TextButton singleplayerButton;
    public TextButton multiplayerButton;
    public TextButton settingsButton;
    public TextButton tutorialButton;
    public TextButton highscoreButton;

    public MainMenuView() {
        super();
        // Create menu buttons
        singleplayerButton = new TextButton("Singleplayer", skin);
        multiplayerButton = new TextButton("Multiplayer", skin);
        settingsButton = new TextButton("Settings", skin);
        tutorialButton = new TextButton("Tutorial", skin);
        highscoreButton = new TextButton("Highscores", skin);

        setup();
    }

    public void setup() {
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Label titleLabel = new Label("WordleBattle", skin);
        titleLabel.setFontScale(8, 8);
        stage.addActor(titleLabel);
        titleLabel.setPosition((float) (Gdx.graphics.getWidth() * 0.5 - titleLabel.getWidth()), (float) (Gdx.graphics.getHeight() * 0.8));

        // Add the buttons to the table
        table.row().pad(50, 0, 50, 0);
        table.add(singleplayerButton).fill().uniform();
        table.row().pad(50, 0, 50, 0);
        table.add(multiplayerButton).fill().uniform();
        table.row().pad(50, 0, 50, 0);
        table.add(settingsButton).fill().uniform();
        table.row().pad(50, 0, 50, 0);
        table.add(tutorialButton).fill().uniform();
        table.row().pad(50, 0, 50, 0);
        table.add(highscoreButton).fill().uniform();
    }


}
