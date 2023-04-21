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

    //public TextButton pauseButton;
    //protected static final float WORD_POS_X_DIVISOR = 2.5f;
    //protected static final float WORD_DELTA_Y = 150.0f;

    /*
    protected static final Color COLOR_KEY_ENABLED = Color.WHITE;
    protected static final Color COLOR_KEY_DISABLED = Color.GRAY;

    /*
    protected static final float buttonWidth = Gdx.graphics.getWidth() / 16f;
    protected static final float buttonHeight = Gdx.graphics.getHeight() / 20f;
    protected static final float buttonPadding = buttonWidth / 5f;
    protected final TextButton[][] buttons = new TextButton[SingleplayerGameState.buttonValues.length][];

     */

    /*
    protected Texture graySquareTexture;
    protected Texture orangeSquareTexture;
    protected Texture greenSquareTexture;
    private LetterMap letterMap;

     */


    public TextField usernameTextField;
    public TextButton addHighscoreButton;
    public TextButton newGameButton;


    public SingleplayerGameView() {
        super();

        // all buttons
        usernameTextField = new TextField("Name", skin);
        //addHighscore = new TextButton("Submit", skin);
        //newGame = new TextButton("New game", skin);

        /*
        graySquareTexture = new Texture(Gdx.files.internal("textures/backgrounds/gray.png"));
        orangeSquareTexture= new Texture(Gdx.files.internal("textures/backgrounds/orange.png"));
        greenSquareTexture = new Texture(Gdx.files.internal("textures/backgrounds/green.png"));
        pauseButton = new TextButton("Options", skin);
        letterMap = new LetterMap();

        setupPauseButton();
        setupKeyboard();

         */

        newGameButton = new TextButton("New Game", skin);
        //usernameTextField = new TextField("", skin);
        addHighscoreButton = new TextButton("Add highscore", skin);
        setup();

    }


    @Override
    public void setup() {
        Gdx.input.setInputProcessor(keyboardStage);

        //highscore setup
        usernameTextField.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 300), 400);
        usernameTextField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        usernameTextField.setMessageText("Username: ");

        //addHighscore.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR + 300), 400);
        //addHighscore.setSize((float) (Gdx.graphics.getWidth()*0.3), (float) (Gdx.graphics.getHeight() * 0.05));

        addHighscoreButton.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR + 100), 400);
        addHighscoreButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        newGameButton.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100), 100);
        newGameButton.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        endgameStage.addActor(usernameTextField);
        endgameStage.addActor(addHighscoreButton);
        endgameStage.addActor(newGameButton);
    }

    /*
    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        final GameState gameState = (GameState) state;

        if (!(state instanceof GameState)) {
            throw new StateException("Wrong state type! Please provide a PlayState.");
        }

        /*
        if(gameState.getGameStatus().equals(GameStatus.WIN)) {
            font.draw(spriteBatch, "You win!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
            // your score
            font.draw(spriteBatch, "Your score is: " + gameState.getScore().getHighscore(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 700);
        }
        */

        /*
        if (gameState.getGameStatus().equals(GameStatus.LOSS)) {
            // display a new game-button
            addHighscoreButton.remove();
            usernameTextField.remove();
        }



        super.render(state, spriteBatch);
    }

         */



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
        addHighscoreButton.remove();
        usernameTextField.remove();

        font.draw(spriteBatch, "Out of guesses!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
        font.draw(spriteBatch, "The word was:", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 650);
        font.draw(spriteBatch, gameState.getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, 550);
    }
}
