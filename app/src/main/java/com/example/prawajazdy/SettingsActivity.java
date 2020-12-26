package com.example.prawajazdy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    int step = 1;
    int min = 1;
    int value = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView textView = findViewById(R.id.textView10);
        textView.setText("1");
        SeekBar seekBar = findViewById(R.id.countOfRepeat);
        Button save = findViewById(R.id.zachowajBtn);

        seekBar.setMax(9);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = min + (progress * step);
                textView.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        System.out.println(value);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();

                bundle.putInt("Value", value);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}