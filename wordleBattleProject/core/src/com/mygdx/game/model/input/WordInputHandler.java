package com.mygdx.game.model.input;

import static com.mygdx.game.WordleBattleGame.WORD_LENGTH;

import com.mygdx.game.model.words.Language;
import com.mygdx.game.model.words.WordValidator;

import java.util.Arrays;
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
     * Handles input of new words. Adds a new guessed word with validation flags, and sets disabled letters.
     * @param keyboardInput to handle and validate.
     * @return validation state WordStatus of the input word.
     */
    public WordStatus handleInput (KeyboardInput keyboardInput){
        String inputWord = keyboardInput.getCurrentText();
        WordStatus wordStatus = validateWord(inputWord);
        if(wordStatus == WordStatus.INVALID){
            return wordStatus;
        }
        Collection<GuessedLetter> letters = Arrays.asList(getValidatedLetters(inputWord));
        guessedWords.add(new GuessedWord(inputWord,letters));
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
    private GuessedLetter [] getValidatedLetters(String guess){
        GuessedLetter [] guessedLetters = new GuessedLetter [WORD_LENGTH];

        // Do separate pass for correct letters to be able to count correct letters in guess later.
        for (int i = 0; i < guess.length(); i++){
            char c = guess.charAt(i);

            if(solution.charAt(i) == c){
                guessedLetters[i] = new GuessedLetter(c,GuessedLetterStatus.CORRECT);
            }
        }

        // Pass for flagging WRONG_POS and INCORRECT letters.
        for (int i = 0; i < guess.length(); i++){

            if(guessedLetters[i] != null){
                continue;
            }
            char c = guess.charAt(i);
            int validGuessCount; // Keeps track of  non-incorrect guesses for given letter.

            if(solution.contains("" + c)){             // Concat to CharSequence
                guessedLetters[i] = new GuessedLetter(c, GuessedLetterStatus.WRONG_POS);
                // Count number of times letter has been flagged with correct or wrong_pos
                validGuessCount = getNumOccurrences(guessedLetters, c);

                // Flag as WRONG_POS if not already guessed correctly,
                // or WRONG_POS is set greater or equal to that char's number of occurrences.
                if(validGuessCount > getNumOccurrences(solution.toCharArray(), c)){
                    guessedLetters[i] = (new GuessedLetter(c, GuessedLetterStatus.INCORRECT));
                }
            }
            // Flag incorrect and disable button if the letter is not in the word.
            else {
                guessedLetters[i] = (new GuessedLetter(c, GuessedLetterStatus.INCORRECT));
                this.disabledLetters.add(c);
            }
        }
        return guessedLetters;
    }

    /**
     * Get number of occurrences of a non-incorrect guess.
     * @return num non-incorrect occurrences.
     */
    private static int getNumOccurrences(GuessedLetter [] letters, char c){
        int count = 0;
        for (GuessedLetter letter : letters){
            if(letter == null){
                continue;
            }
            if(
                    letter.getLetter().equals((Character) c)
                    && !letter.getStatus().equals(GuessedLetterStatus.INCORRECT
            )
            ){
                count++;
            }
        }
        return  count;
    }

    /**
     * Get number of occurrences of a char in an array.
     * @return num occurrences.
     */
    private static int getNumOccurrences(char[] array, char c){
        int count = 0;
        for (char element : array){
            if(element == c)
                count++;
        }
        return  count;
    }
}
