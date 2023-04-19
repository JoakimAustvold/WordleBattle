package com.mygdx.game.model.highscore;

import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.State;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a list of several players' scores
 */
public class HighscoreList extends State {
    private List<Score> highScores = new ArrayList<>();

    public HighscoreList() {
        fetchHighscores();
    }

    /**
     * Update the local version of the highscore list from the firebase database
     */
    public void fetchHighscores() {
        System.out.println(highScores);
        this.highScores.clear();
        System.out.println(highScores);
        SingletonAPI.getInstance().getHighscoreList(this.highScores);
    }

    /**
     * @return A copy of the local highscore list
     */
    public List<Score> getLocalHighscores() {
        return new ArrayList<Score>(highScores);
    }

    /**
    *   Creates a string with the information from this class in a readable manner
    */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < highScores.size(); i++) {
            output += highScores.get(i).toString();
        }
        return output;
    }

}
