package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.multiplayer.JoinLobbyController;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.states.State;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
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
    private BitmapFont font;
    
    public JoinGameView() {
        super();

        inviteCodeTextField = new TextField("", skin);
        usernameTextField = new TextField("", skin);
        joinButton = new TextButton("Join lobby", skin);
        font = new BitmapFont();
        font.getData().setScale(4);

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

    @Override
    public void render(State state, SpriteBatch spriteBatch) {

        LobbyInfo lobbyState = (LobbyInfo) state;

        if (!(state instanceof LobbyInfo)) {
            throw new StateException("Wrong state type! Please provide LobbyInfo as state");
        }

        switch(lobbyState.getLobbyStatus()) {
            case UNKNOWN:
                //font.draw(spriteBatch, "This lobby does not exist.", (float) Gdx.graphics.getWidth() / 2 - 400, (float) (Gdx.graphics.getHeight() * 0.4));
                break;
            case AVAILABLE:
                //font.draw(spriteBatch, "available", (float) Gdx.graphics.getWidth() / 2 - 400, (float) (Gdx.graphics.getHeight() * 0.4));
                // TODO: Fix. This line breaks with controller-view-model pattern
                ControllerManager.getInstance().push(new JoinLobbyController());
                break;
            case OCCUPIED:
                font.draw(spriteBatch, "This lobby is already full.", (float) Gdx.graphics.getWidth() / 2 - 350, (float) (Gdx.graphics.getHeight() * 0.2));                break;
            case NONEXISTENT:
                font.draw(spriteBatch, "This lobby does not exist.", (float) Gdx.graphics.getWidth() / 2 - 350, (float) (Gdx.graphics.getHeight() * 0.2));
                break;
            case MISSINGUSERNAME:
                font.draw(spriteBatch, "You need a username.", (float) Gdx.graphics.getWidth() / 2 - 300, (float) (Gdx.graphics.getHeight() * 0.2));
                break;
            default:
                font.draw(spriteBatch, "error", (float) Gdx.graphics.getWidth() / 2 - 400, (float) (Gdx.graphics.getHeight() * 0.2));
        }

        super.render(state, spriteBatch);
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
    
}
