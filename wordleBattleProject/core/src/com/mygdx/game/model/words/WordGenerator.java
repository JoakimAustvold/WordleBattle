package com.mygdx.game.model.words;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

import java.util.Random;

/**
 * Class for generating random 5-letter words.
 * Support for multiple languages.
 * @author Marcus Birkeland
 * @version 20.03.2023
 */
public class WordGenerator {

    private final Random random;

    public enum Language {
        ENGLISH,
        NORWEGIAN
    }

    private Language selectedLanguage;

    public WordGenerator(Language language){
        this.selectedLanguage = language;
        random = new Random();
    }

    /**
     * Generate a 5-letter word from the selected language.
     * @return a random 5-letter word.
     */
    public String generateWord() {
        // Get filename based on language.
        String filename = getFilename();
        // Open wordlist as json, and store to a Words object.
        Json json = new Json();
        Words words = json.fromJson(Words.class, Gdx.files.internal(filename));
        String[] wordArr = words.getWords();

        return wordArr[random.nextInt(wordArr.length)];
    }

    /**
     * Get the file name based on the selected language.
     * @return a string containing the filename.
     */
    private String getFilename(){
        String filename;
        switch (selectedLanguage){
            case ENGLISH:
                filename = "words/wordle_answers_eng.json";
                break;
            case NORWEGIAN:
                filename = "words/wordle_norwegian.json";
                break;
            default:
                filename = "words/english_words_5.json";
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
