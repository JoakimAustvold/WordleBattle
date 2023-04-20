package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.WaitingRoomView;

public class WaitingRoomController extends Controller {

    public WaitingRoomController() {
        state = LobbyInfo.getInstance();
        view = new WaitingRoomView();
    }

    @Override
    public void resetView() {
        
    }
}
