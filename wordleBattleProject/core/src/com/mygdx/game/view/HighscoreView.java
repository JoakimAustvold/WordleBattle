package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.model.highscore.HighscoreList;
import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.states.State;

import java.util.List;

/**
 * Shows a list of all the scores in descending order
 * You can scroll up and down with the help of a scroll pane
 * Contains a back button
 */public class HighscoreView extends View{

    private Table table;
    private Table container;
    final private ScrollPane scrollPane;

    public HighscoreView() {
        super();

        table = new Table();
        container = new Table();
        container.setFillParent(true);
        stage.addActor(container);

        // Allowing scrolling through the highscore list
        scrollPane = new ScrollPane(table, skin);
        scrollPane.setScrollingDisabled(true,false); // Disables scrolling in the x-direction

        container.add(scrollPane).expand().fill();

        setup();
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(stage);
        createBackButton();
    }


    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        // The following code is placed in render because it takes time
        // for the highscore list to be updated (firebase is asynchronous)

        HighscoreList highscoreState = (HighscoreList) state;
        List<Score> highscores = highscoreState.getLocalHighscores();

        table.reset();

        // Adds the title to the page
        table.pad(100).defaults().expandX().space(4);
        Label titleLabel = new Label("Highscores", skin);
        titleLabel.setFontScale(5, 5);
        table.add(titleLabel);

        // Adds all the highscore entries to the table to be displayed
        for(int i = 0; i < highscores.size(); i++) {
             Score score = highscores.get(i);
             Label scoreLabel = new Label(i+1 + ". " +score.getUsername() + ":  " + score.getHighscore() , skin);
             scoreLabel.setFontScale(4, 4);

             table.row().pad(50, 0, 50, 0);
             table.add(scoreLabel);
        }

        super.render(state, spriteBatch);
    }
}
