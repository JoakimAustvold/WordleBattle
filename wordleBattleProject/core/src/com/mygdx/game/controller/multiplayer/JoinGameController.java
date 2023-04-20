package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfoState;
import com.mygdx.game.model.states.multiplayer.LobbyStatus;
import com.mygdx.game.view.multiplayer.JoinGameView;

public class JoinGameController extends Controller {

    public JoinGameController() {
        this.state = LobbyInfoState.getInstance();
        this.view = new JoinGameView();

        addBackButtonListener();

        final JoinGameView joinGameView = (JoinGameView) view;
        final LobbyInfoState lobbyState = (LobbyInfoState) state;

        // Adds the player to the specified lobby
        joinGameView.joinButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!joinGameView.usernameTextField.getText().equals("")) {
                    addPlayerTwoToLobby(joinGameView.inviteCodeTextField.getText(), joinGameView.usernameTextField.getText());
                } else {
                    lobbyState.setLobbyStatus(LobbyStatus.MISSINGUSERNAME);
                }
               // ControllerManager.getInstance().push(new JoinLobbyController());
            }
        });

    }


    /**
     * Adds the second player to the lobby
     */
    private void addPlayerTwoToLobby(String code, String username) {
        //TODO: Does the lobby exists?
        //TODO: Are there available space in the lobby?
        //TODO: Updates the state if Singleton runs successfully
        LobbyInfoState lobbystate = (LobbyInfoState) state;
        lobbystate.setPlayerTwo(code, username);
        // Updates firebase
        SingletonAPI.getInstance().addPlayerTwoToLobby(code, username);
        // Adds player one locally from firebase
        SingletonAPI.getInstance().createPlayerOneListener(code);
    }

    @Override
    public void resetView() {

    }

}
