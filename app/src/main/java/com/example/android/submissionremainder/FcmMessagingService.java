package com.example.android.submissionremainder;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            String Title = remoteMessage.getData().get("Title");
            String Message = remoteMessage.getData().get("Message");
            Intent intent = new Intent("com.example.android.submissionremainder_FCM-MESSAGE");
            intent.putExtra("Title", Title);
            intent.putExtra("Message", Message);
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
            localBroadcastManager.sendBroadcast(intent);


        }
    }
}