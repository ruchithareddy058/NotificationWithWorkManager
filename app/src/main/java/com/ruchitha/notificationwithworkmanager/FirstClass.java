package com.ruchitha.notificationwithworkmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;
//import static com.ruchitha.notificationwithworkmanager.MainActivity.channel_ID;

public class FirstClass extends Worker {
    NotificationManager nm;
    PendingIntent pi;
    Intent i;
    public static final String channel_ID="ruchitha.reddy";
    public FirstClass(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        nm= (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        i=new Intent(getApplicationContext(),MainActivity.class);
        pi=PendingIntent.getActivity(getApplicationContext(),123,i,PendingIntent.FLAG_UPDATE_CURRENT);
        createChannel();
        showMyNotification();
        return Result.success();
    }
    private void createChannel() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel nc=new NotificationChannel(channel_ID,"ruchitha", NotificationManager.IMPORTANCE_HIGH);
            nc.setLightColor(Color.BLUE);
            nc.enableVibration(true);
            nm.createNotificationChannel(nc);
        }
    }
    public void showMyNotification() {
       NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),channel_ID);
        builder.setContentTitle("This is my notification");
        builder.setContentText("Hi baby...How are you???");
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
        builder.setDefaults(Notification.DEFAULT_ALL);
        nm.notify(578,builder.build());


    }
}
