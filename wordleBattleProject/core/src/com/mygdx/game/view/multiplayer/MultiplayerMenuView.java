package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.multiplayer.HostGameController;
import com.mygdx.game.controller.multiplayer.JoinGameController;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;

public class MultiplayerMenuView extends View {

    //  private Stage stage;
      //private Skin skin;

       public MultiplayerMenuView() {
           super();
         // this.stage = new Stage(new ScreenViewport());
          //this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
          setup();
      }

       public void setup() {
             Gdx.input.setInputProcessor(stage);

             Table table = new Table();
             table.setFillParent(true);
             table.setDebug(true);
             stage.addActor(table);

             TextButton hostGameButton = new TextButton("Host Game", skin);
             TextButton joinGameButton = new TextButton("Join Game", skin);
            // TextButton backButton = new TextButton("Back", skin);

            /*
             hostGameButton.setTransform(true);
             hostGameButton.setScale(4);
             joinGameButton.setTransform(true);
             joinGameButton.setScale(4);
             */
            // backButton.setTransform(true);
              //backButton.setScale(4);

             // Add the buttons to the table
             table.row().pad(50, 0, 50, 0);
             table.add(hostGameButton).fill().uniform();
             table.row().pad(50, 0, 50, 0);
             table.add(joinGameButton).fill().uniform();
            // table.row().pad(50, 0, 50, 0);
             //table.add(backButton).fill().uniform();

           hostGameButton.addListener(new ChangeListener() {
                 @Override
                 public void changed(ChangeEvent event, Actor actor) {
                       ControllerManager.getInstance().push(new HostGameController());
                 }
             });

           joinGameButton.addListener(new ChangeListener() {
                       @Override
                       public void changed(ChangeEvent event, Actor actor) {
                           ControllerManager.getInstance().push(new JoinGameController());
                       }
                   });

             /*
             backButton.addListener(new ChangeListener() {
                       @Override
                       public void changed(ChangeEvent event, Actor actor) {
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
    */


    /*
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    */


}
