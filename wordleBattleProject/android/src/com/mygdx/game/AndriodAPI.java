package com.mygdx.game;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.multiplayer.LobbyCode;

import java.util.Collections;
import java.util.List;

public class AndriodAPI implements FirebaseAPI {

    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference highscoresRef;
    DatabaseReference lobbiesRef;

    public AndriodAPI() {
        database = FirebaseDatabase.getInstance("https://tdt4240-wordlebattle-default-rtdb.europe-west1.firebasedatabase.app/");
        System.out.println(database);
        highscoresRef = database.getReference("highscores");
        lobbiesRef = database.getReference("lobbies");
    }

    @Override
    /**
     * Push a new highscore to the database
     */
    public void submitHighscore(Score score) {
        highscoresRef.push().setValue(score);
    }

    @Override
    /**
     * Creates a new lobby in the database with the lobbyCode as its id
     */
    public void createLobby(LobbyCode lobbyCode) {
        lobbiesRef.push().setValue(lobbyCode);
    }

    @Override
    /**
     * Removes a finished lobby from the database
     */
    public void removeLobby(LobbyCode lobbyCode) {
        //lobbiesRef.removeValue(lobbyCode);
        DatabaseReference lobbyref = database.getReference(lobbyCode.toString());
        lobbyref.removeValue();
    }

    @Override
    /**
     * Read the highscore list from the database once.
     * This happens asynchronous. Do not expect a print statement to be correct immediately.
     */
    public void getHighscoreList(List<Score> dataholder) {
        System.out.println("Getting highscores");
        highscoresRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    System.out.println("Got highscores");
                    Iterable<DataSnapshot> response = task.getResult().getChildren();
                    for (DataSnapshot child : response) {
                        Log.d("firebase loop:", String.valueOf(child.getValue(Score.class)));
                        dataholder.add(child.getValue(Score.class));
                    }
                    Collections.sort(dataholder);
                    Log.d("firebase dataholder:", String.valueOf(dataholder));

                }
            }
        });
    }
}
