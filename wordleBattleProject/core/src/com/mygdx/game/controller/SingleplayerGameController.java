package com.mygdx.game.controller;

import com.mygdx.game.model.states.PlayState;
import com.mygdx.game.view.SingleplayerGameView;

public class SingleplayerGameController extends Controller{

    public SingleplayerGameController (){
        this.state = new PlayState();
        this.view = new SingleplayerGameView();
    }

    @Override
    public void handleInput() {

    }
}
