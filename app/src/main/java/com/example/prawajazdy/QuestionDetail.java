package com.example.prawajazdy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class QuestionDetail extends AppCompatActivity {

    ImageView imageView;
    TextView pytanie, odpowiedz;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        imageView = (ImageView) findViewById(R.id.media);
        pytanie = (TextView) findViewById(R.id.pytanie);
        odpowiedz = (TextView) findViewById(R.id.odpowedz);

        Intent intent = getIntent();

        String pathname = intent.getStringExtra("image_url");
        pytanie.setText(intent.getStringExtra("pytanie"));
        odpowiedz.setText(intent.getStringExtra("odpowiedz"));

        fetchImage(pathname);
    }


    public void fetchImage(String namePath) {
        storageReference = storage.getReferenceFromUrl("gs://naukaprawjazdy.appspot.com/").child(namePath.equals("") ? "1" : namePath);
        System.out.println(namePath);
        try {
            File file = File.createTempFile("image", "jpg");
            storageReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);
                }
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public void cofnij(View view) {
        finish();
    }
}