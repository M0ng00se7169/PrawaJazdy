package com.example.prawajazdy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    public static final int SETTINGS = 1;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    public static List<Pytanie> questionList;
    FirebaseStorage storage;
    StorageReference storageReference;

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
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void question(View view) {
        Intent intent = new Intent(MenuActivity.this, QuestionActivity.class);
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
            int napis1 = dane.getInt("Value");
            System.out.println("Dane z listy " + napis1);
        }
    }
}