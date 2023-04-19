package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.view.View;

/**
 * The view a user sees when attempting to join another player's game.
 * The view contains a back button, a input text field for the the invite code to a game lobby, 
 * and an input text field for the players own username
 * 
 */
public class JoinGameView extends View {

    public TextField inviteCodeTextField;
    public TextField usernameTextField;
    public TextButton joinButton;
    
    public JoinGameView() {
        super();

        inviteCodeTextField = new TextField("", skin);
        usernameTextField = new TextField("", skin);
        joinButton = new TextButton("Join lobby", skin);

        setup();
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(stage);
        createBackButton();

        stage.addActor(inviteCodeTextField);
        stage.addActor(joinButton);
        stage.addActor(usernameTextField);

        inviteCodeTextField.setPosition((float) (Gdx.graphics.getWidth()*0.5-Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.6));
        inviteCodeTextField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        inviteCodeTextField.setMessageText("Code: ");

        usernameTextField.setPosition((float) (Gdx.graphics.getWidth()*0.5-Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.4));
        usernameTextField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        usernameTextField.setMessageText("Username: ");

        joinButton.setPosition((float) (Gdx.graphics.getWidth() *0.5 -Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight()*0.1));
        joinButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
    }
    
}
