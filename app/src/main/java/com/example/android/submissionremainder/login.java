package com.example.android.submissionremainder;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    private Button elogin;
    private EditText etextuser, etextpassword;
    private  TextView tvregister ,tfpass;
    private FirebaseAuth firebaseAuth;

    private CheckBox cbpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        elogin = (Button) findViewById(R.id.elogin);
        etextpassword = (EditText) findViewById(R.id.etextpassword);
        etextuser = (EditText) findViewById(R.id.etextuser);
        tvregister = (TextView) findViewById(R.id.tvregister);
        tfpass =(TextView) findViewById(R.id.tfpass);
        cbpass =(CheckBox) findViewById(R.id.cbpass) ;
        firebaseAuth = FirebaseAuth.getInstance();


        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(login.this, MainActivity.class));
        }

        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etextuser.getText().toString().trim();
                String password = etextpassword.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(login.this , "please fill username or password" ,Toast.LENGTH_SHORT).show();
                }
                else {


                    validate(etextuser.getText().toString(), etextpassword.getText().toString());
                }

            }
        });
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, register.class));
            }
        });
        tfpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(login.this, passwordactivity.class));



            }
        });


        cbpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                    etextpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                else
                    etextpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });


    }
    public void openAdmin (View view){
        Intent startAdmin = new Intent(this, Admin.class);
        startActivity(startAdmin);
    }

    private void validate(String username, String password) {
        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    checkEmailVerification();
                    // startActivity(new Intent(login.this, MainActivity.class));

                    // Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(login.this, "username or password  invalid", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        //  startActivity(new Intent(login.this, MainActivity.class));

        if(emailflag){

            finish();
            startActivity(new Intent(login.this, MainActivity.class));
        }else{
            Toast.makeText(login.this, "Verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }


}
