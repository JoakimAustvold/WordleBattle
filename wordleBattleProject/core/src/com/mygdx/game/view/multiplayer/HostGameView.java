package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.view.View;

/**
 * The view a user will see when starting a hosted game.
 * The view contains a back button, a field to enter their username and a button to create a lobby.
 * The username field must be filled in before the lobby can be created.
 */
public class HostGameView extends View {

    public TextField usernameTextField;
    public TextButton createLobbyButton;

    public HostGameView() {
        usernameTextField = new TextField("", skin);
        createLobbyButton = new TextButton("Create lobby", skin);
        setup();
    }

     public void setup() {
        Gdx.input.setInputProcessor(stage);
        createBackButton();

        stage.addActor(usernameTextField);
        stage.addActor(createLobbyButton);

        usernameTextField.setPosition((float) (Gdx.graphics.getWidth()*0.5-Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.4));
        usernameTextField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        usernameTextField.setMessageText("Username: ");

        createLobbyButton.setPosition((float) (Gdx.graphics.getWidth() *0.5 -Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight()*0.1));
        createLobbyButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
    }

}
