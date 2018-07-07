package com.example.android.submissionremainder;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private CardView logout,forum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth =FirebaseAuth.getInstance();
        logout = (CardView)findViewById(R.id.blogout);
        forum =(CardView) findViewById(R.id.bforum);
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , discussion.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this, login.class));

            }
        });

    }
    public void openNewS (View view){
        Intent startNewS = new Intent(this, NewS.class);
        startActivity(startNewS);
    }
    public void openNewE (View view){
        Intent startNewE = new Intent(this, NewE.class);
        startActivity(startNewE);
    }
    public void openNewG (View view){
        Intent startNewG = new Intent(this, NewG.class);
        startActivity(startNewG);
    }
    public void openDiscussion (View view){
        Intent startDiscussion = new Intent(this, discussion.class);
        startActivity(startDiscussion);
    }
}