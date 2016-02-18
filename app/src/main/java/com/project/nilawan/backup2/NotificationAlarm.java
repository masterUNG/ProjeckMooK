package com.project.nilawan.backup2;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class NotificationAlarm extends Activity {
    double motion;
    double motion2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control);
        Intent i = getIntent();
        motion = i.getDoubleExtra("motion",0);
        motion2 = i.getDoubleExtra("motion2",0);
        //double A = motion + 1;

        if (motion == 0){
            motion2 = motion;
            Intent intent = new Intent(NotificationAlarm.this, ControlActivity.class);
            startActivity(intent);
        }else if (motion ==1){
            if (motion == motion2){
                Intent intent = new Intent(NotificationAlarm.this, ControlActivity.class);
                startActivity(intent);
            }else if (motion != motion2){
                motion2 = motion;
                NotificationCompat.Builder notification = new NotificationCompat.Builder(NotificationAlarm.this);

                notification.setSmallIcon(R.drawable.red);
                notification.setTicker("New notification!!!");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("This is a new notification!!");
                notification.setContentText("Please check your bike!");
                notification.setAutoCancel(true);


                Uri sound = RingtoneManager.getDefaultUri(android.app.Notification.DEFAULT_SOUND);
                notification.setSound(sound);

                Bitmap picture = BitmapFactory.decodeResource(getResources(), R.mipmap.red);
                notification.setLargeIcon(picture);

                PendingIntent myPendingIntent;
                Intent myIntent = new Intent();
                Context myContext = getApplicationContext();

                myIntent.setClass(myContext, Notification.class);
                myIntent.putExtra("ID",1);

                myPendingIntent = PendingIntent.getActivities(myContext, 0, new Intent[]{myIntent}, 0);
                notification.setContentIntent(myPendingIntent);

                android.app.Notification n = notification.build();
                NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                nm.notify(1,n);

            }
        }

/*
        if (A == 2 && motion == 1){
            NotificationCompat.Builder notification = new NotificationCompat.Builder(NotificationAlarm.this);

            notification.setSmallIcon(R.drawable.red);
            notification.setTicker("New notification!!!");
            notification.setWhen(System.currentTimeMillis());
            notification.setContentTitle("This is a new notification!!");
            notification.setContentText("Please check your bike!");
            notification.setAutoCancel(true);


            Uri sound = RingtoneManager.getDefaultUri(android.app.Notification.DEFAULT_SOUND);
            notification.setSound(sound);

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.mipmap.red);
            notification.setLargeIcon(picture);

            PendingIntent myPendingIntent;
            Intent myIntent = new Intent();
            Context myContext = getApplicationContext();

            myIntent.setClass(myContext, Notification.class);
            myIntent.putExtra("ID",1);
            myPendingIntent = PendingIntent.getActivities(myContext ,0 , new Intent[]{myIntent},0);
            notification.setContentIntent(myPendingIntent);

            android.app.Notification n = notification.build();
            NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            nm.notify(1,n);

        }
        if (motion == 0){
            Intent intent = new Intent(NotificationAlarm.this, Notification.class);
            startActivity(intent);

        }
*/
    }

}
