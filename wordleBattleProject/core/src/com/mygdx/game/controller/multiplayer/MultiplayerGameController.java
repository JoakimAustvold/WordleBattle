package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.multiplayer.CurrentPlayer;
import com.mygdx.game.model.states.multiplayer.LobbyInfoState;
import com.mygdx.game.model.states.multiplayer.LobbyStatus;
import com.mygdx.game.view.multiplayer.MultiplayerGameView;

import java.util.Stack;

public class MultiplayerGameController extends GameController {

    public MultiplayerGameController(String word) {
        super(new MultiplayerGameView(), new GameState(word));

        SingletonAPI.getInstance().getMultiplayerScore(LobbyInfoState.getInstance().getCode());

        setupView();
    }

    @Override
    protected void setupView() {
        super.setupView();

        final MultiplayerGameView multiplayerGameView = (MultiplayerGameView) view;


        multiplayerGameView.backToLobbyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {

                LobbyInfoState.getInstance().setLobbyStatus(LobbyStatus.UNKNOWN);
                SingletonAPI.getInstance().setOnlineLobbyStatus(LobbyInfoState.getInstance().getCode(), LobbyStatus.UNKNOWN);
                // TODO: fix this mess
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().pop();
            }
        });
    }

}
