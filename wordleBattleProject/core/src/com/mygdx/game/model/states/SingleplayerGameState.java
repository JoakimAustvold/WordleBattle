package com.mygdx.game.model.states;


import static com.mygdx.game.model.words.Language.ENGLISH;

import com.mygdx.game.model.input.GuessedWord;
import com.mygdx.game.model.input.KeyboardInput;
import com.mygdx.game.model.input.WordInputHandler;
import com.mygdx.game.model.words.Language;
import com.mygdx.game.model.words.WordGenerator;

import java.util.Collection;

public class SingleplayerGameState extends State {
    private boolean isGameOver;
    private int turn;
    private Collection<GuessedWord> guesses;
    private Collection<Character> disabledChars;
    private final String solution;
    private final WordInputHandler wordInputHandler;
    private final KeyboardInput keyboardInput;
    private final Language language = ENGLISH;


    public static final String[][] buttonValues = {
            {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"},
            {"a", "s", "d", "f", "g", "h", "j", "k", "l"},
            {"Enter", "z", "x", "c", "v", "b", "n", "m", "<--"}
    };

    public SingleplayerGameState() {
        keyboardInput = new KeyboardInput();
        WordGenerator wg = new WordGenerator(language);
        solution = wg.generateWord();
        wordInputHandler = new WordInputHandler(solution, language, guesses, disabledChars);
    }

    public KeyboardInput getKeyboardInput(){
        return this.keyboardInput;
    }


    @Override
    public void update(float dt) {

    }

    public String getSolution(){
        return this.solution;
    }

    public WordInputHandler getWordInputHandler() {
        return wordInputHandler;
    }

    public Collection<GuessedWord> getGuesses() {
        return guesses;
    }

    public Collection<Character> getDisabledChars() {
        return disabledChars;
    }
}
