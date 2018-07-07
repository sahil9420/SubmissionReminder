package com.example.android.submissionremainder;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Admin  extends Activity {
    // User name
    private EditText et_Username;
    // Password
    private EditText et_Password;
    // Sign In
    private Button bt_SignIn;
    // Message
    private TextView tv_message;
    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialization
        et_Username = (EditText) findViewById(R.id.et_Username);
        et_Password = (EditText) findViewById(R.id.et_Password);
        bt_SignIn = (Button) findViewById(R.id.bt_SignIn);
        tv_message = (TextView) findViewById(R.id.tv_message);


        bt_SignIn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                // Stores User name
                username = String.valueOf(et_Username.getText());
                // Stores Password
                String password = String.valueOf(et_Password.getText());

                // Validates the User name and Password for admin, admin
                if (username.equals("adminExtc") && password.equals("9890507")) {
                    tv_message.setText("Login Successful!");
                    //last phase starting for inheritance
                    Intent intent= new Intent(Admin.this, Admin1.class);
                    startActivity(new Intent(Admin.this, Admin1.class));

                } else {
                    tv_message.setText("Login Unsuccessful!");
                }
            }
        });
    }

    public String getEt_Username() {
        return String.valueOf(et_Username.getText());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

}