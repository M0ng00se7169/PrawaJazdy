package com.example.prawajazdy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView score;
    private TextView label;
    private Button done;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score = findViewById(R.id.sa_score);
        done = findViewById(R.id.sa_done);
        label = findViewById(R.id.textView20);

        label.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int score_str = bundle.getInt("WYNIK");
        int size = bundle.getInt("ILOSC");
        int wrong = bundle.getInt("ZLE_ODPOWIEDZI");
        score.setText(score_str + "/" + size);
        System.out.println("WYNIK " + score_str);

        if (wrong > 2) {
            label.setVisibility(View.VISIBLE);
        }

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