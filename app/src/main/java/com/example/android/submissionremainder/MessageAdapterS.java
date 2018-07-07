package com.example.android.submissionremainder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.Calendar;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class MessageAdapterS extends ArrayAdapter<AdminContent> {


    public MessageAdapterS(Context context, int resource, List<AdminContent> objects) {
        super(context, resource, objects);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }


        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        com.example.android.submissionremainder.AdminContent message = getItem(position);



            messageTextView.setVisibility(View.VISIBLE);

            messageTextView.setText(message.getText());

        authorTextView.setText(message.getName());

        return convertView;
    }



}
