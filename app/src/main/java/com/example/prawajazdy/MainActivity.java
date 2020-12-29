package com.example.prawajazdy;

    import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.os.Bundle;
import android.util.Log;
    import android.view.Menu;
    import android.view.MenuInflater;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ImageView;
import android.widget.ListView;
    import android.widget.SearchView;
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
    EditText pytanieTresc;
    Button wyszukaj;
    List<Pytanie> lista;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    List<Pytanie> pytanieList;
    RecyclerViewConfig.PytaniaAdapter adapter;
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
                adapter = new RecyclerViewConfig().setConfig(recyclerView, MainActivity.this, pytania, keys);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}