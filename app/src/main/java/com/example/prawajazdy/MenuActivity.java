package com.example.prawajazdy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    public static List<Pytanie> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference("Tresc_pytania");

        questionList = new ArrayList<>();

        getData();
    }

    public void egzamin(View view) {
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void question(View view) {
        Intent intent = new Intent(MenuActivity.this, QuestionActivity.class);
        startActivity(intent);
    }

     public void getData() {
         mDatabaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for (DataSnapshot keyNode : snapshot.getChildren()) {
                     Pytanie pytanie = keyNode.getValue(Pytanie.class);
                     questionList.add(pytanie);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

     }
}