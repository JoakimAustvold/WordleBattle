package com.mygdx.game.model.states.multiplayer;

import com.mygdx.game.model.input.KeyboardInput;
import com.mygdx.game.model.states.State;
import com.mygdx.game.model.words.Language;
import com.mygdx.game.model.words.WordGenerator;

public class MultiplayerGameState extends State {
    private boolean isDuel;
    private boolean isGameOver;
    private int turn;
    private String inviteCode;
    private String[] guessesPlayer1;
    private String[] guessesPlayer2;
    private final String word;
    //private Player player1;
    //private Player player2;

    public static final String[][] buttonValues = {
            {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"},
            {"a", "s", "d", "f", "g", "h", "j", "k", "l"},
            {"Enter", "z", "x", "c", "v", "b", "n", "m", "<--"}
    };

    private final KeyboardInput keyboardInput;

    public MultiplayerGameState() {
        keyboardInput = new KeyboardInput();
        WordGenerator wg = new WordGenerator(Language.ENGLISH);
        word = wg.generateWord();
    }

    public KeyboardInput getKeyboardInput(){
        return this.keyboardInput;
    }

    public String getWord(){
        return this.word;
    }

}
