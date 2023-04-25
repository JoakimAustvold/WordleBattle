package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.states.State;
import com.mygdx.game.model.states.multiplayer.LobbyInfoState;

/**
 * The view the player hosting a lobby sees. They will see the user joining the lobby and be able to
 * start the game once a second player has joined.
 */
public class HostLobbyView extends LobbyView {

    public TextButton startGameButton;
    private BitmapFont font;

    public HostLobbyView() {
        super();
        startGameButton = new TextButton("Start game", skin);
        font = new BitmapFont();

        createButton();
        setup();
    }

    @Override
    public void setup() {
        super.setup();
        Gdx.input.setInputProcessor(stage);
    }

    public void createButton() {
        //stage.addActor(startGameButton);
        startGameButton.setPosition((float) (Gdx.graphics.getWidth() *0.5 -Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight()*0.1));
        //startGameButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {

        super.render(state, spriteBatch);

        LobbyInfoState lobbyState = (LobbyInfoState) state;
        font.getData().setScale(4);

        if (!(state instanceof LobbyInfoState)) {
            throw new StateException("Wrong state type! Please provide LobbyInfo as state");
        }

        // Only show the start game button if the lobby is full
        if(lobbyState.getPlayerTwo() == null) {
            if (stage.getActors().contains(startGameButton, true)) {
                startGameButton.remove();
            }
            font.draw(spriteBatch, "Waiting for player two", (float) Gdx.graphics.getWidth() / 2 - 350, (float) (Gdx.graphics.getHeight() * 0.2));
        } else {
            if (!stage.getActors().contains(startGameButton, true)) {
                stage.addActor(startGameButton);
            }
        }


    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}
