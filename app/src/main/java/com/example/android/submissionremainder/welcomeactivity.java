package com.example.android.submissionremainder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class welcomeactivity extends AppCompatActivity {
    LinearLayout a11,a12;
    Button button;
    Animation uptodown,downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeactivity);
        button=(Button)findViewById(R.id.buttonEnter);
        a11=(LinearLayout) findViewById(R.id.a11);
        a12=(LinearLayout) findViewById(R.id.a12);
        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        a11.setAnimation(uptodown);
        downtoup= AnimationUtils.loadAnimation(this,R.anim.downtoup);
        a12.setAnimation(downtoup);
    }
    public void openLogin (View view){
        Intent startLogin = new Intent(this, login.class);
        startActivity(startLogin);
    }
}
