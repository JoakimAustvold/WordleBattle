package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.states.State;

/**
 * A view reserved for a player joining a lobby
 * A the host will not see what is displayed on this page
 */
public class JoinLobbyView extends LobbyView {

    private BitmapFont font;

    public JoinLobbyView() {
        super();
        font = new BitmapFont();
        font.getData().setScale(4);
        setup();
    }

    @Override
    public void setup() {
        super.setup();
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        super.render(state, spriteBatch);
        font.draw(spriteBatch, "Waiting for the host to start the game", (float) 75, (float) (Gdx.graphics.getHeight() * 0.2));
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}
