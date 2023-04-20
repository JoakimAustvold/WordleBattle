package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PauseMenuView extends View {

    public TextButton backToGameButton;
    public TextButton newGameButton;
    public TextButton backToMainButton;
    public TextButton tutorialButton;

    private static final int buttonPaddingX = -120;
    private static final int buttonPaddingY = 80;
    
    public PauseMenuView() {
        super();
        // Create menu buttons
        backToGameButton = new TextButton("Back to Game", skin);
        tutorialButton = new TextButton("Tutorial", skin);
        backToMainButton = new TextButton("End Game", skin);
        newGameButton = new TextButton("New Game", skin);
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Add the buttons to the table
        table.row().pad(buttonPaddingY, buttonPaddingX, buttonPaddingY, buttonPaddingX);
        table.add(backToGameButton).fill().uniform();
        table.row().pad(buttonPaddingY, buttonPaddingX, buttonPaddingY, buttonPaddingX);
        table.add(tutorialButton).fill().uniform();
        table.row().pad(buttonPaddingY, buttonPaddingX, buttonPaddingY, buttonPaddingX);
        table.add(newGameButton).fill().uniform();
        table.row().pad(buttonPaddingY, buttonPaddingX, 50, buttonPaddingX);
        table.add(backToMainButton).fill().uniform();
        table.row().pad(50, -64, 50, -64);
        table.add(new Label("",skin)).fill().uniform(); // hack to get padding inside button

    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }

}
