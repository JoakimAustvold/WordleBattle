package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;

/**
 * A view reserved for a player joining a lobby
 * A the host will not see what is displayed on this page
 */
public class JoinLobbyView extends LobbyView {
    
    public JoinLobbyView() {
        super();
        setup();
    }

    @Override
    public void setup() {
        super.setup();
        Gdx.input.setInputProcessor(stage);
    }
}
