package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.multiplayer.HostLobbyController;
import com.mygdx.game.model.multiplayer.LobbyCode;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;

/**
 * The view a user will see when starting a hosted game.
 */
public class HostGameView extends View {

    /*
    private Stage stage;
    private Skin skin;
    */

    public HostGameView() {
       // this.stage = new Stage(new ScreenViewport());
       // this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
       // this.skin.getFont("default-font").getData().setScale(4f,4f);
        setup();
    }

     public void setup() {
        Gdx.input.setInputProcessor(stage);

        final TextField usernameTextField = new TextField("", skin);
        TextButton createLobbyButton = new TextButton("Create lobby", skin);
        //TextButton backButton = new TextButton("Back", skin);

        stage.addActor(usernameTextField);
        stage.addActor(createLobbyButton);
       // stage.addActor(backButton);

        usernameTextField.setPosition((float) (Gdx.graphics.getWidth()*0.5-Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.4));
        usernameTextField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        usernameTextField.setMessageText("Username: ");

        createLobbyButton.setPosition((float) (Gdx.graphics.getWidth() *0.5 -Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight()*0.1));
        createLobbyButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        //backButton.setPosition(50, (float) (Gdx.graphics.getHeight() * 0.90));
        //backButton.setSize((float) (Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.05));

        createLobbyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(usernameTextField.getText());
                HostLobbyController hostLobbyController = new HostLobbyController();
                hostLobbyController.createLobby(usernameTextField.getText());
                ControllerManager.getInstance().push(hostLobbyController);
               // ControllerManager.getInstance().push(new HostLobbyController(usernameTextField.getText()));

            }
        });

        /*
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                ControllerManager.getInstance().pop();
            }
        });
        */

        createBackButtonWithDefaultListener();
    }

    /*
    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    */


}
