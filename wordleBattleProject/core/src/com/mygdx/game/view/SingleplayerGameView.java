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
import com.mygdx.game.model.keyboard.KeyboardInput;
import com.mygdx.game.model.states.PlayState;
import com.mygdx.game.model.states.State;

public class SingleplayerGameView extends View {

    private final float buttonWidth = Gdx.graphics.getWidth() / 16f;
    private final float buttonHeight = Gdx.graphics.getHeight() / 20f;
    private final float buttonPadding = buttonWidth / 5f;


    public final TextButton[][] buttons = new TextButton[PlayState.buttonValues.length][];
    private final SpriteBatch batch = new SpriteBatch();
    private final BitmapFont font = new BitmapFont();
    public final KeyboardInput keyboardInput;
    private final Stage stage = new Stage();

    public SingleplayerGameView() {
        font.getData().setScale(6, 6);
        font.setColor(Color.BLACK);

        keyboardInput = new KeyboardInput();

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        for (int i = 0; i < PlayState.buttonValues.length; i++) {
            TextButton[] rowButtons = new TextButton[PlayState.buttonValues[i].length];
            for (int j = 0; j < PlayState.buttonValues[i].length; j++) {
                String buttonValue = PlayState.buttonValues[i][j];
                TextButton button = new TextButton(buttonValue, getButtonStyle());
                button.getLabel().setFontScale(buttonWidth / 20f);
                rowButtons[j] = button;
            }
            buttons[i] = rowButtons;
        }

        for (TextButton[] rowButtons : buttons) {
            for (TextButton button : rowButtons) {
                table.add(button).size(buttonWidth, buttonHeight).pad(buttonPadding);
            }
            table.row();
        }

        table.setY(-Gdx.graphics.getHeight()/3.5f);
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (state instanceof PlayState) {
            font.draw(spriteBatch, ((PlayState) state).getWord(), Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() - 45);
        }
        else {
            throw new StateException("Wrong state type! Please provide a PlayState.");
        }

        batch.begin();
        font.draw(batch, keyboardInput.getCurrentText(), Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 1.2f);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private TextButton.TextButtonStyle getButtonStyle() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        style.fontColor = Color.BLACK;
        return style;
    }
}
