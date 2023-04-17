package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.SingleplayerGameController;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.GameStatus;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.input.GuessedLetter;
import com.mygdx.game.model.input.GuessedLetterStatus;
import com.mygdx.game.model.input.GuessedWord;
import com.mygdx.game.model.states.SingleplayerGameState;
import com.mygdx.game.model.states.State;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;

public class SingleplayerGameView extends View {

    private static final float WORD_POS_X_DIVISOR = 2.5f;
    private static final float WORD_DELTA_Y = 150.0f;

    private final Texture texture = new Texture(Gdx.files.internal("textures/letters/a.png"));

    private static final Color COLOR_KEY_ENABLED = Color.WHITE;
    private static final Color COLOR_KEY_DISABLED = Color.GRAY;

    private static final float buttonWidth = Gdx.graphics.getWidth() / 16f;
    private static final float buttonHeight = Gdx.graphics.getHeight() / 20f;
    private static final float buttonPadding = buttonWidth / 5f;
    private final TextButton[][] buttons = new TextButton[SingleplayerGameState.buttonValues.length][];

    private final BitmapFont font = new BitmapFont();
    private final Stage stage = new Stage();
    private final Stage stage2 = new Stage();

    private TextField usernameTextField;
    private TextButton addHighscore;

    public SingleplayerGameView() {
        font.getData().setScale(6, 6);
        font.setColor(COLOR_KEY_ENABLED);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setInputProcessor(stage2);

        setupKeyboard();
        setup();

        //LocalTime start = LocalTime.now();
    }

    @Override
    public void setup() {
        // input field for username
        usernameTextField = new TextField("", skin);
        usernameTextField.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 300), 400);
        usernameTextField.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));
        usernameTextField.setMessageText("Username: ");


         // button add high (also restarts game)

        addHighscore = new TextButton("Add highscore", skin);
        addHighscore.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR + 100), 400);
        addHighscore.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        addHighscore.addListener(new ChangeListener() {
        @Override
        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               // System.out.println(usernameTextField.getText());
                // add highscore to firebase
                //SingletonAPI...
               // gameState.getScore().setUsername(usernameTextField.getText());

               // SingletonAPI.getInstance().submitHighscore(gameState.getScore());

               System.out.println("I am adding the highscore!!!!!");

                // new game
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().push(new SingleplayerGameController());
            }
        });

        // skip / new game
        TextButton newGame = new TextButton("New Game", skin);
        newGame.setPosition((Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100), 100);
        newGame.setSize((float) (Gdx.graphics.getWidth()*0.4), (float) (Gdx.graphics.getHeight() * 0.05));

        newGame.addListener(new ChangeListener() {
        @Override
        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            ControllerManager.getInstance().pop();
            ControllerManager.getInstance().push(new SingleplayerGameController());
        }
        });

        stage2.addActor(usernameTextField);
        stage2.addActor(addHighscore);
        stage2.addActor(newGame);

    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        final SingleplayerGameState gameState = (SingleplayerGameState) state;

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
            String[] colourToDraw = createColoredList(word);
            for (int i = 0; i < word.getWord().length(); i++) {
                switch(colourToDraw[i]) {
                    case "[GRAY]":
                        spriteBatch.draw(new Texture(Gdx.files.internal("textures/backgrounds/gray.png")),
                                (Gdx.graphics.getWidth() / (WORD_POS_X_DIVISOR + 1.5f)) + (i * 150), (Gdx.graphics.getHeight() - 110.0f - (c+1)*WORD_DELTA_Y) - WORD_DELTA_Y);
                        break;
                    case "[ORANGE]":
                        spriteBatch.draw(new Texture(Gdx.files.internal("textures/backgrounds/orange.png")),
                                (Gdx.graphics.getWidth() / (WORD_POS_X_DIVISOR + 1.5f)) + (i * 150), (Gdx.graphics.getHeight() - 110.0f - (c+1)*WORD_DELTA_Y) - WORD_DELTA_Y);
                        break;
                    case "[GREEN]":
                        spriteBatch.draw(new Texture(Gdx.files.internal("textures/backgrounds/green.png")),
                                (Gdx.graphics.getWidth() / (WORD_POS_X_DIVISOR + 1.5f)) + (i * 150), (Gdx.graphics.getHeight() - 110.0f - (c+1)*WORD_DELTA_Y) - WORD_DELTA_Y);
                        break;
                }
                spriteBatch.draw(new Texture(Gdx.files.internal("textures/letters/"+word.getWord().charAt(i)+".png")),
                        (Gdx.graphics.getWidth() / (WORD_POS_X_DIVISOR + 1.5f)) + (i * 150), (Gdx.graphics.getHeight() - 110.0f - (c+1)*WORD_DELTA_Y) - WORD_DELTA_Y);
            }
            c++;
        }

        if(gameState.getGameStatus().equals(GameStatus.ONGOING)){
            String currentText = ((SingleplayerGameState) state).getKeyboardInput().getCurrentText();
            for (int i = 0; i < currentText.length(); i++) {
                spriteBatch.draw(new Texture(Gdx.files.internal("textures/letters/"+currentText.charAt(i)+".png")),
                        (Gdx.graphics.getWidth() / (WORD_POS_X_DIVISOR + 1.5f)) + (i * 150), (Gdx.graphics.getHeight() - 110.0f - (c+1)*WORD_DELTA_Y) - WORD_DELTA_Y);
            }
            // Draw keyboard
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }

        if(gameState.getGameStatus().equals(GameStatus.WIN)){

            // Removes keyboard from the stage
            /*
            for (Actor actor: stage.getActors()) {
                actor.remove();
            }*/

            font.draw(spriteBatch, "You win!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
            //font.draw(spriteBatch, "The word was:", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 700);
           // font.draw(spriteBatch, gameState.getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, 600);

           // your score
            font.draw(spriteBatch, "Your score is: " + gameState.getScore().getHighscore(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 700);

            stage2.act(Gdx.graphics.getDeltaTime());
            stage2.draw();
        }
        else if(gameState.getGameStatus().equals(GameStatus.LOSS)){
            font.draw(spriteBatch, "Out of guesses!", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 100, 800);
            font.draw(spriteBatch, "The word was:", Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR - 200, 650);
            font.draw(spriteBatch, gameState.getSolution(), Gdx.graphics.getWidth() / WORD_POS_X_DIVISOR, 550);
        }

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        font.dispose();
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
