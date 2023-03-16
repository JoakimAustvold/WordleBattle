package com.mygdx.game.model.highscore;

public class Score implements Comparable<Score> {

    private int highscore;
    private String name;

    public Score(String name, int highscore)
    {
        this.highscore = highscore;
        this.name = name;
    }

    public int getHighscore() {
        return highscore;
    }

    public String getName() {
        return name;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public String toString() {
        return String.format("(name: %s, score: %s)", name, highscore);
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
            return name.compareTo(o.name);
        }
    }

}