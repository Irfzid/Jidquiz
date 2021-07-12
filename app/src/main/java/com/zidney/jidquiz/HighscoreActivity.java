package com.zidney.jidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {

    private Button btn_mainmenu, btn_replay;
    private TextView tv_Hiscore;
    int hiscore, hicategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        btn_mainmenu = findViewById(R.id.btn_mainmenu);
        btn_replay = findViewById(R.id.btn_replay);
        tv_Hiscore = findViewById(R.id.tv_hiscore);

        Intent hIntent = getIntent();
        hiscore = hIntent.getIntExtra("HISCORE", 0);
        hicategory = hIntent.getIntExtra("HICATEGORY", 0);

        tv_Hiscore.setText(Integer.toString(hiscore));

        btn_mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighscoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReplay(hicategory);
            }
        });

    }

    private void startReplay(int category){
        Intent mIntent = new Intent(HighscoreActivity.this, DifficultyActivity.class);
        mIntent.putExtra("CATEGORY", category);
        startActivity(mIntent);
    }
}