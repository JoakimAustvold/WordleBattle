package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfoState;
import com.mygdx.game.view.multiplayer.WaitingRoomView;

public class WaitingRoomController extends Controller {

    public WaitingRoomController() {
        state = LobbyInfoState.getInstance();
        view = new WaitingRoomView();

        // User then has to wait for second user to submit word
        SingletonAPI.getInstance().getWordSubmitted(LobbyInfoState.getInstance().getCode());
    }

}
