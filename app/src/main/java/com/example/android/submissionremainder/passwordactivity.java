package com.example.android.submissionremainder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class passwordactivity extends AppCompatActivity {
    private EditText etmail;
    private Button forpassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordactivity);
        etmail = (EditText)findViewById(R.id.etmail);
        forpassword = (Button) findViewById(R.id.forpassword);

        firebaseAuth= FirebaseAuth.getInstance();
        etmail = (EditText)findViewById(R.id.etmail);
        forpassword = (Button) findViewById(R.id.forpassword);

        firebaseAuth= FirebaseAuth.getInstance();


        forpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = etmail.getText().toString().trim();

                if (useremail.equals("")){
                    Toast.makeText(passwordactivity.this ,"please enter the Correct email Id" ,Toast.LENGTH_SHORT).show();


                }
                else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(passwordactivity.this ," password resent email has been sent" ,Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(passwordactivity.this ,login.class));



                            }
                            else
                            {

                                Toast.makeText(passwordactivity.this ,"Not  registerd  email Id" ,Toast.LENGTH_SHORT).show();


                            }

                            }
                        });
                    }
                }


        });
    }
}
