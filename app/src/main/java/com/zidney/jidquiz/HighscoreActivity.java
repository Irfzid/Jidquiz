package com.zidney.jidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {
    private static final String TAG = "HighscoreActivity";

    private static final String SHARED_PREFS = "sharedprefs";
    private static final String KEY_HIGHSCORE = "keyhighscore";

    private Button btn_mainmenu, btn_replay, btn_reset;
    private TextView tv_Hiscore, tv_finalscore;
    int hiscore, hicategory, Finalscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        btn_mainmenu = findViewById(R.id.btn_mainmenu);
        btn_replay = findViewById(R.id.btn_replay);
        tv_finalscore = findViewById(R.id.tv_hiscore);
        tv_Hiscore = findViewById(R.id.tv_hiscore1);
        btn_reset = findViewById(R.id.btn_reset);

        Intent hIntent = getIntent();
        Finalscore = hIntent.getIntExtra("HISCORE", 0);
        hicategory = hIntent.getIntExtra("HICATEGORY", 0);

        tv_finalscore.setText(Integer.toString(Finalscore));

        loadHighscore();
        highscore();
        Log.d(TAG, "onCreate: Highscore " + hiscore);

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

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatehighscore(0);
            }
        });

    }

    private void startReplay(int category){
        Intent mIntent = new Intent(HighscoreActivity.this, DifficultyActivity.class);
        mIntent.putExtra("CATEGORY", category);
        startActivity(mIntent);
    }

    private void highscore(){
        if(Finalscore > hiscore){
            updatehighscore(Finalscore);

        }
    }

    private void updatehighscore(int highscorenew) {
        hiscore = highscorenew;
        tv_Hiscore.setText(Integer.toString(hiscore));

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscorenew);
        editor.apply();
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        hiscore = prefs.getInt(KEY_HIGHSCORE, 0);
        tv_Hiscore.setText(Integer.toString(hiscore));
    }
}