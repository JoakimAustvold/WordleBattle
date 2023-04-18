package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.SingleplayerGameController;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.multiplayer.LobbyCode;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.MainMenuView;
import com.mygdx.game.view.multiplayer.HostLobbyView;

public class HostLobbyController extends Controller {
    private LobbyCode lobbyCode;

    public HostLobbyController() {
        System.out.println("HostLobbyController is initialized");
        this.state = LobbyInfo.getInstance();
        this.view = new HostLobbyView();
        lobbyCode = new LobbyCode();

        HostLobbyView hostLobbyView = (HostLobbyView) view;

        hostLobbyView.startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO: Add a multiplayer game screen
                System.out.println("The multiplayer game would be starting now");
                //ControllerManager.getInstance().push(new HostLobbyController());

            }
        });

       hostLobbyView.backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Vi er i HostLobbyView");
                //SingletonAPI.getInstance().removeLobby(LobbyInfo.getInstance().getCode());

                destroyLobby();
                ControllerManager.getInstance().pop();

                /*
                // TODO: Better way to write this? Without Peeking
                HostLobbyController controller = (HostLobbyController) ControllerManager.getInstance().peek();
                controller.destroyLobby();
                ControllerManager.getInstance().pop();
                */
                //ControllerManager.getInstance().push(new HostLobbyController());
            }
        });

    }

    /**
     *  Creates a new lobby and adds the creator as player one
     */
    public void createLobby(String username) {
        // Updates local state
        LobbyInfo lobbyState = (LobbyInfo) state;
        lobbyState.setUsername(username);
        lobbyState.setCode(lobbyCode.getCode());
        // Updates firebase
        SingletonAPI.getInstance().createLobby(lobbyCode);
        SingletonAPI.getInstance().addPlayerOneToLobby(lobbyCode.getCode(), username);
        // With listener
        SingletonAPI.getInstance().createPlayerTwoListener(lobbyCode.getCode());
    }


    /**
     * Removes the lobby from the database
     */
    public void destroyLobby() {
        SingletonAPI.getInstance().removeLobby(lobbyCode.getCode());
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void resetView() {

    }
}
