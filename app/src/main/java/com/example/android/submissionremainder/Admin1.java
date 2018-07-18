package com.example.android.submissionremainder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Admin1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);

    }
    public void openNewG1 (View view){
        Intent startNewG1 = new Intent(this, NewG1.class);
        startActivity(startNewG1);
    }
    public void openNewS1 (View view){
        Intent startNewS1 = new Intent(this, NewS1.class);
        startActivity(startNewS1);
    }
    public void openNewE1 (View view){
        Intent startNewE1 = new Intent(this, NewE1.class);
        startActivity(startNewE1);
    }

    public void welcome (View view){
        Intent welcome = new Intent(this, welcomeactivity.class);
        startActivity(welcome);
    }

}
