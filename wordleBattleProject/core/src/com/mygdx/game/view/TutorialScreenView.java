package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.model.states.State;

public class TutorialScreenView extends View {
    private MainMenuController controller;
    private Stage stage;
    private Skin skin;
    private int width;
    private int height;
    private final SpriteBatch batch = new SpriteBatch();
    private final BitmapFont font = new BitmapFont();

    public TutorialScreenView(MainMenuController mainMenuController) {
        this.controller = mainMenuController;
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));

        // Fontsizing and scaling
        float fontSize = 4.5f; // Change this to the desired font size

        // Set the scale factor based on the desired font size and the original font size
        float scaleFactor = fontSize / font.getData().scaleX;
        font.getData().setScale(scaleFactor);
    }


    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        // Ensure that the user gets a new "site", so the buttons in the main menu aren't clickable
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        font.draw(spriteBatch, "Tutorial \n 1. Start guessing\nthe word \n", Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() - 10), Gdx.graphics.getHeight() - 10);

        stage.addActor(table);
    }

    //TODO implement the resize function
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}