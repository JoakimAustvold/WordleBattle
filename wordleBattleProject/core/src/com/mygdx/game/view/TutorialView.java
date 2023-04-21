package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.mygdx.game.model.states.State;
import com.mygdx.game.model.states.TutorialState;

public class TutorialView extends View {
    private final BitmapFont font = new BitmapFont();
    private Stage stage;
    private Texture tutorialTexture;

    public TextButton backButton;


    public TutorialView(TutorialState ts) {
        // Fontsizing and scaling
        float fontSize = 3f; // Change this to the desired font size

        // Set the scale factor based on the desired font size and the original font size
        float scaleFactor = fontSize / font.getData().scaleX;
        font.getData().setScale(scaleFactor);

        // Load the tutorial image from the assets folder
        tutorialTexture = new Texture("tutorial2.png");
        backButton = new TextButton("Back", skin);
        this.stage = new Stage(new ScreenViewport());
        setup();
    }


    @Override
    public void render(State state, SpriteBatch spriteBatch) {
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

        // Create back button
        backButton.setTransform(true);
        backButton.setScale(2);

        // Add the button to the stage
        stage.addActor(backButton);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void setup() {
        // Ensure that the user gets a new "site", so the buttons in the main menu aren't clickable
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);

        // Set the size and position of the tutorial image
        Image tutorialImage = new Image(tutorialTexture);
        tutorialImage.setScale(1.75f); // Set the image size
        tutorialImage.setPosition(Gdx.graphics.getWidth()/3, 10);

        stage.addActor(tutorialImage);
        stage.addActor(table);
    }

    @Override
    public void dispose() {

    }


    //TODO implement the resize function
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
