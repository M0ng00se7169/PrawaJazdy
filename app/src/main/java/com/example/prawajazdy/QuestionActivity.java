package com.example.prawajazdy;


import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView question, qCount, timer;
    private Button option1, option2, option3;
    private ImageView picture;
    private List<Pytanie> questionList;
    private int quesNum;
    private CountDownTimer countDown;
    private String media;
    int score;
    int wrongAnswers;


    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        storage = FirebaseStorage.getInstance();

        question = findViewById(R.id.question);
        qCount = findViewById(R.id.quest_num);
        timer = findViewById(R.id.countdown);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        picture = findViewById(R.id.image);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);

        score = 0;
        wrongAnswers = 0;

        getQuestionsList();
    }

    private void getQuestionsList() {

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            int questionTimes = bundle.getInt("countOfRepeats");
            int error = bundle.getInt("countOfRepeatsError");
            int countOfQuestions = bundle.getInt("countOfQuestions");
            System.out.println("Quiestion " + questionTimes + " Error " + error + " Ilosc pytan " + countOfQuestions);
            questionList = MenuActivity.questionList.subList(7, 11);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        setQuestion();
    }

    @SuppressLint("SetTextI18n")
    private void setQuestion() {
        timer.setText(String.valueOf(20));

        question.setText(questionList.get(0).getPytanie());

        option1.setText(questionList.get(0).getOdpowiedz_A());
        option2.setText(questionList.get(0).getOdpowiedz_B());
        option3.setText(questionList.get(0).getOdpowiedz_C());
        media = questionList.get(0).getMedia();

        qCount.setText(1 + "/" + questionList.size());

        startTimer();

        quesNum = 0;
    }

    private void startTimer() {
        countDown = new CountDownTimer(22000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished < 20000)
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
            score++;
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            System.out.println(score);
        }
        else {
            //Wrong Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            switch (questionList.get(quesNum).getPoprawna_odp()) {
                case "A":
                case "T":
                    option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    score++;
                    break;
                case "B":
                case "N":
                    option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    score++;
                    break;
                case "C":
                    option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    score++;
                    break;
            }
            wrongAnswers++;
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
            playAnim(picture, 0, 4);

            qCount.setText((quesNum+1) + "/" + (questionList.size()));

            timer.setText(String.valueOf(20));
            startTimer();

        }
        else
        {
            // Go to Score Activity
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            intent.putExtra("WYNIK", String.valueOf(score) + "/" + String.valueOf(questionList.size()));
            intent.putExtra("ZLE_ODPOWIEDZI", wrongAnswers);
            startActivity(intent);
        }
    }

    private void playAnim(final View view, final int value, final int viewNum) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(value == 0) {
                            switch (viewNum) {
                                case 0:
                                    ((TextView)view).setText(questionList.get(quesNum).getPytanie());
                                    break;
                                case 1:
                                    if (questionList.get(quesNum).getOdpowiedz_A().equals("")) {
                                        ((Button)view).setText("Tak");

                                    } else {
                                        ((Button)view).setText(questionList.get(quesNum).getOdpowiedz_A());
                                    }
                                    break;
                                case 2:
                                    if (questionList.get(quesNum).getOdpowiedz_B().equals("")) {
                                        ((Button)view).setText("Nie");

                                    } else {
                                        ((Button)view).setText(questionList.get(quesNum).getOdpowiedz_B());
                                    }
                                    break;
                                case 3:
                                    if (questionList.get(quesNum).getOdpowiedz_C().equals("")) {
                                        ((Button)view).setVisibility(View.INVISIBLE);
                                    } else {
                                        ((Button)view).setText(questionList.get(quesNum).getOdpowiedz_C());
                                        ((Button)view).setVisibility(View.VISIBLE);
                                    }
                                    break;
                                case 4:
                                    fetchImage(questionList.get(quesNum).getMedia());
                                    break;
                            }
                            if(viewNum != 0)
                                (view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E99C03")));
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

    public void fetchImage(String namePath) {
        storageReference = storage.getReferenceFromUrl("gs://naukaprawjazdy.appspot.com/").child(namePath.equals("") ? "1" : namePath);
        System.out.println(namePath);
        try {
            File file = File.createTempFile("image", "jpg");
            storageReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    picture.setImageBitmap(bitmap);
                }
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}