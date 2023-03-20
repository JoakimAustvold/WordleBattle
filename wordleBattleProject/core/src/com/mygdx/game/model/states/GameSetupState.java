package com.mygdx.game.model.states;

public class GameSetupState extends State {
    private static GameSetupState instance = new GameSetupState();

    private GameSetupState() {
    }

    public static GameSetupState getInstance() {
        return instance;
    }

    @Override
    public void update(float dt) {

    }

}
