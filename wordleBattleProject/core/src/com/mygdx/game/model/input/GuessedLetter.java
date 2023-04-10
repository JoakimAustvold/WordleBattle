package com.mygdx.game.model.input;

public class GuessedLetter {
    private Character letter;
    private GuessedLetterStatus status;

    public GuessedLetter(Character letter, GuessedLetterStatus status) {
        this.letter = letter;
        this.status = status;
    }

    public Character getLetter() {
        return letter;
    }

    public GuessedLetterStatus getStatus() {
        return status;
    }
}
