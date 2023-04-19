package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.states.multiplayer.MultiplayerWordInputState;
import com.mygdx.game.view.multiplayer.MultiplayerWordInputView;

public class MultiplayerWordInputController extends Controller {

    public MultiplayerWordInputController() {
        this.state = new MultiplayerWordInputState();

        this.view = new MultiplayerWordInputView();
    }

    @Override
    public void resetView() {

    }
}
