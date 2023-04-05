package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.input.GuessedWord;
import com.mygdx.game.model.states.SingleplayerGameState;
import com.mygdx.game.model.states.State;

import java.util.Collection;

public class SingleplayerGameView extends View {

    private static final float WORD_POS_X_DIVISOR = 2.5f;
    private static final float WORD_DELTA_Y = 90.0f;

    private static final float buttonWidth = Gdx.graphics.getWidth() / 16f;
    private static final float buttonHeight = Gdx.graphics.getHeight() / 20f;
    private static final float buttonPadding = buttonWidth / 5f;
    private final TextButton[][] buttons = new TextButton[SingleplayerGameState.buttonValues.length][];

    private final SpriteBatch batch = new SpriteBatch();
    private final BitmapFont font = new BitmapFont();
    private final Stage stage = new Stage();

    public SingleplayerGameView() {
        font.getData().setScale(6, 6);
        font.setColor(Color.BLACK);

        Gdx.input.setInputProcessor(stage);

        setupKeyboard();
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!(state instanceof SingleplayerGameState)) {
            throw new StateException("Wrong state type! Please provide a PlayState.");
        }

        batch.begin();
        font.draw(spriteBatch, ((SingleplayerGameState) state).getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, Gdx.graphics.getHeight() - WORD_DELTA_Y);
        Collection<GuessedWord> guessedWords = ((SingleplayerGameState) state).getGuesses();
        int c = 0;
        for (GuessedWord word : guessedWords){
            font.draw(spriteBatch, word.getWord(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, (Gdx.graphics.getHeight() - 100.0f - c*WORD_DELTA_Y) - WORD_DELTA_Y);
            c++;
        }

        font.draw(batch, ((SingleplayerGameState) state).getKeyboardInput().getCurrentText(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, Gdx.graphics.getHeight() / 1.2f);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public TextButton[][] getButtons() {
        return buttons;
    }


    private TextButton.TextButtonStyle getButtonStyle() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        style.fontColor = Color.BLACK;
        return style;
    }

    private TextButton.TextButtonStyle getButtonStyle(TextButton button) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        if(!button.isDisabled())
            style.fontColor = Color.BLACK;
        else
            style.fontColor = Color.GRAY;

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
