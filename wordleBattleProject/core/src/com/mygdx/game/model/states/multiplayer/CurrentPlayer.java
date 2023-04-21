package com.mygdx.game.model.states.multiplayer;

public enum CurrentPlayer {
    PLAYERONE("playerOne"),
    PLAYERTWO("playerTwo");

    public final String label;

    private CurrentPlayer(String label) {
        this.label = label;
    }
}
