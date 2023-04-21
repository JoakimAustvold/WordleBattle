package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.mygdx.game.model.states.State;
import com.mygdx.game.model.states.TutorialState;

public class TutorialView extends View {
    private Table table;
    private Table container;
    final private ScrollPane scrollPane;
    private final BitmapFont font;
    private Texture tutorialTexture;

    public TutorialView(TutorialState ts) {
        font = new BitmapFont();

        // Load the tutorial image from the assets folder
        tutorialTexture = new Texture("tutorial2.png");
        //backButton = new TextButton("Back", skin);

        table = new Table();
        container = new Table();
        container.setFillParent(true);
        stage.addActor(container);

        // Allowing scrolling through the highscore list
        scrollPane = new ScrollPane(table, skin);
        scrollPane.setScrollingDisabled(true,false); // Disables scrolling in the x-direction

        container.add(scrollPane).expand().fill();

        //this.stage = new Stage(new ScreenViewport());

        // Adds the title to the page
        table.pad(100).defaults().expandX().space(4);
        Label titleLabel = new Label("Tutorial", skin);
        titleLabel.setFontScale(8);
        table.add(titleLabel);

        Label informationLabel = new Label("You have six chances to guess a five-letter word. " +
                "After each guess, the game colours each letter in one of three ways: \n\n\n" +
                "Green = correct letter in the right position.\n\n" +
                "Yellow = correct letter in the wrong position.\n\n" +
                "Grey = the letter is not in the word at all.\n\n\n" +
                "Use the feedback to guess the word in as few attempts as possible.", skin);
        informationLabel.setFontScale(6f);
        informationLabel.setWrap(true);
        table.row().pad(50, 150, 50, 150);

        table.add(informationLabel).width(Gdx.graphics.getWidth()-300);
        table.row().pad(500, -350, 50, 150);

        // Set the size and position of the tutorial image
        Image tutorialImage = new Image(tutorialTexture);
        tutorialImage.setScale(2.75f); // Set the image size
        //tutorialImage.setPosition(Gdx.graphics.getWidth()/3, 10);

        table.add(tutorialImage);
        setup();
    }


    @Override
    public void setup() {
        // Ensure that the user gets a new "site", so the buttons in the main menu aren't clickable
        Gdx.input.setInputProcessor(stage);
        createBackButton();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
