package com.example.android.submissionremainder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

import Common.Common;
import model.Question;

public class QuizHome extends AppCompatActivity {

    Button btnplay;
     FirebaseDatabase database;
     DatabaseReference questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);

        database=FirebaseDatabase.getInstance();
        questions=database.getReference("Questions");
        loadQuestions(Common.CategoryID);
        btnplay=(Button)findViewById(R.id.btnplay);
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizHome.this,Playing.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadQuestions(String categoryID) {


        if(Common.questionList.size()>0){
            Common.questionList.clear();
        }
        questions.orderByChild("CategoryID").equalTo(categoryID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Question question= postSnapshot.getValue(Question.class);
                    Common.questionList.add(question);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Collections.shuffle(Common.questionList);
        }
    }

