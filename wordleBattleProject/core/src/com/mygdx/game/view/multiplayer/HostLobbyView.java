package com.mygdx.game.view.multiplayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.multiplayer.HostLobbyController;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.multiplayer.LobbyCode;
import com.mygdx.game.model.states.HostLobbyState;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;


public class HostLobbyView extends View {

    private Stage stage;
    private Skin skin;
    private BitmapFont font;
    // private LobbyCode lobbyCode;

    public HostLobbyView() {
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        font = new BitmapFont();
        setup();
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(stage);
        //lobbyCode = new LobbyCode();
        //SingletonAPI.getInstance().createLobby(lobbyCode);

        TextButton startGameButton = new TextButton("Start game", skin);
        TextButton backButton = new TextButton("Back", skin);

        startGameButton.setPosition((float) (Gdx.graphics.getWidth() *0.5 -Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight()*0.1));
        startGameButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        backButton.setPosition(50, (float) (Gdx.graphics.getHeight() * 0.90));
        backButton.setSize((float) (Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.05));

        startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                //ControllerManager.getInstance().push(new HostLobbyController());

            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {

                ControllerManager.getInstance().pop();
            }
        });
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
         HostLobbyState lobbyState = (HostLobbyState) state;

         if (!(state instanceof HostLobbyState)) {
             throw new StateException("Wrong state type! Please provide a PlayState.");
         }

         font.getData().setScale(6);
         font.draw(spriteBatch, String.valueOf(lobbyState.getCode()), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

    }

    @Override
    public void dispose() {
        font.dispose();
        skin.dispose();
        stage.dispose();
    }


}
