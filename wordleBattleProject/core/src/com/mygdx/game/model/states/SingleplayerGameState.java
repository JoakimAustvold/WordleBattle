package com.mygdx.game.model.states;


import static com.mygdx.game.model.words.Language.ENGLISH;

import com.mygdx.game.model.GameStatus;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.input.GuessedWord;
import com.mygdx.game.model.input.KeyboardInput;
import com.mygdx.game.model.input.WordInputHandler;
import com.mygdx.game.model.input.WordStatus;
import com.mygdx.game.model.words.Language;
import com.mygdx.game.model.words.WordGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class SingleplayerGameState extends State {

    private GameStatus gameStatus; // Replaces isGameOver
    private int turn;
    private static final int MAX_GUESSES = 6;
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
        guesses = new ArrayList<>();
        disabledChars = new ArrayList<>();
        wordInputHandler = new WordInputHandler(solution, language, guesses, disabledChars);
        gameStatus= GameStatus.ONGOING;

        Date start = new Date();
    }

    public KeyboardInput getKeyboardInput(){
        return this.keyboardInput;
    }

    /**
     * Checks word-submissions to end game when player is out of guesses, or guesses correctly.
     * @param wordStatus of the entered word.
     */
    public void handleSubmit(WordStatus wordStatus){
        if(wordStatus.equals(WordStatus.INVALID)){
            return;
        }

        if(wordStatus.equals(WordStatus.SOLUTION)){
            Date winDate = new Date();


            SingletonAPI.getInstance().submitHighscore();
            gameStatus = GameStatus.WIN;
        }

        else if(guesses.size() >= MAX_GUESSES){
            gameStatus = GameStatus.LOSS;
        }
    }



    @Override
    public void update(float dt) {

    }

    public GameStatus getGameStatus() {
        return gameStatus;
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
