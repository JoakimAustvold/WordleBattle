package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.model.states.State;

public class TutorialScreenView extends View {
    private final SpriteBatch batch = new SpriteBatch();
    private final BitmapFont font = new BitmapFont();
    private MainMenuController controller;
    private Stage stage;
    private Texture tutorialTexture;

    public TutorialScreenView(MainMenuController mainMenuController) {
        this.controller = mainMenuController;
        this.stage = new Stage(new ScreenViewport());

        // Fontsizing and scaling
        float fontSize = 3f; // Change this to the desired font size

        // Set the scale factor based on the desired font size and the original font size
        float scaleFactor = fontSize / font.getData().scaleX;
        font.getData().setScale(scaleFactor);

        // Load the tutorial image from the assets folder
        tutorialTexture = new Texture("tutorial.png");
    }


    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        // Ensure that the user gets a new "site", so the buttons in the main menu aren't clickable
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        // Set the maximum width of the text
        float textWidth = Gdx.graphics.getWidth() - 20;

        // Use stage.getBatch() to draw the text using the same batch as the stage
        stage.getBatch().begin();
        font.draw(stage.getBatch(),
                "Tutorial\n" +
                        "You have six chances to guess a five-letter word.\n" +
                        "After each guess, the game colours each letter in one of three ways:\n" +
                        "\n" +
                        "Green = correct letter in the right position.\n" +
                        "Yellow = correct letter in the wrong position.\n" +
                        "Grey = the letter is not in the word at all.\n" +
                        "\n" +
                        "Use the feedback to guess the word in as few attempts as possible.",
                10, Gdx.graphics.getHeight() - 10, textWidth, Align.left, true);

        stage.getBatch().end();

        // Add the tutorial image to the table
        Image tutorialImage = new Image(tutorialTexture);
        tutorialImage.setScale(2); // Double the size of the image
        table.add(tutorialImage).padTop(10);

        stage.addActor(table);
        stage.draw();
    }


    //TODO implement the resize function
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
