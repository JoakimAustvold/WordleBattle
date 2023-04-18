package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.JoinGameView;

public class JoinGameController extends Controller {

    public JoinGameController() {
        this.state = LobbyInfo.getInstance();
        this.view = new JoinGameView();

        addBackButtonListener();

        final JoinGameView joinGameView = (JoinGameView) view;

        joinGameView.joinButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
           // System.out.println(inviteCodeTextField.getText());
            //System.out.println(usernameTextField.getText());

            /*
            JoinGameController controller = (JoinGameController) ControllerManager.getInstance().peek();
            controller.addPlayerTwoToLobby(Integer.parseInt(inviteCodeTextField.getText()), usernameTextField.getText());
            */

            JoinLobbyController controller = new JoinLobbyController();
            controller.addPlayerTwoToLobby(joinGameView.inviteCodeTextField.getText(), joinGameView.usernameTextField.getText());
            ControllerManager.getInstance().push(controller);
            }
        });

    }
    
    @Override
    public void handleInput() {

    }

    @Override
    public void resetView() {

    }

}
