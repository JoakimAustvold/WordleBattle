package com.mygdx.game.model.highscore;

import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.input.GuessedWord;
import com.mygdx.game.model.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
     * Add a new score to the highscore list in the database
     * @param name
     * @param highscore
     */
    public void submitHighscore(String name, int highscore) {
        SingletonAPI.getInstance().submitHighscore(new Score(name, highscore));
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < highScores.size(); i++) {
            output += highScores.get(i).toString();
        }
        return output;
    }

    @Override
    public void update(float deltaTime) {

    }
    // TODO: Finish this logic
    public long calculateHighscore(Date start, Date end, Collection<GuessedWord> guesses) {
        long startScore = 10000000l;
        long timeUsed = end.getTime() - start.getTime();
        startScore -= timeUsed;
        startScore -= guesses.size() * (50000);

        return startScore;
    }
}
