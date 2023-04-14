package com.mygdx.game.view.multiplayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.multiplayer.HostLobbyController;

/**
 * The view the player hosting a lobby sees. They will see the user joining the lobby and be able to
 * start the game.
 */
public class HostLobbyView extends LobbyView {

    // private LobbyCode lobbyCode;

    public HostLobbyView() {
        super();
        createButton();
    }

    public void createButton() {        
        Gdx.input.setInputProcessor(stage);

        TextButton startGameButton = new TextButton("Start game", skin);

        stage.addActor(startGameButton);

        startGameButton.setPosition((float) (Gdx.graphics.getWidth() *0.5 -Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight()*0.1));
        startGameButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO: Add a multiplayer game screen

                //ControllerManager.getInstance().push(new HostLobbyController());

            }
        });

        backButton.removeListener(listener);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Vi er i HostLobbyView");
                //SingletonAPI.getInstance().removeLobby(LobbyInfo.getInstance().getCode());

                // TODO: Better way to write this? Without Peeking
                HostLobbyController controller = (HostLobbyController) ControllerManager.getInstance().peek();
                controller.destroyLobby();
                ControllerManager.getInstance().pop();

                //ControllerManager.getInstance().push(new HostLobbyController());
            }
        });
    }

}
