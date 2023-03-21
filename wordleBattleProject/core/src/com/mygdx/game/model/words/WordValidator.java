package com.mygdx.game.model.words;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

import java.util.Random;

/**
 * Class to validate any input word.
 * @author Marcus Birkeland
 * @version 21.03.2023
 */
public class WordValidator {

    private WordGenerator.Language selectedLanguage;

    public WordValidator(WordGenerator.Language language){
        this.selectedLanguage = language;
    }

    /**
     * Checks if a given word is valid. Used to check input when a player inputs a word.
     * @param word to check.
     * @return true if valid, false if invalid.
     */
    public boolean isValid(String word){
        if(word.length() != 5){
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

    public WordGenerator.Language getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(WordGenerator.Language selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }
}
