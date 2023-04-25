package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.GameStatus;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.letters.LetterMap;

import java.util.Collection;

public class SingleplayerGameView extends GameView {
    public TextField usernameTextField;
    public TextButton addHighscore;
    public TextButton newGameButton;


    public SingleplayerGameView() {
        super();

        // all buttons
        usernameTextField = new TextField("", skin);
        newGameButton = new TextButton("New Game", skin);
        addHighscore = new TextButton("Add Score", skin);
        setupPauseButton();
        setup();
    }


    @Override
    public void setup() {
        Gdx.input.setInputProcessor(keyboardStage);

        //highscore setup
        usernameTextField.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 350), 300);
        usernameTextField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        usernameTextField.setMessageText("Username");

        addHighscore.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR + 150), 300);

        newGameButton.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100), 100);

        endgameStage.addActor(usernameTextField);
        endgameStage.addActor(addHighscore);
        endgameStage.addActor(newGameButton);
    }


    /**
     * End of game graphics for when the player has won
     */
    @Override
    protected void displayWinnerGraphics(SpriteBatch spriteBatch, GameState gameState) {
        font.draw(spriteBatch, "You win!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
        // your score
        font.draw(spriteBatch, "Your score is: " + gameState.getScore().getHighscore(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 300, 700);
        //font.getData().setScale(4);
        font.draw(spriteBatch, "Add your highscore below!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 400, 600);
    }

    /**
     * End of game graphics for when the player has lost
     */
    @Override
    protected void displayLoserGraphics(SpriteBatch spriteBatch, GameState gameState) {
        addHighscore.remove();
        usernameTextField.remove();

        font.draw(spriteBatch, "Out of guesses!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
        font.draw(spriteBatch, "The word was:", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 650);
        font.draw(spriteBatch, gameState.getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, 550);
    }


}
