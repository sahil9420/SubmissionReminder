package com.example.android.submissionremainder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Done extends AppCompatActivity {


    Button tryAgainButton;
    TextView TextViewScore, gettxtResultQuestion;
    ProgressBar progressBar;
    FirebaseDatabase database;
    DatabaseReference questions_score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        database=FirebaseDatabase.getInstance();
        questions_score=database.getReference("Question_Score");
        TextViewScore=(TextView)findViewById(R.id.textTotalScore);
        gettxtResultQuestion=(TextView)findViewById(R.id.textTotalQuestion);
        progressBar=(ProgressBar)findViewById(R.id.doneProgressbar);
        tryAgainButton=(Button)findViewById(R.id.Trybtnplay);
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Done.this,QuizHome.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle extras =getIntent().getExtras();
        if (extras!=null){
           int score = extras.getInt("SCORE");
            int Totalquestion = extras.getInt("TOTAL");
            int CorrectAns = extras.getInt("CORRECT");
            TextViewScore.setText(String.format("SCORE : %d",score));
            gettxtResultQuestion.setText(String.format("PASSED %d/%d",CorrectAns,Totalquestion));
            progressBar.setMax(Totalquestion);
            progressBar.setProgress(CorrectAns);


        }

    }
}
