package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.view.multiplayer.MultiplayerMenuView;

public class MultiplayerMenuController extends Controller {

        public MultiplayerMenuController() {
            this.state = null;
            this.view = new MultiplayerMenuView();
        }

    @Override
    public void handleInput() {

    }
}
