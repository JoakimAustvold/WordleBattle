package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.multiplayer.LobbyCode;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.HostGameView;

public class HostGameController extends Controller {

    public HostGameController() {
        this.state = LobbyInfo.getInstance();
        this.view = new HostGameView();
        addBackButtonListener();

        final HostGameView hostGameView = (HostGameView) view;

        hostGameView.createLobbyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!hostGameView.usernameTextField.getText().equals("")) {

                    // Generate a lobby
                    createLobby(hostGameView.usernameTextField.getText(),  new LobbyCode().getCode());
                    ControllerManager.getInstance().push(new HostLobbyController());
                } else {
                    //TODO: give the user feedback on missing input
                    System.out.println("You are missing a username. We cannot proceed without it :(");
                }

        }
        });
    }

    public void createLobby(String username , String code) {
        // Updates local state
        LobbyInfo lobbyState = (LobbyInfo) state;
        lobbyState.setUsername(username);
        lobbyState.setCode(code);
        // Updates firebase
        SingletonAPI.getInstance().createLobby(code);
        SingletonAPI.getInstance().addPlayerOneToLobby(code, username);
        // With listener
        SingletonAPI.getInstance().createPlayerTwoListener(code);
    }


    @Override
    public void handleInput() {

    }

    @Override
    public void resetView() {

    }
}
