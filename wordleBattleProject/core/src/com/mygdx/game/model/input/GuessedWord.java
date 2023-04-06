package com.mygdx.game.model.input;

import java.util.Collection;

/**
 * Class for holding state for a guessed word.
 * Has a collection of letters that have state for each letter that can be used to color the letters accordingly.
 * @author Marcus Birkeland
 * @version 05.04.2023
 */
public class GuessedWord {
    private String word;
    private Collection<GuessedLetter> letters;

    public GuessedWord(String word, Collection<GuessedLetter> letters) {
        this.word = word;
        this.letters = letters;
    }

    public String getWord() {
        return word;
    }

    public Collection<GuessedLetter> getLetters() {
        return letters;
    }
}
