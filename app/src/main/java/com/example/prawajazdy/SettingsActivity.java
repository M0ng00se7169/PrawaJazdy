package com.example.prawajazdy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    int step = 1;
    int min = 1;
    int countOfRepeats = 1;
    int countOfRepeatsError = 1;
    int countOfQuestions = 10;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView textView = findViewById(R.id.textView10);
        TextView textView1 = findViewById(R.id.textView14);
        TextView textView2 = findViewById(R.id.textView18);
        SeekBar seekBar = findViewById(R.id.countOfRepeat);
        SeekBar seekBar1 = findViewById(R.id.seekBar1);
        SeekBar seekBar2 = findViewById(R.id.seekBar2);
        Button save = findViewById(R.id.zachowajBtn);

        textView.setText("1");
        textView1.setText("1");
        textView2.setText("1");
        seekBar.setMax(9);
        seekBar1.setMax(9);
        seekBar2.setMax(49);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                countOfRepeats = min + (progress * step);
                textView.setText(String.valueOf(countOfRepeats));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                countOfRepeatsError = min + (progress * step);
                textView1.setText(String.valueOf(countOfRepeatsError));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                countOfQuestions = min + (progress * step);
                textView2.setText(String.valueOf(countOfQuestions));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // System.out.println(countOfRepeats);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();

                bundle.putInt("countOfRepeats", countOfRepeats);
                bundle.putInt("countOfRepeatsError", countOfRepeatsError);
                bundle.putInt("countOfQuestions", countOfQuestions);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}