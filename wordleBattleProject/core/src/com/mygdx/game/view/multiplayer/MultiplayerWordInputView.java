package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.multiplayer.JoinLobbyController;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;

public class MultiplayerWordInputView extends View {

    private Stage stage;
    private Skin skin;

    public MultiplayerWordInputView() {
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        this.skin.getFont("default-font").getData().setScale(4f,4f);
        setup();
    }
    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(stage);
        final TextField wordField = new TextField("", skin);
        TextButton submitButton = new TextButton("Submit", skin);
        stage.addActor(wordField);
        stage.addActor(submitButton);
        wordField.setPosition((float) (Gdx.graphics.getWidth()*0.5-Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.6));
        wordField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        wordField.setMessageText("word:");
        wordField.setMaxLength(5);
        submitButton.setPosition((float) (Gdx.graphics.getWidth() *0.5 -Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight()*0.1));
        submitButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO: Submit word to firebase and open the Multiplayer Game View
            }
        });
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
