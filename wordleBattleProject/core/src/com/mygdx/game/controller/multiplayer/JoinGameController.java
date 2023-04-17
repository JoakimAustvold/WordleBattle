package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.JoinGameView;

public class JoinGameController extends Controller {

    public JoinGameController() {
        this.state = LobbyInfo.getInstance();
        this.view = new JoinGameView();
    }
    
    @Override
    public void handleInput() {

    }

    @Override
    public void resetView() {

    }

}
