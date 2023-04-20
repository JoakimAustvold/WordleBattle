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
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.multiplayer.JoinLobbyController;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.multiplayer.LobbyCode;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.model.states.multiplayer.LobbyStatus;

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

    /*
    /**
     * Adds a player wishing to join the game to the lobby, given that there is only
     * one player in the lobby
     *
    @Override
     public void addPlayerTwoToLobby(String code, String username) {
        //TODO: Check that the lobby exists
        //TODO: Check that the lobby is not already full
         System.out.println(lobbiesRef.child(String.valueOf(code)).get());
         lobbiesRef.child(code).child("playerTwo").setValue(username);
    }
    */

    @Override
    public void addPlayerTwoToLobby(String code, String username) {
        lobbiesRef.child(code).addListenerForSingleValueEvent(new ValueEventListener() {
            /**
             * This method will be called with a snapshot of the data at this location. It will also be called
             * each time that data changes.
             *
             * @param snapshot The current data at the location
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // the lobby exists
                    Log.d("firebase addP2toL:", "The lobby exists");
                    if (!snapshot.hasChild("playerTwo")) {
                        Log.d("firebase addP2toL:", "The lobby is available");
                        lobbiesRef.child(code).child("playerTwo").setValue(username);
                        //ControllerManager.getInstance().push(new JoinLobbyController());
                        LobbyInfo.getInstance().setLobbyStatus(LobbyStatus.AVAILABLE);
                        
                    } else {
                        Log.d("firebase addP2toL:", "The lobby is occupied");
                        //throw new IllegalStateException("The lobby is occupied");
                        LobbyInfo.getInstance().setLobbyStatus(LobbyStatus.OCCUPIED);
                    }
                } else {
                    Log.d("firebase addP2toL:", "The lobby does not exists");
                    //throw new IllegalArgumentException("The lobby does not exists");
                    LobbyInfo.getInstance().setLobbyStatus(LobbyStatus.NONEXISTENT);
                }
            }

            /**
             * This method will be triggered in the event that this listener either failed at the server, or
             * is removed as a result of the security and Firebase Database rules. For more information on
             * securing your data, see: <a
             * href="https://firebase.google.com/docs/database/security/quickstart" target="_blank"> Security
             * Quickstart</a>
             *
             * @param error A description of the error that occurred
             */
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("firebase addP2toL", error.toException());
            }
        });
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

    /**
     * Read from firebase the name of player two and store it locally
     * @param code The invite code of a lobby
     */
    @Override
    public void createPlayerTwoListener(String code) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                String playerTwo= dataSnapshot.getValue(String.class);
                Log.d("firebase createP2List:", "The local player two is: " + playerTwo);
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

    /**
     * Read from firebase the name of player one and store it locally
     * @param code The invite code of a lobby
     */
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
