package com.zidney.jidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_GK, btn_film, btn_sn, btn_myth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_GK = findViewById(R.id.btn_GK);
        btn_film = findViewById(R.id.btn_film);
        btn_sn = findViewById(R.id.btn_sn);
        btn_myth = findViewById(R.id.btn_myth);

        btn_GK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choosediff(9);
            }
        });

        btn_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choosediff(11);
            }
        });

        btn_sn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choosediff(17);
            }
        });

        btn_myth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choosediff(20);
            }
        });

    }

    private void Choosediff(int category) {
        Intent intent = new Intent(MainActivity.this, DifficultyActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finishAffinity();
    }
}