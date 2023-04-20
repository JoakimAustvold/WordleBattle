package com.mygdx.game.controller;

import static com.mygdx.game.WordleBattleGame.WORD_LENGTH;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.input.KeyboardInput;
import com.mygdx.game.model.input.WordStatus;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.view.GameView;
import com.mygdx.game.view.SingleplayerGameView;

import java.util.Collection;

public class SingleplayerGameController extends GameController {

        public SingleplayerGameController() {
            super((GameView) new SingleplayerGameView());
           // this.view = new SingleplayerGameView();

            //this.state = new GameState();
            setupView();

        }

        protected void setupView() {
             super.setupView();

            // add listeners to the buttons
            gameView.addHighscore.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    // update username
                    gameState.getScore().setUsername(gameView.usernameTextField.getText());
                    // register the highscore in the firebase database
                    SingletonAPI.getInstance().submitHighscore(gameState.getScore());

                    // new game
                    ControllerManager.getInstance().pop();
                    ControllerManager.getInstance().push(new SingleplayerGameController());
                }
            });

            gameView.newGame.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    ControllerManager.getInstance().pop();
                    ControllerManager.getInstance().push(new SingleplayerGameController());
                }
            });

        }
}
