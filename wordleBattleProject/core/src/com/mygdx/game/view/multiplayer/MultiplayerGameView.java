package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.GameStatus;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.GameView;

public class MultiplayerGameView extends GameView {

    public TextButton rematchButton;
    public TextButton leaveButton;

    public MultiplayerGameView() {
        super();

        rematchButton = new TextButton("Rematch opponent", skin);
        leaveButton = new TextButton("Leave lobby", skin);
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(keyboardStage);

        rematchButton.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100), 300);
        //rematchButton.setSize((float) (Gdx.graphics.getWidth()*0.6), (float) (Gdx.graphics.getHeight() * 0.05));

        leaveButton.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100), 200);
        leaveButton.setSize((float) (Gdx.graphics.getWidth()*0.6), (float) (Gdx.graphics.getHeight() * 0.05));

        endgameStage.addActor(rematchButton);
        endgameStage.addActor(leaveButton);
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        super.render(state, spriteBatch);

        final GameState gameState = (GameState) state;

        if (!(state instanceof GameState)) {
            throw new StateException("Wrong state type! Please provide a PlayState.");
        }

        if(gameState.getGameStatus().equals(GameStatus.WIN)){

        }
    }

    /**
     * End of game graphics for when the player has won
     */
    @Override
    protected void displayWinnerGraphics(SpriteBatch spriteBatch, GameState gameState) {
        font.draw(spriteBatch, "Correct guess", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
        font.draw(spriteBatch, "Your score is: " + gameState.getScore().getHighscore(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 700);
        // display the oponents score and who won
    }

    /**
     * End of game graphics for when the player has lost
     */
    @Override
    protected void displayLoserGraphics(SpriteBatch spriteBatch, GameState gameState) {
        font.draw(spriteBatch, "Out of guesses!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
        font.draw(spriteBatch, "The word was:", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 650);
        font.draw(spriteBatch, gameState.getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, 550);
        // isplay the oponents score and who won
    }
}
