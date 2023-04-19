package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.GameStatus;
import com.mygdx.game.model.input.GuessedLetter;
import com.mygdx.game.model.input.GuessedLetterStatus;
import com.mygdx.game.model.input.GuessedWord;
import com.mygdx.game.model.states.SingleplayerGameState;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.letters.LetterMap;


import java.util.Collection;

public class SingleplayerGameView extends View {

    public TextButton pauseButton;
    protected static final float WORD_POS_X_DIVISOR = 2.5f;
    protected static final float WORD_DELTA_Y = 150.0f;

    protected final Texture texture = new Texture(Gdx.files.internal("textures/letters/a.png"));

    protected static final Color COLOR_KEY_ENABLED = Color.WHITE;
    protected static final Color COLOR_KEY_DISABLED = Color.GRAY;

    protected static final float buttonWidth = Gdx.graphics.getWidth() / 16f;
    protected static final float buttonHeight = Gdx.graphics.getHeight() / 20f;
    protected static final float buttonPadding = buttonWidth / 5f;
    protected final TextButton[][] buttons = new TextButton[SingleplayerGameState.buttonValues.length][];

    protected Texture graySquareTexture;
    protected Texture orangeSquareTexture;
    protected Texture greenSquareTexture;
    private LetterMap letterMap;

    private final BitmapFont font = new BitmapFont();
    private final Stage keyboardStage = new Stage();
    private final Stage endgameStage = new Stage();

    public TextField usernameTextField;
    public TextButton addHighscore;
    public TextButton newGame;


    public SingleplayerGameView() {
        super();
        font.getData().setScale(6, 6);
        font.setColor(COLOR_KEY_ENABLED);

        Gdx.input.setInputProcessor(keyboardStage);
        // all buttons
        usernameTextField = new TextField("", skin);
        addHighscore = new TextButton("Add highscore", skin);
        newGame = new TextButton("New Game", skin);

        graySquareTexture = new Texture(Gdx.files.internal("textures/backgrounds/gray.png"));
        orangeSquareTexture= new Texture(Gdx.files.internal("textures/backgrounds/orange.png"));
        greenSquareTexture = new Texture(Gdx.files.internal("textures/backgrounds/green.png"));
        pauseButton = new TextButton("Options", skin);
        letterMap = new LetterMap();
        setupPauseButton();
        setupKeyboard();
        setup();
    }

