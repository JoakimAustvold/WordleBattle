package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.model.states.TutorialState;


/**
 * The first view you see when you open the application
 * Contains menu buttons that will take you to other views
 */
public class MainMenuView extends View {

    // Buttons are be public so that event listeners easily can be added in the corresponding controller
    // TODO: should the buttons be public, or should it be private with getters???
    public TextButton singleplayerButton;
    public TextButton multiplayerButton;
    public TextButton settingsButton;
    public TextButton tutorialButton;
    public TextButton highscoreButton;

    private TutorialState ts;

    private static final int buttonPaddingX = -120;

    public MainMenuView(TutorialState ts) {
        super();
        this.ts = ts;
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
        stage.addActor(table);

        Label titleLabel = new Label("WordleBattle", skin);
        titleLabel.setFontScale(8, 8);
        stage.addActor(titleLabel);
        titleLabel.setFontScale(12f);
        titleLabel.setPosition((float) (Gdx.graphics.getWidth() * 0.5 - titleLabel.getWidth() * 0.5 - 200), (float) (Gdx.graphics.getHeight() * 0.8));

        // Add the buttons to the table
        table.row().pad(50, buttonPaddingX, 50, buttonPaddingX);
        table.add(singleplayerButton).fill().uniform();
        table.row().pad(50, buttonPaddingX, 50, buttonPaddingX);
        table.add(multiplayerButton).fill().uniform();
        table.row().pad(50, buttonPaddingX, 50, buttonPaddingX);
        table.row().pad(50, buttonPaddingX, 50, buttonPaddingX);
        table.add(tutorialButton).fill().uniform();
        table.row().pad(50, buttonPaddingX, 50, buttonPaddingX);
        table.add(highscoreButton).fill().uniform();
        table.row().pad(50, -64, 50, -64);
        table.add(new Label("",skin)).fill().uniform(); // hack to get padding inside button


    }

}
