package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.JoinLobbyView;
import com.mygdx.game.view.multiplayer.LobbyView;


public class JoinLobbyController extends Controller {

    public JoinLobbyController() {
        this.state = LobbyInfo.getInstance();
       // this.view = new LobbyView();
        this.view = new JoinLobbyView();

        //LobbyView lobbyView = (LobbyView) view;
        JoinLobbyView lobbyView = (JoinLobbyView) view;

        lobbyView.backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            System.out.println("Vi er i lobbyView");
            /*
            // TODO: Better way to write this? Without Peeking
            JoinLobbyController controller = (JoinLobbyController) ControllerManager.getInstance().peek();
            controller.removePlayerTwoFromLobby();
             */
                removePlayerTwoFromLobby();
                ControllerManager.getInstance().pop();
            }
        });

    }

    /**
     * Adds the second player to the lobby
     */
    public void addPlayerTwoToLobby(String code, String username) {
        //TODO: Does the lobby exists?
        //TODO: Are there available space in the lobby?
        //TODO: Updates the state if Singleton runs successfully
        LobbyInfo lobbystate = (LobbyInfo) state;
        lobbystate.setPlayerTwo(code, username);
        // Updates firebase
        SingletonAPI.getInstance().addPlayerTwoToLobby(code, username);
        // Adds player one locally from firebase
        SingletonAPI.getInstance().createPlayerOneListener(code);
    }

    /**
     * Removes the second player from the lobby.
     */
    public void removePlayerTwoFromLobby() {
        LobbyInfo lobbystate = (LobbyInfo) state;
        SingletonAPI.getInstance().removePlayerTwoFromLobby(lobbystate.getCode());
    }


    @Override
    public void handleInput() {

    }

    @Override
    public void resetView() {

    }
}
