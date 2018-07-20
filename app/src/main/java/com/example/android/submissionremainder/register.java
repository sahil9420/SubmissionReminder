package com.example.android.submissionremainder;

import android.app.ActionBar;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.submissionremainder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    String name ,username ,Branch ,Id,pass ,mobile;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    private EditText ename, eid, euser2, epass2,emobile;
    private Button eregister;
    private FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        eregister = (Button) findViewById(R.id.eregister);
        ename = (EditText) findViewById(R.id.ename);
        eid = (EditText) findViewById(R.id.eid);
        epass2 = (EditText) findViewById(R.id.epass2);
        euser2 = (EditText) findViewById(R.id.euser2);
        spinner = (Spinner)findViewById(R.id.spinner);
        emobile =(EditText) findViewById(R.id.emobile);
        firebaseauth= FirebaseAuth.getInstance();
        adapter= ArrayAdapter.createFromResource(register.this ,R.array.branch_arrays,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        eregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                { String useremail=euser2.getText().toString().trim();
                    String pass =epass2.getText().toString().trim();


                    firebaseauth.createUserWithEmailAndPassword(useremail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendEmailVerification();

                            }
                            else
                                Toast.makeText(register.this,"registration unsuccessful",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }
    private  Boolean validate()
    { Boolean result= false;
        name = ename.getText().toString();
        pass = epass2.getText().toString();
        username = euser2.getText().toString();
        Branch =spinner.getSelectedItem().toString();
        Id = eid.getText().toString();
        mobile =emobile.getText().toString();






        if(username.isEmpty() || pass.isEmpty() || name.isEmpty() || Branch.equals("SELECT YOUR BRANCH") || Id.isEmpty() || mobile.isEmpty())
        {
            Toast.makeText(this, "please fill alll credentials ",Toast.LENGTH_SHORT).show();
        }
        else { result=true;}
        return  result;
    }
    public void senduserdata(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference(firebaseauth.getUid());
        Userprofile userprofile = new Userprofile(name ,Id ,Branch , username ,mobile);
        myref.setValue(userprofile);

    }
    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseauth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        senduserdata();
                        Toast.makeText(register.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseauth.signOut();
                        finish();
                        startActivity(new Intent(register.this, login.class));
                    }else{
                        Toast.makeText(register.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }




}

