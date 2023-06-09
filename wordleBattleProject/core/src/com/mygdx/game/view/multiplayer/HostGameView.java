package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.states.State;
import com.mygdx.game.model.states.multiplayer.LobbyInfoState;
import com.mygdx.game.model.states.multiplayer.LobbyStatus;
import com.mygdx.game.view.View;

/**
 * The view a user will see when starting a hosted game.
 * The view contains a back button, a field to enter their username and a button to create a lobby.
 * The username field must be filled in before the lobby can be created.
 */
public class HostGameView extends View {

    public TextField usernameTextField;
    public TextButton createLobbyButton;
    private BitmapFont font;
    //private SpriteBatch spriteBatch;

    public HostGameView() {
        usernameTextField = new TextField("", skin);
        createLobbyButton = new TextButton("Create lobby", skin);
        //spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(4);
        setup();
    }

     public void setup() {
        Gdx.input.setInputProcessor(stage);
        createBackButton();

        stage.addActor(usernameTextField);
        stage.addActor(createLobbyButton);


        usernameTextField.setPosition((float) (Gdx.graphics.getWidth()*0.3 - 50), (float) (Gdx.graphics.getHeight() * 0.4));
        usernameTextField.setSize((float) (Gdx.graphics.getWidth()*0.5), (float) (Gdx.graphics.getHeight() * 0.05));
        usernameTextField.setMessageText("Username");

        createLobbyButton.setPosition((float) (Gdx.graphics.getWidth() *0.3 - 50), (float) (Gdx.graphics.getHeight()*0.1));
        //createLobbyButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        LobbyInfoState lobbyState = (LobbyInfoState) state;

        if (!(state instanceof LobbyInfoState)) {
            throw new StateException("Wrong state type! Please provide LobbyInfo as state");
        }

        if (lobbyState.getLobbyStatus() == LobbyStatus.MISSINGUSERNAME) {
            font.draw(spriteBatch, "You need a username.", (float) Gdx.graphics.getWidth() / 2 - 350, (float) (Gdx.graphics.getHeight() * 0.2));
        }

        font.draw(spriteBatch, "Host a lobby by creating a\nusername and pressing the button!", (float) (Gdx.graphics.getWidth()*0.1), (float) (Gdx.graphics.getHeight()*0.7));


        super.render(state, spriteBatch);
    }
}
