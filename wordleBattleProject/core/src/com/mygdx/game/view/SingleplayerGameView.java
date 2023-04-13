package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.GameStatus;
import com.mygdx.game.model.input.GuessedLetter;
import com.mygdx.game.model.input.GuessedLetterStatus;
import com.mygdx.game.model.input.GuessedWord;
import com.mygdx.game.model.states.SingleplayerGameState;
import com.mygdx.game.model.states.State;

import java.util.Collection;

public class SingleplayerGameView extends View {

    private static final float WORD_POS_X_DIVISOR = 2.5f;
    private static final float WORD_DELTA_Y = 90.0f;

    private static final Color COLOR_KEY_ENABLED = Color.WHITE;
    private static final Color COLOR_KEY_DISABLED = Color.GRAY;

    private static final float buttonWidth = Gdx.graphics.getWidth() / 16f;
    private static final float buttonHeight = Gdx.graphics.getHeight() / 20f;
    private static final float buttonPadding = buttonWidth / 5f;
    private final TextButton[][] buttons = new TextButton[SingleplayerGameState.buttonValues.length][];

    private final SpriteBatch batch = new SpriteBatch();
    private final BitmapFont font = new BitmapFont();
    private final Stage stage = new Stage();

    public SingleplayerGameView() {
        font.getData().setScale(6, 6);
        font.setColor(COLOR_KEY_ENABLED);

        Gdx.input.setInputProcessor(stage);

        setupKeyboard();
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        SingleplayerGameState gameState = (SingleplayerGameState) state;

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!(state instanceof SingleplayerGameState)) {
            throw new StateException("Wrong state type! Please provide a PlayState.");
        }
        // Draw solution word
        font.draw(spriteBatch, gameState.getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, Gdx.graphics.getHeight() - WORD_DELTA_Y);
        Collection<GuessedWord> guessedWords = gameState.getGuesses();

        // Draw guessed words
        int c = 0;
        for (GuessedWord word : guessedWords){
            float height = (Gdx.graphics.getHeight() - 100.0f - c*WORD_DELTA_Y) - WORD_DELTA_Y*2;
            font.getData().markupEnabled = true;
            String wordToDraw = createColoredWord(word);
            font.draw(spriteBatch, wordToDraw, Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, height);
            c++;
        }

        if(gameState.getGameStatus().equals(GameStatus.ONGOING)){
            // Draw keyboardinput
            font.draw(
                    spriteBatch, ((SingleplayerGameState) state).getKeyboardInput().getCurrentText(),
                    Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR,
                    (Gdx.graphics.getHeight() - 100.0f - (c+1)*WORD_DELTA_Y) - WORD_DELTA_Y
            );
            // Draw keyboard
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }

        if(gameState.getGameStatus().equals(GameStatus.WIN)){
            font.draw(spriteBatch, "You win!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
            font.draw(spriteBatch, "The word was:", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 700);
            font.draw(spriteBatch, gameState.getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, 600);
        }
        else if(gameState.getGameStatus().equals(GameStatus.LOSS)){
            font.draw(spriteBatch, "Out of guesses!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
            font.draw(spriteBatch, "The word was:", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 650);
            font.draw(spriteBatch, gameState.getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, 550);
        }

    }

    @Override
    public void setup() {

    }

    @Override
    public void dispose() {

    }

    public TextButton[][] getButtons() {
        return buttons;
    }

    public void updateKeyboardStyle(){
        for (TextButton[] rowButtons : buttons) {
            for (TextButton button : rowButtons) {
                button.setStyle(getButtonStyle(button));
            }
        }
    }

    /**
     * Create colored word based on letter-state
     * @param guessedWord to color letters for
     * @return string with libgdx markup to color letters.
     */
    private String createColoredWord(GuessedWord guessedWord){
        String word = "";
        for(GuessedLetter letter : guessedWord.getLetters()){
            word += getColorFromLetter(letter) + letter.getLetter();
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

    private TextButton.TextButtonStyle getButtonStyle(TextButton button) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        if(!button.isDisabled())
            style.fontColor = COLOR_KEY_ENABLED;
        else
            style.fontColor = COLOR_KEY_DISABLED;

        return style;
    }

    private void setupKeyboard(){
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

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
}
