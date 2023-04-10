package com.mygdx.game.model.multiplayer;

import java.util.Random;

public class LobbyCode {

    private int code;

    public LobbyCode() {
        // Generetes a five numbered number
        Random random = new Random();
        int max = 1000000;
        int min = 100000;
        this.code = random.nextInt(max - min + 1) + min;
    }

    public int getCode() {
        return code;
    }



}
