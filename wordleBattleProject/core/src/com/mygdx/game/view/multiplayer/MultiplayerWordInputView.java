package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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
        stage.addActor(wordField);
        wordField.setPosition((float) (Gdx.graphics.getWidth()*0.5-Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.6));
        wordField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        wordField.setMessageText("word:");
        wordField.setMaxLength(5);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
