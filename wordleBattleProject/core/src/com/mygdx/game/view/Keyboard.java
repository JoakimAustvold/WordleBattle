package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Keyboard extends ApplicationAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private String currentText;
    private Stage stage;

    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        currentText = "";
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        float buttonWidth = Gdx.graphics.getWidth() / 10f;
        float buttonHeight = Gdx.graphics.getHeight() / 10f;
        float buttonPadding = Gdx.graphics.getWidth() / 100f;

        // Create buttons for each letter of the alphabet
        for (char c = 'a'; c <= 'z'; c++) {
            final char charToAdd = c;
            createButton(String.valueOf(c), buttonWidth, buttonHeight, new Runnable() {
                @Override
                public void run() {
                    currentText += charToAdd;
                }
            });
        }

        // Create the "Enter" button
        createButton("Enter", buttonWidth, buttonHeight, new Runnable() {
            @Override
            public void run() {
                currentText += "\n";
            }
        });

        // Create the "Backspace" button
        createButton("Backspace", buttonWidth, buttonHeight, new Runnable() {
            @Override
            public void run() {
                if (currentText.length() > 0) {
                    currentText = currentText.substring(0, currentText.length() - 1);
                }
            }
        });
    }

    private void createButton(String label, float width, float height, final Runnable onClick) {
        Button button = new Button();
        button.setSize(width, height);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClick.run();
            }
        });
        stage.addActor(button);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, currentText, Gdx.graphics.getWidth() / 10f, Gdx.graphics.getHeight() / 2f);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
        stage.dispose();
    }
}
