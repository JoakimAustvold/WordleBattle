package com.mygdx.game;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DesktopAPI implements FirebaseAPI {

    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference myRef;

    public DesktopAPI() {
        database = FirebaseDatabase.getInstance();
        database.getReference("message");
    }

    @java.lang.Override
    public void updateAPI() {
        if(myRef != null) {
            myRef.setValue("Hello world");
        } else {
            System.out.println("Database test failed");
        }
    }
}
