package com.mygdx.game;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.multiplayer.LobbyCode;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import java.util.Collections;
import java.util.List;

public class AndroidAPI implements FirebaseAPI {
    FirebaseDatabase database;
    DatabaseReference highscoresRef;
    DatabaseReference lobbiesRef;

    public AndroidAPI() {
        database = FirebaseDatabase.getInstance("https://tdt4240-wordlebattle-default-rtdb.europe-west1.firebasedatabase.app/");
        System.out.println(database);
        highscoresRef = database.getReference("highscores");
        lobbiesRef = database.getReference("lobbies");
    }

    /**
     * Push a new highscore to the database
     */
    @Override
    public void submitHighscore(Score score) {
        highscoresRef.push().setValue(score);
    }
    
    
    /**
    * Creates a new lobby in the database with the lobbyCode as its id
    */
    @Override
    public void createLobby(String code) {
        lobbiesRef.child(code).setValue("empty");
    }

    /**
     * Removes a lobby no longer in use from the database
     */
    @Override
    public void removeLobby(String code) {
        lobbiesRef.child(code).removeValue();
    }

    /**
     *  Adds player one to the lobby specified
     */
    @Override
    public void addPlayerOneToLobby(String code, String username) {
        System.out.println(lobbiesRef.child(String.valueOf(code)).get());
        lobbiesRef.child(code).child("playerOne").setValue(username);
    }

    /**
     * Adds a player wishing to join the game to the lobby, given that there is only
     * one player in the lobby
     */
    @Override
     public void addPlayerTwoToLobby(String code, String username) {
        //TODO: Check that the lobby exists
        //TODO: Check that the lobby is not already full
         System.out.println(lobbiesRef.child(String.valueOf(code)).get());
         lobbiesRef.child(code).child("playerTwo").setValue(username);
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

    @Override
    public void createPlayerTwoListener(String code) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                String playerTwo= dataSnapshot.getValue(String.class);
                LobbyInfo.getInstance().setPlayerTwo(playerTwo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadP2:onCancelled", databaseError.toException());
            }
        };

        lobbiesRef.child(code).child("playerTwo").addValueEventListener(postListener);
    }

    @Override
    public void createPlayerOneListener(String code) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                String playerOne= dataSnapshot.getValue(String.class);
                LobbyInfo.getInstance().setPlayerOne(playerOne);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadP1:onCancelled", databaseError.toException());
            }
        };

        lobbiesRef.child(code).child("playerOne").addValueEventListener(postListener);
    }

    @Override
    public void removePlayerTwoFromLobby(String code) {
        lobbiesRef.child(code).child("playerTwo").removeValue();
    }

    @Override
    public void viewAllLobbies(List<String> dataholder) {
        System.out.println("Getting lobbies");
        lobbiesRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    System.out.println("Got Lobbies");
                    Iterable<DataSnapshot> response = task.getResult().getChildren();
                    for (DataSnapshot child : response) {
                        //Log.d("firebase loop1:", String.valueOf(child.getValue(HashMap.class)));
                        Log.d("firebase loop2:", String.valueOf(child.getKey()));
                        dataholder.add(child.getKey());
                    }
                    //Collections.sort(dataholder);
                    Log.d("firebase dataholder:", String.valueOf(dataholder));

                }
            }
        });
    }


}
