package com.mygdx.game.model.multiplayer;

import com.mygdx.game.model.SingletonAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A class that creates a randomly generated code used to represent a unique lobby.
 */
public class LobbyCode {

    private String code;
    
    /**
     * Generates a five digit number using the Random class.
     */
    public LobbyCode() {
        Random random = new Random();
        int max = 1000000;
        int min = 100000;
        this.code = String.valueOf(random.nextInt(max - min + 1) + min);

        // TODO: Check if the code is unique
        //List<String> dataholder = new ArrayList<String>();
        //SingletonAPI.getInstance().viewAllLobbies(dataholder);
        // TODO: Figure out how to wait for this asynchronous function to update the list fully
    }

    public String getCode() {
        return code;
    }



}
