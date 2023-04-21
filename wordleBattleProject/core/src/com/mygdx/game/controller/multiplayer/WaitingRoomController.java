package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.WaitingRoomView;

public class WaitingRoomController extends Controller {

    public WaitingRoomController() {
        state = LobbyInfo.getInstance();
        view = new WaitingRoomView();

        // User then has to wait for second user to submit word
        SingletonAPI.getInstance().getWordSubmitted(LobbyInfo.getInstance().getCode());
    }

    @Override
    public void resetView() {
        
    }
}
