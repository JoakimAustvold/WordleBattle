package com.mygdx.game.model.input;

import com.mygdx.game.model.words.Language;
import com.mygdx.game.model.words.WordGenerator;
import com.mygdx.game.model.words.WordValidator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Validates a word and sets flags for coloring each letter.
 * Also sets the map of disabled letters.
 * @version 05.04.2023
 * @author Marcus Birkeland
 */
public class WordInputHandler {
    private final String solution;
    private final WordValidator wordValidator;
    private final Collection<GuessedWord> guessedWords;
    private final Collection<Character> disabledLetters;

    public WordInputHandler(
            String solution,
            Language language,
            Collection<GuessedWord> guessedWords,
            Collection<Character> disabledChars
    ){
        this.solution = solution;
        this.wordValidator = new WordValidator(language);
        this.guessedWords = guessedWords;
        this.disabledLetters = disabledChars;
    }


    /**
     * Handles input of new words.
     * @param keyboardInput to handle and validate.
     * @return validation state WordStatus of the input word.
     */
    public WordStatus handleInput (KeyboardInput keyboardInput){
        String inputWord = keyboardInput.getCurrentText();
        WordStatus wordStatus = validateWord(inputWord);
        if(wordStatus == WordStatus.INVALID){
            return wordStatus;
        }
        guessedWords.add(new GuessedWord(inputWord,getValidatedLetters(inputWord)));
        return wordStatus;
    }

    /**
     * Checks if a input word is valid or equal to the solution.
     * @param inputWord
     * @return validation state WordStatus of inputWord.
     */
    private WordStatus validateWord(String inputWord){
        if (inputWord.equals(solution))
            return WordStatus.SOLUTION;

        if(!wordValidator.isValid(inputWord))
            return WordStatus.INVALID;

        return WordStatus.VALID;
    }

    /**
     * Generates GuessedLetter objects with validation state.
     * @param guess to validate letters for. Also sets disabled letters collection.
     * @return a collection of GuessedLetter(s). All letters should have set a flag for their status.
     */
    private Collection<GuessedLetter> getValidatedLetters(String guess){
        Collection<GuessedLetter> guessedLetters = new ArrayList<>();

        for (int i = 0; i < guess.length(); i++){
            char c = guess.charAt(i);

            if(solution.charAt(i) == c){
                guessedLetters.add(new GuessedLetter(c,GuessedLetterStatus.CORRECT));
            }
            else if(solution.contains("" + c)){             // Concat to CharSequence
                guessedLetters.add(new GuessedLetter(c, GuessedLetterStatus.WRONG_POS));
            }
            else {
                guessedLetters.add(new GuessedLetter(c, GuessedLetterStatus.INCORRECT));
                this.disabledLetters.add(c);
            }
        }
        return guessedLetters;
    }
}
