package com.mygdx.game.model.words;

import static com.mygdx.game.WordleBattleGame.WORD_LENGTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

/**
 * Class to validate any input word.
 * @author Marcus Birkeland
 * @version 21.03.2023
 */
public class WordValidator {

    private Language selectedLanguage;

    public WordValidator(Language language){
        this.selectedLanguage = language;
    }

    /**
     * Checks if a given word is valid. Used to check input when a player inputs a word.
     * @param word to check.
     * @return true if valid, false if invalid.
     */
    public boolean isValid(String word){
        if(word.length() != WORD_LENGTH){
            return false;
        }
        // Open wordlist as json, and store to a Words object.
        Json json = new Json();
        Words words = json.fromJson(Words.class, Gdx.files.internal(getFilename()));
        String[] wordArr = words.getWords();
        // Check if word exists in wordlist.
        for(String w : wordArr){
            if(word.toLowerCase().equals(w)){
                return true;
            }
        }
        return false;
    }


    /**
     * Get the file name based on the selected language.
     * @return a string containing the filename.
     */
    private String getFilename(){
        String filename;
        switch (selectedLanguage){
            case ENGLISH:
                filename = "words/wordle_guesses_eng.json";
                break;
            case NORWEGIAN:
                filename = "words/wordle_norwegian.json";
                break;
            default:
                filename = "words/wordle_guesses_eng.json";
        }
        return filename;
    }

    public Language getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(Language selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }
}
