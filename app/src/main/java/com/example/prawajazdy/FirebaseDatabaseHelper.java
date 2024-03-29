package com.example.prawajazdy;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    public static ArrayList<Pytanie> pytania = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<Pytanie> pytania, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        this.mDatabase = FirebaseDatabase.getInstance();
        this.mDatabaseReference = mDatabase.getReference("Tresc_pytania");
    }

    public void readPytania(final DataStatus dataStatus) {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pytania.clear();
                List<String> keys = new ArrayList<>();
                String message = snapshot.child("media").getValue(String.class);
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Pytanie pytanie = keyNode.getValue(Pytanie.class);
                    pytania.add(pytanie);
                }
                dataStatus.DataIsLoaded(pytania, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
