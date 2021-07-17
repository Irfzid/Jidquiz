package com.zidney.jidquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zidney.jidquiz.model.Tokens;
import com.zidney.jidquiz.retrofit.ApiEndpoint;
import com.zidney.jidquiz.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn_GK, btn_film, btn_sn, btn_geo;
    private String tokentrivia;
    private MediaPlayer mediaPlayer;
    private static final String TAG = "MainActivity";
    ApiEndpoint apiEndpoint = ApiService.getClient().create(ApiEndpoint.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        btn_GK = findViewById(R.id.btn_GK);
        btn_film = findViewById(R.id.btn_film);
        btn_sn = findViewById(R.id.btn_sn);
        btn_geo = findViewById(R.id.btn_myth);

        getToken();
        Log.d(TAG, "onCreate: " + tokentrivia);

        btn_GK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choosediff(9, tokentrivia);
            }
        });

        btn_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choosediff(11, tokentrivia);
            }
        });

        btn_sn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choosediff(17, tokentrivia);
            }
        });

        btn_geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choosediff(22, tokentrivia);
            }
        });

    }

    private void Choosediff(int category, String tokentrivia) {
        Intent intent = new Intent(MainActivity.this, DifficultyActivity.class);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("TOKEN_TRIVIA", tokentrivia);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finishAffinity();
    }

    private void getToken(){
        Call<Tokens> call = apiEndpoint.getTokenTrivia();
        call.enqueue(new Callback<Tokens>() {
            @Override
            public void onResponse(Call<Tokens> call, Response<Tokens> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.code());
                }
                Tokens tokens = response.body();

                tokentrivia = tokens.getToken();
                Log.d(TAG, "onResponse: token anda " + tokentrivia);
            }

            @Override
            public void onFailure(Call<Tokens> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

}