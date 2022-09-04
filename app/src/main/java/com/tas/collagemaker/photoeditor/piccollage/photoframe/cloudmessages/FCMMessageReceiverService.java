package com.tas.collagemaker.photoeditor.piccollage.photoframe.cloudmessages;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;

import static com.tas.collagemaker.photoeditor.piccollage.photoframe.cloudmessages.App.FCM_CHANNEL_ID;


public class FCMMessageReceiverService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("MyTag","onMessageReceived");
        Log.d("MyTag","onMessageReceived"+remoteMessage.getFrom());
        if(remoteMessage.getNotification()!=null){
            String title=remoteMessage.getNotification().getTitle();
            String body=remoteMessage.getNotification().getBody();

            Notification notification = new NotificationCompat.Builder(this, FCM_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setColor(Color.GREEN)
                    .setStyle(new NotificationCompat.InboxStyle()
                            .addLine(remoteMessage.getData().toString()))
                        .setColor(Color.BLUE)
                    .build();

            NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(1002,notification);

        }
        if(remoteMessage.getData().size()>0){
            Log.d("MyTag","On Message"+remoteMessage.getData().toString());
        }
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.d("MyTag","On onDeletedMessages");
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("MyTag","onNewToken");
    }
}
