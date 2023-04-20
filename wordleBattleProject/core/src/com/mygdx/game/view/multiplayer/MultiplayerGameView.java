package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.GameStatus;
import com.mygdx.game.model.states.GameState;
import com.mygdx.game.model.states.State;
import com.mygdx.game.model.states.multiplayer.CurrentPlayer;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.GameView;

public class MultiplayerGameView extends GameView {

    public TextButton backToLobbyButton;
    private boolean first = true;

    public MultiplayerGameView() {
        super();

        backToLobbyButton = new TextButton("Leave lobby", skin);
    }

    @Override
    public void setup() {
        Gdx.input.setInputProcessor(keyboardStage);

        backToLobbyButton.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100), 200);
        backToLobbyButton.setSize((float) (Gdx.graphics.getWidth()*0.6), (float) (Gdx.graphics.getHeight() * 0.05));

    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        super.render(state, spriteBatch);
    }

    /**
     * End of game graphics for when the player has won
     */
    @Override
    protected void displayWinnerGraphics(SpriteBatch spriteBatch, GameState gameState) {
        font.draw(spriteBatch, "Correct guess", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
        font.draw(spriteBatch, "Your score is: " + gameState.getScore().getHighscore(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 700);
        // display the opponents score and who won
        displayOpponentsScore(spriteBatch, gameState);

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
        displayOpponentsScore(spriteBatch, gameState);
    }


    private void displayOpponentsScore(SpriteBatch spriteBatch, GameState gameState) {
        if (LobbyInfo.getInstance().getCurrentPlayer() == CurrentPlayer.PLAYERONE) {
            if (LobbyInfo.getInstance().getPlayerTwoScore() != null) {
                font.draw(spriteBatch, "Opponent score is: " + LobbyInfo.getInstance().getPlayerTwoScore(), 20, 600);
                displayEndgameButtons();
            } else {
                font.draw(spriteBatch, "Waiting on opponent", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 600);
            }
        } else if (LobbyInfo.getInstance().getCurrentPlayer() == CurrentPlayer.PLAYERTWO){
            if (LobbyInfo.getInstance().getPlayerOneScore() != null) {
                font.draw(spriteBatch, "Opponent score is: " + LobbyInfo.getInstance().getPlayerOneScore(), 20, 600);
                displayEndgameButtons();
            } else {
                font.draw(spriteBatch, "Waiting on opponent", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 600);
            }
        }
    }

    private void displayEndgameButtons() {
        if (first) {
            first = false;
            endgameStage.addActor(backToLobbyButton);
        }
    }
}
