package com.example.liwensheng.broadcast;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetDemo extends AppWidgetProvider {

    final  String STATICATION = "com.example.liwensheng.broadcast.staticreceiver";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText =
                context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Intent intent = new Intent(context, MainPageActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
        remoteViews.setOnClickPendingIntent(R.id.widgetImg, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public  void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
        Bundle bundle = intent.getExtras();

        if (intent.getAction().equals(STATICATION)) {
            remoteViews.setTextViewText(R.id.appwidget_text, bundle.getString("fruitName"));
            remoteViews.setImageViewResource(R.id.widgetImg, bundle.getInt("fruitImg"));

            Intent intent1  = new Intent(context, MainPageActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1,0);
            remoteViews.setOnClickPendingIntent(R.id.widgetRelLayout, pendingIntent);

            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(),
                    WidgetDemo.class), remoteViews);
        }
    }
}

