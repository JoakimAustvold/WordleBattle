package com.mygdx.game.controller;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.view.SingleplayerGameView;


public class SingleplayerGameController extends GameController {

        public SingleplayerGameController() {
            super(new SingleplayerGameView());
            setupView();
        }

        @Override
        protected void setupView() {
             super.setupView();
             addPauseButtonListener();

             final SingleplayerGameView singleplayerGameView = (SingleplayerGameView) view;

            // add listeners to the buttons
            singleplayerGameView.addHighscore.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    // update username
                    gameState.getScore().setUsername(singleplayerGameView.usernameTextField.getText());
                    // register the highscore in the firebase database
                    SingletonAPI.getInstance().submitHighscore(gameState.getScore());

                    // new game
                    ControllerManager.getInstance().pop();
                    ControllerManager.getInstance().push(new SingleplayerGameController());
                }
            });

            singleplayerGameView.newGameButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                    ControllerManager.getInstance().pop();
                    ControllerManager.getInstance().push(new SingleplayerGameController());
                }
            });

        }
}
