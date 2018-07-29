package com.example.android.submissionremainder;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Common.Common;

public class Playing extends AppCompatActivity implements View.OnClickListener {
    final static long INTERVAL =1000; //1 sec
    final static long TIMEOUT =7000; //7 sec
    int progressValue =0;
    CountDownTimer countDownTimer;
    int index =0, score =0, thisQuestion=0,totalQuestion,CorrectAns;


    //lets start firebase
    FirebaseDatabase database;
    DatabaseReference questions;

    TextView question_text,text_score,text_question_no;
    ProgressBar progressBar;
    Button buttonA, buttonB, buttonC, buttonD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        //firebase
        database=FirebaseDatabase.getInstance();
        questions=database.getReference("Questions");

        //Views
        question_text=(TextView)findViewById(R.id.myTextquestion);
        text_score=(TextView)findViewById(R.id.txtscore);
        text_question_no=(TextView)findViewById(R.id.txtNoQuestions);

        progressBar=(ProgressBar)findViewById(R.id.progressBarQuiz);
        buttonA=(Button)findViewById(R.id.ButtonAnsA);
        buttonB=(Button)findViewById(R.id.ButtonAnsB);
        buttonC=(Button)findViewById(R.id.ButtonAnsC);
        buttonD=(Button)findViewById(R.id.ButtonAnsD);

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        countDownTimer.cancel();
       if (index < totalQuestion){
            Button mChecked =(Button)v;
            if(mChecked.getText().equals(Common.questionList.get(index).getCorrectAns())){
                score+=10;
                CorrectAns++;
                showQuestion(++index);//trigerring next question
            }else {

                //wrong ans
                Intent intent = new Intent(this,Done.class);
                Bundle datasend = new Bundle();
                datasend.putInt("SCORE",score);
                datasend.putInt("TOTAl",totalQuestion);
                datasend.putInt("CORRECT",CorrectAns);
                intent.putExtras(datasend);
                startActivity(intent);
                finish();
            }
            text_score.setText(String.format("%d",score));
       }

    }

    private void showQuestion(int i) {
        if(index < totalQuestion){
            thisQuestion++;
            text_question_no.setText(String.format("%d/%d",thisQuestion,totalQuestion));
            progressBar.setProgress(0);

            progressValue=0;
            question_text.setText(Common.questionList.get(index).getQuestion());
            question_text.setVisibility(View.VISIBLE);

            buttonA.setText(Common.questionList.get(index).getAnswerA());
            buttonB.setText(Common.questionList.get(index).getAnswerB());
            buttonC.setText(Common.questionList.get(index).getAnswerC());
            buttonD.setText(Common.questionList.get(index).getAnswerD());
            countDownTimer.start();

        }else {
            Intent intent = new Intent(this,Done.class);
            Bundle datasend = new Bundle();
            datasend.putInt("SCORE",score);
            datasend.putInt("TOTAl",totalQuestion);
            datasend.putInt("CORRECT",CorrectAns);
            intent.putExtras(datasend);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalQuestion=Common.questionList.size();

        countDownTimer= new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long minisec) {
                progressBar.setProgress(progressValue);
                progressValue++;

            }

            @Override
            public void onFinish() {
             countDownTimer.cancel();
             showQuestion(++index);
            }
        };
        showQuestion(++index);
    }
}
