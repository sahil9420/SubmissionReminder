package com.example.android.submissionremainder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuizMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);
    }

    public void openQuizHome (View view){
        Intent startQuizhome = new Intent(this, QuizHome.class);
        startActivity(startQuizhome);
    }
    public void openRankingHome (View view){
        Intent startRankingHome = new Intent(this, RankingHome.class);
        startActivity(startRankingHome);
    }
}
