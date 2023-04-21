package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.GameStatus;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.State;

public class SingleplayerGameView extends GameView {

    public TextField usernameTextField;
    public TextButton addHighscoreButton;
    public TextButton newGameButton;


    public SingleplayerGameView() {
        super();

        newGameButton = new TextButton("New Game", skin);
        usernameTextField = new TextField("", skin);
        addHighscoreButton = new TextButton("Add highscore", skin);
    }


    @Override
    public void setup() {
        Gdx.input.setInputProcessor(keyboardStage);

        //highscore setup
        usernameTextField.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 300), 400);
        usernameTextField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        usernameTextField.setMessageText("Username: ");

        addHighscoreButton.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR + 100), 400);
        addHighscoreButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        newGameButton.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100), 100);
        newGameButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        endgameStage.addActor(usernameTextField);
        endgameStage.addActor(addHighscoreButton);
        endgameStage.addActor(newGameButton);
    }


    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        final GameState gameState = (GameState) state;

        if (!(state instanceof GameState)) {
            throw new StateException("Wrong state type! Please provide a PlayState.");
        }

        if(gameState.getGameStatus().equals(GameStatus.WIN)) {
            font.draw(spriteBatch, "You win!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
            // your score
            font.draw(spriteBatch, "Your score is: " + gameState.getScore().getHighscore(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 700);
        }

        if (gameState.getGameStatus().equals(GameStatus.LOSS)) {
            // display a new game-button
            addHighscoreButton.remove();
            usernameTextField.remove();
        }


        super.render(state, spriteBatch);

    }


    /**
     * End of game graphics for when the player has won
     */
    @Override
    protected void displayWinnerGraphics(SpriteBatch spriteBatch, GameState gameState) {
        font.draw(spriteBatch, "You win!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
        // your score
        font.draw(spriteBatch, "Your score is: " + gameState.getScore().getHighscore(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 700);

    }

    /**
     * End of game graphics for when the player has lost
     */
    @Override
    protected void displayLoserGraphics(SpriteBatch spriteBatch, GameState gameState) {
        font.draw(spriteBatch, "Out of guesses!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
        font.draw(spriteBatch, "The word was:", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 650);
        font.draw(spriteBatch, gameState.getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, 550);
    }
}
