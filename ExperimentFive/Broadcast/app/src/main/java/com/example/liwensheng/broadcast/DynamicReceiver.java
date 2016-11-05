package com.example.liwensheng.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Created by liWensheng on 16/10/20.
 */

public class DynamicReceiver extends BroadcastReceiver {
    private static final String DYNAMICACTION = "com.example.liwensheng.broadcast.dynamic";

    @Override
    public void onReceive(Context context, Intent intent) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_demo);

        if(intent.getAction().equals(DYNAMICACTION))  {
            //广播
            Bundle bundle = intent.getExtras();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context);

            builder.setContentTitle("动态广播")
                    .setContentText(bundle.getString("mes"))
                    .setTicker("您有一条新消息")
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                            R.mipmap.dynamic))
                    .setSmallIcon(R.mipmap.dynamic)
                    .setAutoCancel(true)
                    .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MainPageActivity.class), 0));

            Notification notify = builder.build();
            manager.notify(0, notify);

            //在widget显示内容
            remoteViews.setTextViewText(R.id.appwidget_text, bundle.getString("mes"));
            remoteViews.setImageViewResource(R.id.widgetImg, R.mipmap.dynamic);

            //点击图片跳回主界面
            Intent intent1 = new Intent(context, MainPageActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
            remoteViews.setOnClickPendingIntent(R.id.widgetRelLayout, pendingIntent);

            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(),
                    WidgetDemo.class), remoteViews);
        }
    }
}
