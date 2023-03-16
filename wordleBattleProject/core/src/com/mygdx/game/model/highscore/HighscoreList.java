package com.mygdx.game.model.highscore;

import com.mygdx.game.model.FirebaseAPI;

import java.util.ArrayList;
import java.util.List;

public class HighscoreList {
    private List<Score> highScores = new ArrayList<>();
    FirebaseAPI firebaseAPI;

    public HighscoreList(FirebaseAPI firebaseAPI) {
        this.firebaseAPI = firebaseAPI;
        fetchHighscores();
    }

    public void fetchHighscores() {
        this.highScores.clear();
        firebaseAPI.getHighscoreList(this.highScores);
        System.out.println(highScores);
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < highScores.size(); i++) {
          output += highScores.get(i).toString();
        }
        return output;
    }

    public void submitHighscore(String name, int highscore) {
        firebaseAPI.submitHighscore(new Score( name, highscore));
        
    }

}
