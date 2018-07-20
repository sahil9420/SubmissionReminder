package com.example.android.submissionremainder;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Network;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
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
    private DrawerLayout dl ;
    private ActionBarDrawerToggle toggle ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        elogin = (Button) findViewById(R.id.elogin);
        etextpassword = (EditText) findViewById(R.id.etextpassword);
        etextuser = (EditText) findViewById(R.id.etextuser);
        tvregister = (TextView) findViewById(R.id.tvregister);
        tfpass =(TextView) findViewById(R.id.tfpass);
        cbpass =(CheckBox) findViewById(R.id.cbpass);
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



        //Items were not responding in click
        //dl = (DrawerLayout) findViewById(R.id.dl);
        //toggle = new ActionBarDrawerToggle(this,dl,R.string.open,R.string.close);
        //dl.addDrawerListener(toggle);
        //NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
       // nvDrawer.bringToFront();
        //toggle.syncState();
        //getSupportActionBar().setTitle("Submission Reminder");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setupDrawerContent(nvDrawer);


    }
    /*public void selectItemDrawer(MenuItem menuItem) {
        Fragment myFragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.drawermenu:
                fragmentClass = developer.class;
                break;

            default:
                fragmentClass = developer.class;
        }
        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent,myFragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        dl.closeDrawer(GravityCompat.START);


    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navigationView.bringToFront();
                selectItemDrawer(item);
                return true;
            }
        });
    }*/


    public void openAdmin (View view){
        Intent startAdmin = new Intent(this, Admin.class);
        startActivity(startAdmin);
    }

    public void openDeveloper (View view){
        Intent startDeveloper = new Intent(this, developerabout.class);
        startActivity(startDeveloper);
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
