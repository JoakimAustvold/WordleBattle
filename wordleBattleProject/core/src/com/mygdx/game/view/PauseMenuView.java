package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PauseMenuView extends View {

    public TextButton backToGameButton;
    public TextButton newGameButton;
    public TextButton backToMainButton;
    public TextButton tutorialButton;
    
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

}
