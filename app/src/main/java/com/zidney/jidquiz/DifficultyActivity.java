package com.zidney.jidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DifficultyActivity extends AppCompatActivity {

    private Button btn_easy, btn_medium, btn_hard;
    private TextView tv_category;
    private int category;
    private String kategori;
    private static final String TAG = "DifficultyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        btn_easy = findViewById(R.id.btn_easy);
        btn_medium = findViewById(R.id.btn_medium);
        btn_hard = findViewById(R.id.btn_hard);
        tv_category = findViewById(R.id.tv_category);

        Intent mIntent = getIntent();
        category = mIntent.getIntExtra("CATEGORY", 0);
        Log.d(TAG, "onCreate: "+ category);

        tv_category.setText(DisplayCategory());



        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("easy",category);
            }
        });

        btn_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("medium", category);
            }
        });

        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("hard", category);
            }
        });

    }

    private void startQuiz(String difficulty, int category) {
        Intent intent = new Intent(DifficultyActivity.this, QuizActivity.class);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("DIFFICULTY", difficulty);
        startActivity(intent);
    }

    private String DisplayCategory(){
        if(category == 9)
        {
            kategori = "General Knowloge";
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

}