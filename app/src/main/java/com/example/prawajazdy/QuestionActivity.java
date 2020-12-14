package com.example.prawajazdy;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView question, qCount, timer;
    private Button option1, option2, option3;
    private List<Pytanie> questionList;
    private int quesNum;
    private CountDownTimer countDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.question);
        qCount = findViewById(R.id.quest_num);
        timer = findViewById(R.id.countdown);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);

        getQuestionsList();
    }

    private void getQuestionsList() {
        questionList = new ArrayList<>();

        questionList.add(new Pytanie("", "1", "a", "b", "c", "c", "pytanie 1"));
        questionList.add(new Pytanie("", "2", "a", "b", "c", "b", "pytanie 2"));
        questionList.add(new Pytanie("", "3", "a", "b", "c", "c", "pytanie 3"));
        questionList.add(new Pytanie("", "4", "a", "b", "c", "a", "pytanie 4"));
        questionList.add(new Pytanie("", "5", "a", "b", "c", "a", "pytanie 5"));

        setQuestion();

    }

    @SuppressLint("SetTextI18n")
    private void setQuestion() {
        timer.setText(String.valueOf(10));

        question.setText(questionList.get(0).getPytanie());
        option1.setText(questionList.get(0).getOdpowiedz_A());
        option2.setText(questionList.get(0).getOdpowiedz_B());
        option3.setText(questionList.get(0).getOdpowiedz_C());

        qCount.setText(1 + "/" + questionList.size());

        startTimer();

        quesNum = 0;
    }

    private void startTimer() {
        countDown = new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished < 10000)
                    timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                changeQuestion();
            }
        };

        countDown.start();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        String selectedOption = "";

        switch (v.getId()) {
            case R.id.option1:
                selectedOption = "a";
                break;
            case R.id.option2:
                selectedOption = "b";
                break;
            case R.id.option3:
                selectedOption = "c";
                break;
        }
        countDown.cancel();
        checkAnswer(selectedOption, v);
    }

    private void checkAnswer(String selectedOption, View view) {
        if(selectedOption.equals(questionList.get(quesNum).getPoprawna_odp())) {
            //Right Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        }
        else {
            //Wrong Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            switch (questionList.get(quesNum).getPoprawna_odp()) {
                case "a":
                    option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case "b":
                    option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case "c":
                    option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(this::changeQuestion, 2000);
    }


    @SuppressLint("SetTextI18n")
    private void changeQuestion() {
        if( quesNum < questionList.size() - 1)
        {
            quesNum++;

            playAnim(question,0,0);
            playAnim(option1,0,1);
            playAnim(option2,0,2);
            playAnim(option3,0,3);

            qCount.setText((quesNum+1) + "/" + (questionList.size()));

            timer.setText(String.valueOf(10));
            startTimer();

        }
        else
        {
            // Go to Score Activity
            Intent intent = new Intent(QuestionActivity.this, MenuActivity.class);
            startActivity(intent);
            QuestionActivity.this.finish();
        }
    }

    private void playAnim(final View view, final int value, final int viewNum) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(value == 0) {
                            switch (viewNum) {
                                case 0:
                                    ((TextView)view).setText(questionList.get(quesNum).getPytanie());
                                    break;
                                case 1:
                                    ((Button)view).setText(questionList.get(quesNum).getOdpowiedz_A());
                                    break;
                                case 2:
                                    ((Button)view).setText(questionList.get(quesNum).getOdpowiedz_B());
                                    break;
                                case 3:
                                    ((Button)view).setText(questionList.get(quesNum).getOdpowiedz_C());
                                    break;
                            }

                            if(viewNum != 0)
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E99C03")));


                            playAnim(view,1,viewNum);

                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }
}