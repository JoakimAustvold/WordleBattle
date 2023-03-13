package com.mygdx.game;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AndriodAPI implements FirebaseAPI{

    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference myRef;

    public AndriodAPI() {
        database = FirebaseDatabase.getInstance("https://tdt4240-wordlebattle-default-rtdb.europe-west1.firebasedatabase.app/");
        System.out.println(database);
        myRef = database.getReference("message");
    }

    @Override
    public void updateAPI() {
        if(myRef != null) {
            myRef.setValue("Hello World!");
        } else {
            System.out.println("Database test failed");
        }
    }
}
