package com.example.prawajazdy;

    import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.firebase.auth.FirebaseAuth;
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

public class MainActivity extends AppCompatActivity {

    TextView pytanie, odpowiedz;
    ImageView obrazek;
    ListView listViewPytania;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    List<Pytanie> pytanieList;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = FirebaseStorage.getInstance();
        obrazek = findViewById(R.id.obrazek);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        new FirebaseDatabaseHelper().readPytania(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Pytanie> pytania, List<String> keys) {
                new RecyclerViewConfig().setConfig(recyclerView, MainActivity.this, pytania, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    }