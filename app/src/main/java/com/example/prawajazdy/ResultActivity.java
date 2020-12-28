package com.example.prawajazdy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView score;
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score = findViewById(R.id.sa_score);
        done = findViewById(R.id.sa_done);

        Intent intent = getIntent();

        String score_str = intent.getStringExtra("WYNIK");
        score.setText(score_str);
//        int  wrong_str = intent.getIntExtra("ZLE_ODPOWIEDZI", 0);
//        String notApproved = "Nie zdane! Spróbuj jeszcze raz!";
//        if (wrong_str > 2) {
//            score.setText(notApproved);
//        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}