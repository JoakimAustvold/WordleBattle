package com.mygdx.game.model;

import com.mygdx.game.model.highscore.Score;

import java.util.List;

/**
 * FirbaseAPI is an interface which defines the methods used to read and write to the database
 * The methods are implemented in the class AndroidAPI
 */
public interface FirebaseAPI {

    void updateAPI();

    public void getHighscoreList(List<Score> dataholder);
    public void submitHighscore(Score score);
}
