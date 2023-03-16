package com.mygdx.game;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.highscore.Score;

import java.util.Collections;
import java.util.List;

public class AndriodAPI implements FirebaseAPI {

    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference highscores;

    public AndriodAPI() {
        database = FirebaseDatabase.getInstance("https://tdt4240-wordlebattle-default-rtdb.europe-west1.firebasedatabase.app/");
        System.out.println(database);
        highscores = database.getReference("highscores");
    }

    @Override
    public void updateAPI() {
        if(highscores != null) {
            highscores.setValue("Hello World!");
        } else {
            System.out.println("Database test failed");
        }
    }

    @Override
    public void submitHighscore(Score score) {
        highscores.push().setValue(score);
    }

    @Override
    public void getHighscoreList(List<Score> dataholder) {
        System.out.println("Getting highscores");
        highscores.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                System.out.println("Got highscores");
                Iterable<DataSnapshot> response = task.getResult().getChildren();
                for (DataSnapshot child : response) {
                    dataholder.add(child.getValue(Score.class));
                }
                Collections.sort(dataholder);
            }
        });
    }
}
