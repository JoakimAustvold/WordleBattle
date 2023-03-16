package com.mygdx.game.model;

import com.mygdx.game.model.highscore.Score;

import java.util.List;

public interface FirebaseAPI {

    public void updateAPI();

    public void getHighscoreList(List<Score> dataholder);
    void submitHighscore(Score score);
}
