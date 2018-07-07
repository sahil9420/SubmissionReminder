package com.example.android.submissionremainder;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.submissionremainder.R;
import com.example.android.submissionremainder.chatMessage;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class discussion extends AppCompatActivity {
    private FirebaseListAdapter<chatMessage> adapter;

    FloatingActionButton fab ;
    RelativeLayout discussion ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        discussion =(RelativeLayout)findViewById(R.id.discussion);
        fab =(FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input =(EditText)findViewById(R.id.inputtype);
                FirebaseDatabase.getInstance().getReference().child("chats").push().setValue(new chatMessage(input.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");


            }
        });

        Toast.makeText(discussion.this,
                "Welcome" + FirebaseAuth.getInstance()
                        .getCurrentUser()
                        .getEmail(),
                Toast.LENGTH_SHORT)
                .show();
        displayChatMessages();

    }

    public void displayChatMessages() {
        ListView listofMessage = (ListView)findViewById(R.id.list_of_messages);
        Query query = FirebaseDatabase.getInstance().getReference().child("chats");
        FirebaseListOptions<chatMessage> options =
                new FirebaseListOptions.Builder<chatMessage>().setLayout(R.layout.list_item).setLifecycleOwner(discussion.this)
                        .setQuery(query, chatMessage.class)
                        .build();

        adapter = new FirebaseListAdapter<chatMessage>(options) {
            @Override
            protected void populateView(View v, chatMessage model, int position) {

                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };
        listofMessage.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
