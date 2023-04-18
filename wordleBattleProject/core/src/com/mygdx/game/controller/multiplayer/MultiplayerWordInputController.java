package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.MultiplayerWordInputView;

public class MultiplayerWordInputController extends Controller {

    public MultiplayerWordInputController() {
        this.state = LobbyInfo.getInstance();

        this.view = new MultiplayerWordInputView();
    }

    @Override
    public void resetView() {

    }
}
