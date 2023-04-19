package com.mygdx.game.model.highscore;

import com.mygdx.game.model.input.GuessedWord;

import java.util.Collection;
import java.util.Date;

/**
 * A class representing a player's score.
 */
public class Score implements Comparable<Score> {

    private int highscore;
    private String username;

    public Score() {
        highscore = 0;
        username = "";
    }

    /**
     * Calculate a players score based on time played and number of guesses
     * @param start the time when a game begins
     * @param end the time when a word is guessed
     * @param guesses number of guesses used
     */
    public Score(Date start, Date end, Collection<GuessedWord> guesses) {
        int startScore = 2500;
        System.out.println("Start score: " + startScore);
        // difference of 1000 = 1 second
        int timeUsed = Math.round(end.getTime() - start.getTime())/1000;
        System.out.println("timeused " + timeUsed);
        startScore -= timeUsed;
        System.out.println(startScore);
        // 5*300 = 1500
        startScore -= (guesses.size()-1) * (300);
        System.out.println("highscore: " + startScore);

        // Don't allow negative scores
        if (startScore < 0) {
            startScore = 0;
        }

        this.highscore = startScore;
        this.username = null;
    }

    public Score(String name, int highscore)
    {
        this.highscore = highscore;
        this.username = name;
    }

    public int getHighscore() {
        return highscore;
    }

    public String getUsername() {
        return username;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return String.format("(username: %s, score: %s)", username, highscore);
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
    
}