    @Override
    public void setup() {
        usernameTextField.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 300), 400);
        usernameTextField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        usernameTextField.setMessageText("Username: ");

        addHighscore.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR + 100), 400);
        addHighscore.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        newGame.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100), 100);
        newGame.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        endgameStage.addActor(usernameTextField);
        endgameStage.addActor(addHighscore);
        endgameStage.addActor(newGame);
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        final SingleplayerGameState gameState = (SingleplayerGameState) state;

        if (!(state instanceof SingleplayerGameState)) {
            throw new StateException("Wrong state type! Please provide a PlayState.");
        }

        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );


        // TODO: Remove the solution
        // Draw solution word
        font.draw(spriteBatch, gameState.getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, Gdx.graphics.getHeight() - WORD_DELTA_Y);
        Collection<GuessedWord> guessedWords = gameState.getGuesses();

        // Draw guessed words
        int c = 0;
        for (GuessedWord word : guessedWords){
            String[] colourToDraw = createColoredList(word);
            for (int i = 0; i < word.getWord().length(); i++) {
                switch(colourToDraw[i]) {
                    case "[GRAY]":
                        spriteBatch.draw(graySquareTexture,
                                (Gdx.graphics.getWidth() / (WORD_POS_X_DIVISOR + 1.5f)) + (i * 150), (Gdx.graphics.getHeight() - 110.0f - (c+1)*WORD_DELTA_Y) - WORD_DELTA_Y);
                        break;
                    case "[ORANGE]":
                        spriteBatch.draw(orangeSquareTexture,
                                (Gdx.graphics.getWidth() / (WORD_POS_X_DIVISOR + 1.5f)) + (i * 150), (Gdx.graphics.getHeight() - 110.0f - (c+1)*WORD_DELTA_Y) - WORD_DELTA_Y);
                        break;
                    case "[GREEN]":
                        spriteBatch.draw(greenSquareTexture,
                                (Gdx.graphics.getWidth() / (WORD_POS_X_DIVISOR + 1.5f)) + (i * 150), (Gdx.graphics.getHeight() - 110.0f - (c+1)*WORD_DELTA_Y) - WORD_DELTA_Y);
                        break;
                }
                spriteBatch.draw(letterMap.getTexture(word.getWord().charAt(i)+""),
                        (Gdx.graphics.getWidth() / (WORD_POS_X_DIVISOR + 1.5f)) + (i * 150), (Gdx.graphics.getHeight() - 110.0f - (c+1)*WORD_DELTA_Y) - WORD_DELTA_Y);
            }
            c++;
        }

        if(gameState.getGameStatus().equals(GameStatus.ONGOING)){
            String currentText = ((SingleplayerGameState) state).getKeyboardInput().getCurrentText();
            for (int i = 0; i < currentText.length(); i++) {
                spriteBatch.draw(letterMap.getTexture(currentText.charAt(i)+""),
                        (Gdx.graphics.getWidth() / (WORD_POS_X_DIVISOR + 1.5f)) + (i * 150), (Gdx.graphics.getHeight() - 110.0f - (c+1)*WORD_DELTA_Y) - WORD_DELTA_Y);
            }

            // Draw keyboard
            keyboardStage.act(Gdx.graphics.getDeltaTime());
            keyboardStage.draw();
        }

        if(gameState.getGameStatus().equals(GameStatus.WIN)){
            font.draw(spriteBatch, "You win!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
            // your score
            font.draw(spriteBatch, "Your score is: " + gameState.getScore().getHighscore(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 700);

            // display input field for username + add highscore button + new game button
            if (Gdx.input.getInputProcessor() != endgameStage) {
                Gdx.input.setInputProcessor(endgameStage);
            }
            endgameStage.act(Gdx.graphics.getDeltaTime());
            endgameStage.draw();
        }
        else if(gameState.getGameStatus().equals(GameStatus.LOSS)){
            font.draw(spriteBatch, "Out of guesses!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
            font.draw(spriteBatch, "The word was:", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 650);
            font.draw(spriteBatch, gameState.getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, 550);


            // display a new game-button
            addHighscore.remove();
            usernameTextField.remove();

            if (Gdx.input.getInputProcessor() != endgameStage) {
                Gdx.input.setInputProcessor(endgameStage);
            }
            endgameStage.act(Gdx.graphics.getDeltaTime());
            endgameStage.draw();
        }


    }

    @Override
    public void dispose() {
        super.dispose();
        keyboardStage.dispose();
        endgameStage.dispose();
        font.dispose();
    }

    public TextButton[][] getButtons() {
        return buttons;
    }

    public void updateKeyboardStyle(Collection<Character> disabledLetters){
        for (TextButton[] rowButtons : buttons) {
            for (TextButton button : rowButtons) {
                Character buttonChar =  new Character(button.getText().toString().charAt(0)) ;
                if(disabledLetters.contains(buttonChar)){
                    //System.out.println("disabling");
                    button.setStyle(getButtonGuessedStyle());
                }
            }
        }
    }

    /**
     * Create colored word based on letter-state
     * @param guessedWord to color letters for
     * @return string with libgdx markup to color letters.
     */
    private String[] createColoredList(GuessedWord guessedWord){
        String[] word = {"", "", "", "", ""};
        int i = 0;
        for(GuessedLetter letter : guessedWord.getLetters()){
            word[i] = getColorFromLetter(letter);
            i++;
        }
        return word;
    }

    private String getColorFromLetter(GuessedLetter guessedLetter){
        GuessedLetterStatus status =  guessedLetter.getStatus();
        if(status.equals(GuessedLetterStatus.INCORRECT)){
            return "[GRAY]";
        }
        if (status.equals(GuessedLetterStatus.CORRECT)){
                return "[GREEN]";
        }
        if(status.equals(GuessedLetterStatus.WRONG_POS)) {
            return "[ORANGE]";
        }
        return "";
    }

    private TextButton.TextButtonStyle getButtonStyle() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        style.fontColor = COLOR_KEY_ENABLED;
        return style;
    }

    private TextButton.TextButtonStyle getButtonGuessedStyle() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;

        style.fontColor = COLOR_KEY_DISABLED;
        return style;
    }

    protected void setupKeyboard(){
        Table table = new Table();
        table.setFillParent(true);
        keyboardStage.addActor(table);

        int rowCounter = 0;
        for (String [] buttonValueRow :  SingleplayerGameState.buttonValues) {
            TextButton[] rowButtons = new TextButton[buttonValueRow.length];

            int letterCounter = 0;
            for (String buttonValue : buttonValueRow) {
                TextButton button = new TextButton(buttonValue, getButtonStyle());
                button.getLabel().setFontScale(buttonWidth / 20f);
                rowButtons[letterCounter] = button;
                letterCounter++;
            }
            buttons[rowCounter] = rowButtons;
            rowCounter++;
        }

        for (TextButton[] rowButtons : buttons) {
            for (TextButton button : rowButtons) {
                table.add(button).size(buttonWidth, buttonHeight).pad(buttonPadding);
            }
            table.row();
        }
        table.setY(-Gdx.graphics.getHeight()/3.5f);
    }

    private void setupPauseButton() {
        pauseButton.setPosition(50, (float) (Gdx.graphics.getHeight() * 0.90));
        keyboardStage.addActor(pauseButton);
    }
}
