package com.antherx.prasenjithiwale.ihero.important;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

import com.antherx.prasenjithiwale.ihero.R;
import com.antherx.prasenjithiwale.ihero.activity.MainActivity;

/**
 * Created by Prasenjit Hiwale on 4/3/2017.
 */

public class myIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;
    NotificationCompat.Builder notificationBuilder;
    public myIntentService(){
        super("myIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent){
        notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentTitle("Todays Refreshing Quote");
        notificationBuilder.setContentText("An Eye For an Eye Will Make the Whole World Blind");
        notificationBuilder.setTicker("Daily Quotes");

        Bitmap bitmapImage = BitmapFactory.decodeResource(this.getResources(),R.drawable.step1);

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle().bigPicture(bitmapImage);
        bigPictureStyle.setSummaryText("Quote with picture");
        notificationBuilder.setStyle(bigPictureStyle);

        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);
        Notification notification = notificationBuilder.build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }
}
