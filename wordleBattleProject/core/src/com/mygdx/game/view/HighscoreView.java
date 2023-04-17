package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.HighscoreController;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.highscore.HighscoreList;
import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.states.State;

import java.util.List;

public class HighscoreView extends View{

    private Table table;

    public HighscoreView() {
        super();
        setup();
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(stage);
        createBackButtonWithDefaultListener();

        Label titleLabel = new Label("Highscores", skin);
        titleLabel.setFontScale(5, 5);
        stage.addActor(titleLabel);
        titleLabel.setPosition((float) (Gdx.graphics.getWidth() * 0.5 - titleLabel.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.8));
    }


    @Override
    public void render(State state, SpriteBatch spriteBatch) {

        // subclass specific code
        HighscoreList highscoreState = (HighscoreList) state;
        // display highscores
        List<Score> highscores = highscoreState.getLocalHighscores();

        table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);


        for(int i = 0; i < highscores.size(); i++) {
             Score score = highscores.get(i);
             Label scoreLabel = new Label(score.getUsername() + ":  " + score.getHighscore() , skin);
             scoreLabel.setFontScale(4, 4);

             table.row().pad(50, 0, 50, 0);
             table.add(scoreLabel);
        }
         //TODO: how to use supers render method while overriding it
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }



    /*
    @Override
    public void dispose() {

    }
    */
}
