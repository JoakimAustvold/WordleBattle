package com.mygdx.game.model.states;

public class MenuState extends State {
    private static MenuState instance = new MenuState();

    private MenuState() {

    }

    public static MenuState getInstance() {
        return instance;
    }


    @Override
    public void update(float dt) {

    }

}
