package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.view.GameView;

public class MultiplayerGameView extends GameView {

    public MultiplayerGameView() {
        super();
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(keyboardStage);
        super.setup();
    }


}
