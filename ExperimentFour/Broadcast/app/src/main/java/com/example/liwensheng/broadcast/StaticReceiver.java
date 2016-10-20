package com.example.liwensheng.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Created by liWensheng on 16/10/19.
 */

public class StaticReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final  String STATICATION = "com.example.liwensheng.broadcast.staticreceiver";

        if (intent.getAction().equals(STATICATION)) {
            Bundle bundle = intent.getExtras();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context);

            builder .setContentTitle("静态广播")
                    .setTicker("您有一条新消息")
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setContentText(bundle.getString("fruitName"))
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), bundle.getInt("fruitImg")))
                    .setSmallIcon(bundle.getInt("fruitImg"))
                    .setAutoCancel(true)
                    .setContentIntent(PendingIntent.getActivity(context,0,new Intent(context,MainPageActivity.class),0));

            Notification notify = builder.build();
            manager.notify(0,notify);
        }
    }
}
