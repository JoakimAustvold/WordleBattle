package com.mygdx.game.model.states;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.model.keyboard.KeyboardInput;
import com.mygdx.game.model.words.WordGenerator;

public class PlayState extends State { // Called WordleGame in first draft of diagrams
    private boolean isDuel;
    private boolean isGameOver;
    private int turn;
    private String inviteCode;
    private String[] guessesPlayer1;
    private String[] guessesPlayer2;
    private String word;
    //private Player player1;
    //private Player player2;

    public static final String[][] buttonValues = {
            {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"},
            {"a", "s", "d", "f", "g", "h", "j", "k", "l"},
            {"Enter", "z", "x", "c", "v", "b", "n", "m", "<--"}
    };

    private KeyboardInput keyboardInput;

    public PlayState() {
        keyboardInput = new KeyboardInput();
        WordGenerator wg = new WordGenerator(WordGenerator.Language.ENGLISH);
        word = wg.generateWord();
    }

    public KeyboardInput getKeyboardInput(){
        return this.keyboardInput;
    }


    @Override
    public void update(float dt) {

    }

    public String getWord(){
        return this.word;
    }

}
