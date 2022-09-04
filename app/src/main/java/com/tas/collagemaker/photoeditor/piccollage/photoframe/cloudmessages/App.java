package com.tas.collagemaker.photoeditor.piccollage.photoframe.cloudmessages;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String  FCM_CHANNEL_ID="FCM_CHANNEL_ID";

    @Override
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel fcmChannel=new NotificationChannel(
                    FCM_CHANNEL_ID,
                    "FCM_Channel",
                    NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(fcmChannel);
        }
    }
}
