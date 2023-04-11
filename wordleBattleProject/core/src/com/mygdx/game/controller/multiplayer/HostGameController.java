package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.HostGameView;

public class HostGameController extends Controller {

        public HostGameController() {
        // Set corresponding state and view here!!!
        this.state = LobbyInfo.getInstance();
        this.view = new HostGameView();
    }

    @Override
    public void handleInput() {

    }
}
