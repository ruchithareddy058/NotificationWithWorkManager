package com.ruchitha.notificationwithworkmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    OneTimeWorkRequest one;
    PeriodicWorkRequest repeatedwork;
/*NotificationManager nm;
PendingIntent pi;
Intent i;
public static final String channel_ID="ruchitha.reddy";*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one=new OneTimeWorkRequest.Builder(FirstClass.class).build();
        repeatedwork=new PeriodicWorkRequest.Builder(SecondClass.class,3, TimeUnit.MINUTES).build();
        /*nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        i=new Intent(this,MainActivity.class);
        pi=PendingIntent.getActivity(this,123,i,PendingIntent.FLAG_UPDATE_CURRENT);
        createChannel();*/
    }

   /* private void createChannel() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel nc=new NotificationChannel(channel_ID,"ruchitha",NotificationManager.IMPORTANCE_HIGH);
            nc.setLightColor(Color.BLUE);
            nc.enableVibration(true);
            nm.createNotificationChannel(nc);
        }
    }*/

    public void showMyNotification(View view) {
        WorkManager.getInstance(this).enqueue(one);
        WorkManager.getInstance(this).enqueue(repeatedwork);
       /* NotificationCompat.Builder builder=new NotificationCompat.Builder(this,channel_ID);
        builder.setContentTitle("This is my notification");
        builder.setContentText("Hi baby...How are you???");
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
        builder.setDefaults(Notification.DEFAULT_ALL);
        nm.notify(578,builder.build());*/


    }
}