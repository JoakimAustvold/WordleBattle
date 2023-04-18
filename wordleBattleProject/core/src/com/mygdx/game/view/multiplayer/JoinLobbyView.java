package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;

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
