package com.mygdx.game;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.multiplayer.LobbyCode;

import java.util.List;

public class DesktopAPI implements FirebaseAPI {

    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference myRef;

    public DesktopAPI() {
        database = FirebaseDatabase.getInstance("https://tdt4240-wordlebattle-default-rtdb.europe-west1.firebasedatabase.app/");
        database.getReference("message");
    }

     @Override
    public void updateAPI() {
        if(myRef != null) {
            myRef.setValue("Hello world");
        } else {
            System.out.println("Database test failed");
        }
    }

    /*
    @Override
    public FirebaseAPI getInstance() {
        return null;
    }*/

    @Override
    public void getHighscoreList(List<Score> dataholder) {

    }

    @Override
    public void submitHighscore(Score score) {

    }

    @Override
    public void createLobby(LobbyCode lobbyCode) {

    }

    @Override
    public void removeLobby(LobbyCode lobbyCode) {

    }

    @Override
    public void addUserToLobby(LobbyCode lobbyCode, String username) {
        
    }
}
