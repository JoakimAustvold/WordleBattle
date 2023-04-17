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
import com.mygdx.game.controller.multiplayer.JoinLobbyController;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;
import com.mygdx.game.controller.ControllerManager;

/**
 * The view a user sees when attempting to join another player's game.
 */
public class JoinGameView extends View {

    private Stage stage;
    private Skin skin;

    // UI elements

    // final
    public TextField inviteCodeTextField;
    public TextField usernameTextField;
    // normal
    public TextButton joinButton;
    public TextButton backButton;


    public JoinGameView() {
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        this.skin.getFont("default-font").getData().setScale(4f,4f);

        // initiate UI elements
        inviteCodeTextField = new TextField("", skin);
        usernameTextField = new TextField("", skin);
        joinButton = new TextButton("Join lobby", skin);
        backButton = new TextButton("Back", skin);

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

        stage.addActor(inviteCodeTextField);
        stage.addActor(joinButton);
        stage.addActor(usernameTextField);
        stage.addActor(backButton);

        inviteCodeTextField.setPosition((float) (Gdx.graphics.getWidth()*0.5-Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.6));
        inviteCodeTextField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        inviteCodeTextField.setMessageText("Code: ");

        usernameTextField.setPosition((float) (Gdx.graphics.getWidth()*0.5-Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.4));
        usernameTextField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        usernameTextField.setMessageText("Username: ");

        joinButton.setPosition((float) (Gdx.graphics.getWidth() *0.5 -Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight()*0.1));
        joinButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        backButton.setPosition(50, (float) (Gdx.graphics.getHeight() * 0.90));
        backButton.setSize((float) (Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.05));

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
