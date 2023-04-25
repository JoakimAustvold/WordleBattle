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
import com.mygdx.game.model.states.multiplayer.LobbyInfoState;
import com.mygdx.game.model.states.multiplayer.CurrentPlayer;
import com.mygdx.game.model.states.multiplayer.LobbyStatus;
import java.util.ArrayList;
import java.util.Arrays;
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
                        LobbyInfoState.getInstance().setLobbyStatus(LobbyStatus.AVAILABLE);
                        
                    } else {
                        Log.d("firebase addP2toL:", "The lobby is occupied");
                        //throw new IllegalStateException("The lobby is occupied");
                        LobbyInfoState.getInstance().setLobbyStatus(LobbyStatus.OCCUPIED);
                    }
                } else {
                    Log.d("firebase addP2toL:", "The lobby does not exists");
                    //throw new IllegalArgumentException("The lobby does not exists");
                    LobbyInfoState.getInstance().setLobbyStatus(LobbyStatus.NONEXISTENT);
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
                LobbyInfoState.getInstance().setPlayerTwo(playerTwo);
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
                LobbyInfoState.getInstance().setPlayerOne(playerOne);
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

    @Override
    public void submitWord(String code, String player, String word) {
        lobbiesRef.child(code).child(player+"Wordlist").setValue(new ArrayList<String>(Arrays.asList(word)));
    }

    @Override
    public void getWordSubmitted (String code) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> playerWords = (ArrayList<String>) dataSnapshot.getValue();
                Log.d("firebase newWordList: ", "The word list is " + playerWords);
                if (playerWords != null) {
                    if (LobbyInfoState.getInstance().getCurrentPlayer() == CurrentPlayer.PLAYERONE) {
                        LobbyInfoState.getInstance().setPlayerTwoWordlist(playerWords);
                    } else {
                        LobbyInfoState.getInstance().setPlayerOneWordlist(playerWords);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadWords:onCancelled", databaseError.toException());
            }
        };

        if (LobbyInfoState.getInstance().getCurrentPlayer() == CurrentPlayer.PLAYERONE) {
            lobbiesRef.child(code).child(CurrentPlayer.PLAYERTWO.label+"Wordlist").addValueEventListener(postListener);
        } else if (LobbyInfoState.getInstance().getCurrentPlayer() == CurrentPlayer.PLAYERTWO){
            lobbiesRef.child(code).child(CurrentPlayer.PLAYERONE.label+"Wordlist").addValueEventListener(postListener);
        }
    }

    @Override
    public void submitMultiplayerScore(String code, CurrentPlayer player, Integer score) {
        lobbiesRef.child(code).child(player.label +"Score").setValue(score);
    }

    @Override
    public void getMultiplayerScore(String code) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Integer score = dataSnapshot.getValue(Integer.class);
                if (LobbyInfoState.getInstance().getCurrentPlayer() == CurrentPlayer.PLAYERONE) {
                    LobbyInfoState.getInstance().setPlayerTwoScore(score);
                    Log.d("firebase MultiScores: ", "PlayerTwo's score is: " + score);
                } else if (LobbyInfoState.getInstance().getCurrentPlayer() == CurrentPlayer.PLAYERTWO) {
                    LobbyInfoState.getInstance().setPlayerOneScore(score);
                    Log.d("firebase MultiScores: ", "PlayerOne's score is: " + score);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("MultiScore:onCancelled", databaseError.toException());
            }
        };

        if (LobbyInfoState.getInstance().getCurrentPlayer() == CurrentPlayer.PLAYERONE) {
            lobbiesRef.child(code).child(CurrentPlayer.PLAYERTWO.label+"Score").addValueEventListener(postListener);
        } else if (LobbyInfoState.getInstance().getCurrentPlayer() == CurrentPlayer.PLAYERTWO){
            lobbiesRef.child(code).child(CurrentPlayer.PLAYERONE.label+"Score").addValueEventListener(postListener);
        }
    }

    @Override
    public void setOnlineLobbyStatus(String code, LobbyStatus lobbyStatus) {
        lobbiesRef.child(code).child("onlineLobbyStatus").setValue(lobbyStatus);
    }

    @Override
    public void getOnlineLobbyStatus(String code) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Log.d("firebase LobbyStatus: ", "******* I AM READING ******** ");
                LobbyStatus lobbyStatus = dataSnapshot.getValue(LobbyStatus.class);
                Log.d("firebase LobbyStatus: ", "Lobby status is: " + lobbyStatus);
                if (lobbyStatus != null){
                    LobbyInfoState.getInstance().setLobbyStatus(lobbyStatus);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("lobbyStatus:onCancelled", databaseError.toException());
            }
        };
        lobbiesRef.child(code).child("onlineLobbyStatus").addValueEventListener(postListener);
    }
}
