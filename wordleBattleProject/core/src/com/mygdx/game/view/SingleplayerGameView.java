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

    private final float buttonWidth = Gdx.graphics.getWidth() / 10f;
    private final float buttonHeight = Gdx.graphics.getHeight() / 12f;
    private final float buttonPadding = buttonWidth / 5f;

    private final String[] buttonValues = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
            "a", "s", "d", "f", "g", "h", "j", "k", "l",
            "z", "x", "c", "v", "b", "n", "m", "<--", "Enter"};

    private final TextButton[] buttons = new TextButton[buttonValues.length];

    private final SpriteBatch batch = new SpriteBatch();
    private final BitmapFont font = new BitmapFont();
    private final KeyboardInput keyboardInput;
    private final Stage stage = new Stage();

    public SingleplayerGameView() {
        font.getData().setScale(6,6);
        font.setColor(Color.BLACK);

        keyboardInput = new KeyboardInput();

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        for (int i = 0; i < buttonValues.length; i++) {
            String buttonValue = buttonValues[i];
            TextButton button = new TextButton(buttonValue, getButtonStyle());
            button.getLabel().setFontScale(buttonWidth / 20f);
            buttons[i] = button;
        }

        for (int i = 0; i < buttonValues.length-1; i += 3) {
            table.add(buttons[i]).size(buttonWidth, buttonHeight).pad(buttonPadding);
            table.add(buttons[i+1]).size(buttonWidth, buttonHeight).pad(buttonPadding);
            table.add(buttons[i+2]).size(buttonWidth, buttonHeight).pad(buttonPadding);
            table.row();
        }
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (state instanceof PlayState) {
            font.draw(spriteBatch, ((PlayState) state).getWord(), Gdx.graphics.getWidth()/(((PlayState) state).getWord().length()-2), Gdx.graphics.getHeight() - 45);
        } else {
            throw new StateException("Wrong state type! Please provide a PlayState.");
        }

        batch.begin();
        font.draw(batch, keyboardInput.getCurrentText(), buttonWidth, Gdx.graphics.getHeight() / 2f);
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
