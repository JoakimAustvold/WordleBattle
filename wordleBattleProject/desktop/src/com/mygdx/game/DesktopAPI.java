package com.mygdx.game;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.highscore.Score;

import java.util.List;

public class DesktopAPI implements FirebaseAPI {

    // Write a message to the database
//    FirebaseDatabase database;
//    DatabaseReference myRef;

    public DesktopAPI() {
/*        database = FirebaseDatabase.getInstance();
        database.getReference("message");*/
    }

    @java.lang.Override
    public void updateAPI() {
/*        if(myRef != null) {
            myRef.setValue("Hello world");
        } else {
            System.out.println("Database test failed");
        }*/
    }

    @Override
    public void getHighscoreList(List<Score> dataholder) {

    }

    @Override
    public void submitHighscore(Score score) {

    }
}