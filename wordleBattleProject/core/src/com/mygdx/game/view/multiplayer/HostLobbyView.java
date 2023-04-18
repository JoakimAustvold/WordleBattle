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

    public TextButton startGameButton;

    public HostLobbyView() {
        super();
        startGameButton = new TextButton("Start game", skin);
        createButton();
        setup();
    }

    @Override
    public void setup() {
        super.setup();
        Gdx.input.setInputProcessor(stage);
    }

    public void createButton() {
        stage.addActor(startGameButton);

        startGameButton.setPosition((float) (Gdx.graphics.getWidth() *0.5 -Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight()*0.1));
        startGameButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        //backButton.removeListener(listener);
    }

}
