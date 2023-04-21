package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;

public class MultiplayerWordInputView extends View {

    //private Stage stage;
    //private Skin skin;
    private BitmapFont font;
    private String feedbackMessage;

    public TextButton submitButton;
    public TextField wordField;


    public MultiplayerWordInputView() {
        font = new BitmapFont();
        feedbackMessage = "Enter your word";
        submitButton = new TextButton("Submit", skin);
        wordField = new TextField("", skin);
        font.getData().setScale(6);
        setup();
    }
    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        super.render(state, spriteBatch);

        font.draw(spriteBatch, feedbackMessage,(float) (Gdx.graphics.getWidth()*0.5-Gdx.graphics.getWidth()*0.3), (float) (Gdx.graphics.getHeight() * 0.5));
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(stage);
        stage.addActor(wordField);
        stage.addActor(submitButton);
        wordField.setPosition((float) (Gdx.graphics.getWidth()*0.5-Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.6));
        wordField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        wordField.setMessageText("word:");
        wordField.setMaxLength(5);
        submitButton.setPosition((float) (Gdx.graphics.getWidth() *0.5 -Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight()*0.1));
        submitButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }
}
