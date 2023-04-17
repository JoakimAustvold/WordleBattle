package com.mygdx.game.model.highscore;

import com.mygdx.game.model.input.GuessedWord;

import java.util.Collection;
import java.util.Date;

/**
 * A class representing a player's score.
 */
public class Score implements Comparable<Score> {

    private long highscore;
    private String username;

    public Score() {
        highscore = 0;
        username = "";
    }

    public Score(Date start, Date end, Collection<GuessedWord> guesses) {
        long startScore = 10000000l;
        System.out.println(startScore);
        long timeUsed = end.getTime() - start.getTime();
        System.out.println(startScore);
        startScore -= timeUsed;
        startScore -= guesses.size() * (50000);
        System.out.println(startScore);

        this.highscore = startScore;
        this.username = null;
    }

    public Score(String name, long highscore)
    {
        this.highscore = highscore;
        this.username = name;
    }

    public long getHighscore() {
        return highscore;
    }

    public String getUsername() {
        return username;
    }

    public void setHighscore(long highscore) {
        this.highscore = highscore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return String.format("(name: %s, score: %s)", username, highscore);
    }

    @Override
    public int compareTo(Score o) {
        if (highscore > o.highscore) {
            return -1;
        }
        else if (highscore < o.highscore) {
            return 1;
        }
        else {
            return username.compareTo(o.username);
        }
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