package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.TutorialController;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.view.multiplayer.MultiplayerMenuView;

public class MultiplayerMenuController extends Controller {

    public MultiplayerMenuController() {
        this.state = null;
        this.view = new MultiplayerMenuView();

        MultiplayerMenuView multiplayerMenuView = (MultiplayerMenuView) view;

    }

    @Override
    public void resetView() {

    }
}
