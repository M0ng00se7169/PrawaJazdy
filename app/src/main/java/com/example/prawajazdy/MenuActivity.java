package com.example.prawajazdy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    public static final int SETTINGS = 1;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    public static List<Pytanie> questionList;
    FirebaseStorage storage;
    StorageReference storageReference;

    int countOfRepeats = 1;
    int countOfRepeatsError = 1;
    int countOfQuestions = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference("Tresc_pytania");

        questionList = new ArrayList<>();
        storage = FirebaseStorage.getInstance();
        getData();
    }

    public void egzamin(View view) {
        Intent intent = new Intent(MenuActivity.this, QuestionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("Czy_egzamin", true);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void testy(View view) {
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void question(View view) {
        Intent intent = new Intent(MenuActivity.this, QuestionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("countOfRepeats", countOfRepeats);
        bundle.putInt("countOfRepeatsError", countOfRepeatsError);
        bundle.putInt("countOfQuestions", countOfQuestions);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void settings(View view) {
        Intent intent = new Intent(MenuActivity.this, SettingsActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startActivityForResult(intent, SETTINGS);
    }

     public void getData() {
         mDatabaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 questionList.clear();
                 for (DataSnapshot keyNode : snapshot.getChildren()) {
                     Pytanie pytanie = keyNode.getValue(Pytanie.class);
                     questionList.add(pytanie);
                     System.out.println();
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

     }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SETTINGS) {
            Bundle dane = data.getExtras();
            countOfRepeats = dane.getInt("countOfRepeats");
            countOfRepeatsError = dane.getInt("countOfRepeatsError");
            countOfQuestions = dane.getInt("countOfQuestions");
            System.out.println("Dane z listy " + countOfRepeats);
        }
    }
}