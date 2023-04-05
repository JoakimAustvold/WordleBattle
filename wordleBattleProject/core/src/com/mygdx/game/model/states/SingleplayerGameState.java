package com.mygdx.game.model.states;


import com.mygdx.game.model.input.KeyboardInput;
import com.mygdx.game.model.words.WordGenerator;

public class SingleplayerGameState extends State { // Called WordleGame in first draft of diagrams
    private boolean isGameOver;
    private int turn;
    private String[] guesses;
    private final String word;

    public static final String[][] buttonValues = {
            {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"},
            {"a", "s", "d", "f", "g", "h", "j", "k", "l"},
            {"Enter", "z", "x", "c", "v", "b", "n", "m", "<--"}
    };

    private final KeyboardInput keyboardInput;

    public SingleplayerGameState() {
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
