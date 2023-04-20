package com.mygdx.game.controller;

import com.mygdx.game.model.highscore.HighscoreList;

import com.mygdx.game.view.HighscoreView;

public class HighscoreController extends Controller{

    public HighscoreController() {
        this.state = new HighscoreList();
        this.view = new HighscoreView();

        addBackButtonListener();
    }
}
