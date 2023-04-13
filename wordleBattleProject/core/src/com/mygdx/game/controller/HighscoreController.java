package com.mygdx.game.controller;

import com.mygdx.game.model.highscore.HighscoreList;
import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.view.HighscoreView;

import java.util.List;

public class HighscoreController extends Controller{

    public HighscoreController() {
        this.state = new HighscoreList();
        this.view = new HighscoreView();
    }

    @Override
    public void handleInput() {
        
    }
    
    
}
