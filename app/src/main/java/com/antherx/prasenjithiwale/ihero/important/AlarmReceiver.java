package com.antherx.prasenjithiwale.ihero.important;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

import com.antherx.prasenjithiwale.ihero.activity.MainActivity;
import com.antherx.prasenjithiwale.ihero.R;

import java.util.Random;

/**
 * Created by Prasenjit Hiwale on 4/3/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    NotificationCompat.Builder notificationBuilder;
    int imageLocation;
    Random random = new Random();
    final int min = 1;
    final int max = 10;
    int ramdomNumber;
    @Override
    public void onReceive(Context context, Intent intent){


        ramdomNumber = random.nextInt(max - min)+min;

        if (ramdomNumber == 1){
            imageLocation = R.drawable.d1;
        }else if (ramdomNumber == 2){
            imageLocation = R.drawable.d2;
        }else if (ramdomNumber == 3){
            imageLocation = R.drawable.d3;
        }else if (ramdomNumber == 4){
            imageLocation = R.drawable.d4;
        }else if (ramdomNumber == 5){
            imageLocation = R.drawable.d5;
        }else if (ramdomNumber == 6){
            imageLocation = R.drawable.d6;
        }else if (ramdomNumber == 7){
            imageLocation = R.drawable.d7;
        }else if (ramdomNumber == 8){
            imageLocation = R.drawable.d8;
        }else if (ramdomNumber == 9){
            imageLocation = R.drawable.d9;
        }else if (ramdomNumber == 10){
            imageLocation = R.drawable.d10;
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);


        notificationBuilder = new NotificationCompat.Builder(context);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentTitle("Todays Refreshing Quote");
        notificationBuilder.setContentText("An Eye For an Eye Will Make the Whole World Blind");
        notificationBuilder.setTicker("Daily Quotes");

        Bitmap bitmapImage = BitmapFactory.decodeResource(context.getResources(),imageLocation);

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle().bigPicture(bitmapImage);
        bigPictureStyle.setSummaryText("Quote with picture");
        notificationBuilder.setStyle(bigPictureStyle);

        notificationManager.notify(0, notificationBuilder.build());

    }

}
