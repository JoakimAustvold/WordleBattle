package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.view.GameView;
import com.mygdx.game.view.SingleplayerGameView;
import com.mygdx.game.view.multiplayer.MultiplayerGameView;

public class MultiplayerGameController extends GameController {

    public MultiplayerGameController(String word) {
        super(new MultiplayerGameView());
        this.state = new GameState(word);
    }



    @Override
    public void resetView() {
        
    }
}
