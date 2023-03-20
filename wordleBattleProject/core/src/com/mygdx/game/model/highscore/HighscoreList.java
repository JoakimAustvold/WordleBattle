package com.mygdx.game.model.highscore;

import com.mygdx.game.model.FirebaseAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a list of several players' scores
 */
public class HighscoreList {
    private List<Score> highScores = new ArrayList<>();
    FirebaseAPI firebaseAPI;

    public HighscoreList(FirebaseAPI firebaseAPI) {
        this.firebaseAPI = firebaseAPI;
        fetchHighscores();
    }

    /**
     * Update the local version of the highscore list from the firebase database
     */
    public void fetchHighscores() {
        this.highScores.clear();
        firebaseAPI.getHighscoreList(this.highScores);
    }

    /**
     * @return A copy of the local highscore list
     */
    public List<Score> getLocalHighscores() {
        return new ArrayList<>(highScores);
    }

    /**
     * Add a new score to the highscore list in the database
     * @param name
     * @param highscore
     */
    public void submitHighscore(String name, int highscore) {
        firebaseAPI.submitHighscore(new Score( name, highscore));
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < highScores.size(); i++) {
            output += highScores.get(i).toString();
        }
        return output;
    }

}
