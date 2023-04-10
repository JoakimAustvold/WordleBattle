package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;

public class JoinGameView extends View {

    private Stage stage;
    private Skin skin;
      public JoinGameView() {
            this.stage = new Stage(new ScreenViewport());
            this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
            setup();
        }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        //Gdx.input.getTextInput(listener, "Dialog Title", "Insert lobby code", "Hint Value");
         stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
         stage.draw();
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        TextField inviteCodeTextField = new TextField("", skin);

        inviteCodeTextField.setScale(4);

        table.row().pad(50, 0, 50, 0);
        table.add(inviteCodeTextField).fill().uniform();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
