package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.multiplayer.JoinGameView;
import com.mygdx.game.view.multiplayer.LobbyView;

public class JoinGameController extends Controller {

    public JoinGameController() {
        this.state = LobbyInfo.getInstance();
        this.view = new JoinGameView();

        final JoinGameView joinGameView = ((JoinGameView) view);

        joinGameView.joinButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                // TODO: TryCatch did not work. it does not catch. Try something different
                try {
                    // update firebase with palyer two
                    SingletonAPI.getInstance().addPlayerTwoToLobby(joinGameView.inviteCodeTextField.getText(), joinGameView.usernameTextField.getText());
                    // set local player two
                    LobbyInfo.getInstance().setPlayerTwo(joinGameView.inviteCodeTextField.getText(), joinGameView.usernameTextField.getText());
                    // Adds player one locally from firebase
                    SingletonAPI.getInstance().createPlayerOneListener(joinGameView.inviteCodeTextField.getText());
                    // change screen
                    ControllerManager.getInstance().push(new JoinLobbyController());
                } catch (IllegalStateException exception) {
                   System.out.println("Do nothing. The lobby is occupied");
                } catch (IllegalArgumentException exception) {
                    System.out.println("Do nothing. The lobby does not exists");
                }
                /*
                JoinGameController controller = (JoinGameController) ControllerManager.getInstance().peek();
                controller.addPlayerTwoToLobby(Integer.parseInt(inviteCodeTextField.getText()), usernameTextField.getText());


                JoinLobbyController controller = new JoinLobbyController();
                controller.addPlayerTwoToLobby(inviteCodeTextField.getText(), usernameTextField.getText());
                ControllerManager.getInstance().push(controller);
                */

            }
        });

        joinGameView.backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
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
