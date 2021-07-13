package com.zidney.jidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zidney.jidquiz.model.ResultsModel;
import com.zidney.jidquiz.model.Results;
import com.zidney.jidquiz.retrofit.ApiEndpoint;
import com.zidney.jidquiz.retrofit.ApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizActivity extends AppCompatActivity {

    private final String TAG = "QuizActivity";
    private static final long COUNTDOWN_IN_MILIS = 30000;
    private static final String EXTRA_SCORE = "extrascore";

    private List<Results> resultsList;
    private ResultsModel resultsModels;
    private RadioGroup rg_ans;
    private RadioButton rb_ans1, rb_ans2, rb_ans3, rb_ans4;
    private Button btn_next;
    private TextView tv_question, tv_time, tv_score, tv_amount, tv_difficulty, tv_category2;

    private int score;
    private int counter, category;
    private int quiztotal = 10;

    private String difficulty;
    private String answer1,answer2,answer3,answer4;

    private boolean answered;
    private long timeleft;
    private CountDownTimer countDownTimer;

    private ColorStateList TCdefaulttime;

    ApiEndpoint apiEndpoint = ApiService.getClient().create(ApiEndpoint.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tv_time = findViewById(R.id.tv_time);
        TCdefaulttime = tv_time.getTextColors();
        tv_amount = findViewById(R.id.tv_amountquest);
        tv_score = findViewById(R.id.tv_score);
        tv_difficulty = findViewById(R.id.tv_difficult);
        tv_category2 = findViewById(R.id.tv_category2);
        tv_question = findViewById(R.id.tv_question);

        rg_ans = findViewById(R.id.rg_answer);
        rb_ans1 = findViewById(R.id.rb_ans1);
        rb_ans2 = findViewById(R.id.rb_ans2);
        rb_ans3 = findViewById(R.id.rb_ans3);
        rb_ans4 = findViewById(R.id.rb_ans4);
        btn_next = findViewById(R.id.btn_next);



        Intent mIntent = getIntent();
        category = mIntent.getIntExtra("CATEGORY", 0);
        difficulty = mIntent.getStringExtra("DIFFICULTY");
        Log.d(TAG, "onCreate: "+ category + difficulty);

        tv_difficulty.setText(difficulty);
        tv_category2.setText(DisplayCategory());
        btn_next.setText("Confirm");

        getQuizGK();


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    if(rb_ans1.isChecked() || rb_ans2.isChecked() || rb_ans3.isChecked() || rb_ans4.isChecked()){
                        CheckAnswers();
                    }else {
                        Toast.makeText(QuizActivity.this, "Please select the Answer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Showquestion();
                }
            }
        });

    }


    private void getQuizGK(){
        Call<ResultsModel> Call = apiEndpoint.getTriviaQuiz(1, category, difficulty, "multiple");
        Call.enqueue(new Callback<ResultsModel>() {
            @Override
            public void onResponse(Call<ResultsModel> call, Response<ResultsModel> response) {
                if (!response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.code());
                }
                ResultsModel resultsModel = response.body();

                tv_question.setText(Html.fromHtml(resultsModel.getResults().get(0).getQuestion()));

                answer1 = resultsModel.getResults().get(0).getIncorrectAnswers().get(0);
                answer2 = resultsModel.getResults().get(0).getIncorrectAnswers().get(1);
                answer3 = resultsModel.getResults().get(0).getIncorrectAnswers().get(2);
                answer4 = resultsModel.getResults().get(0).getCorrectAnswer();

                List<String> choices = Arrays.asList(answer1, answer2, answer3, answer4);
                Collections.shuffle(choices);

                tv_amount.setText("Quiz: " + counter + "/" + quiztotal);
                tv_score.setText("score: "+ score);

                rb_ans1.setText(Html.fromHtml(choices.get(0)));
                rb_ans2.setText(Html.fromHtml(choices.get(1)));
                rb_ans3.setText(Html.fromHtml(choices.get(2)));
                rb_ans4.setText(Html.fromHtml(choices.get(3)));
                Log.d(TAG, "onResponse: "+ answer4);
                //Log.d(TAG, "onResponse: " + answer.getText());




            }

            @Override
            public void onFailure(Call<ResultsModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + "failed coy");
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        timeleft = COUNTDOWN_IN_MILIS;
        StartCountDown();
    }

    private String DisplayCategory(){
        String kategori = null;
        if(category == 9)
        {
            kategori = "General Knowledge";
        }
        if (category == 11)
        {
            kategori = "Film";
        }
        if(category == 17){
            kategori = "Science & Nature";
        }
        if(category == 20){
            kategori = "Mythology";
        }
        return kategori;
    }

    private void CheckAnswers(){
        answered = true;

        countDownTimer.cancel();

        int selectedId = rg_ans.getCheckedRadioButtonId();
        RadioButton answer = findViewById(selectedId);

//        Log.d(TAG, "CheckAnswers: " + answer.getText().toString());
//        Log.d(TAG, "CheckAnswers: " + answer4);


        Log.d(TAG, "CheckAnswers: " + answer);

       if(answer != null){
           if(answer4.equals(answer.getText().toString())){
               if(timeleft > 20000 ){
                   score += 3;
               }
               else if(timeleft > 10000 && timeleft <= 20000){
                   score += 2;
               }else{
                   score += 1;
               }
               tv_score.setText("score: "+ score);
            Log.d(TAG, "onCreate: score" + score);
           }
       }
       showSolution();
    }

    private void showSolution(){
//        rb_ans1.setBackgroundColor(Color.RED);
//        rb_ans2.setBackgroundColor(Color.RED);
//        rb_ans3.setBackgroundColor(Color.RED);
//        rb_ans4.setBackgroundColor(Color.RED);
//        switch (answer4) {
//            case 1:
//                rb_ans1.setTextColor(Color.GREEN);
//                tv_question.setText("Correct Answer is "+answer4);
//                break;
//            case 2:
//                rb_ans2.setTextColor(Color.GREEN);
//                tv_question.setText("Correct Answer is "+answer4);
//                break;
//            case 3:
//                rb_ans3.setTextColor(Color.GREEN);
//                tv_question.setText("Correct Answer is "+answer4);
//                break;
//            case 4:
//                rb_ans4.setTextColor(Color.GREEN);
//                tv_question.setText("Correct Answer is "+answer4);
//                break;
//        }
        tv_question.setText(Html.fromHtml("Correct Answer is " + answer4));

        if (counter < quiztotal) {
            btn_next.setText("Next");
        } else {
            btn_next.setText("Finish");
        }

    }

    private void Showquestion(){
        tv_question.clearComposingText();
        rg_ans.clearCheck();

        if(counter < quiztotal){
            getQuizGK();
            counter++;
            tv_amount.setText("Quiz: " + counter + "/" + quiztotal);
            answered = false;
            btn_next.setText("Confirm");

//            timeleft = COUNTDOWN_IN_MILIS;
//            StartCountDown();

        }else {
            finishQuiz(score, category);
        }
    }

    private void StartCountDown() {
        countDownTimer = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft = millisUntilFinished;
                UpdateCountDownText();
            }

            @Override
            public void onFinish() {
                timeleft = 0;
                UpdateCountDownText();
                CheckAnswers();

            }
        }.start();
    }

    private void UpdateCountDownText() {
        int minutes = (int) (timeleft / 1000) / 60;
        int seconds = (int) (timeleft / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tv_time.setText(timeFormatted);
        if (timeleft < 10000) {
            //Toast.makeText(QuizActivity.this,"Please select the answer or application will crash :(", Toast.LENGTH_SHORT).show();
            tv_time.setTextColor(Color.RED);
        } else {
            tv_time.setTextColor(TCdefaulttime);
        }
    }

    private void finishQuiz(int hiscore, int category){
        Intent scoreIntent = new Intent(QuizActivity.this, HighscoreActivity.class);
        scoreIntent.putExtra("HISCORE", hiscore);
        scoreIntent.putExtra("HICATEGORY", category);
        startActivity(scoreIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}