package com.antherx.prasenjithiwale.ihero.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.antherx.prasenjithiwale.ihero.R;
import com.antherx.prasenjithiwale.ihero.important.AlarmReceiver;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView storyMain;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE;
    String currentTime;
    String Times = "TimePrefs";
    String timeKey = "SavedTime";
    String GetSavedTime;
    Intent alarmIntent;
    PendingIntent pendingIntent;
    private static final String TAG = "MainActivity";
    AdView mAdView;
    String story;
    Random random = new Random();
    int randomNumber;
    final int min = 1;
    final int max = 10;
    public String storyTell;
    public String storyTitle;
    TextView storyTitleView;
    private int imageMainRes;
    private ImageView mAsShowImage;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_recycle);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8463426768049995~9235012262");


        sharedPreferences = getApplicationContext().getSharedPreferences(Times, PRIVATE_MODE);
        editor = sharedPreferences.edit();
        editor.putString(timeKey, currentTime).apply();
        GetSavedTime = sharedPreferences.getString(timeKey,"");
        storyMain = (TextView)findViewById(R.id.mainStory);
        storyTitleView = (TextView)findViewById(R.id.storyTitle);
        mAsShowImage = (ImageView)findViewById(R.id.AsShowImage);
        back = (Button)findViewById(R.id.back);


        randomNumber = random.nextInt(max - min)+min;
        if (randomNumber == 1){
            storyTell = "Story_1.txt";
            storyTitle = "Story 1";
            imageMainRes = R.drawable.d1;
        }else if (randomNumber == 2){
            storyTell = "Story_2.txt";
            storyTitle = "Story 2";
            imageMainRes = R.drawable.d2;
        }else if (randomNumber == 3){
            storyTell = "Story_3.txt";
            storyTitle = "Story 3";
            imageMainRes = R.drawable.d3;
        }else if (randomNumber == 4){
            storyTell = "Story_4.txt";
            storyTitle = "Story 4";
            imageMainRes = R.drawable.d4;
        }else if (randomNumber == 5){
            storyTell = "Story_5.txt";
            storyTitle = "Story 5";
            imageMainRes = R.drawable.d5;
        }else if (randomNumber == 6){
            storyTell = "Story_6.txt";
            storyTitle = "Story 6";
            imageMainRes = R.drawable.d6;
        }else if (randomNumber == 7){
            storyTell = "Story_7.txt";
            storyTitle = "Story 7";
            imageMainRes = R.drawable.d7;
        }else if (randomNumber == 8){
            storyTell = "Story_8.txt";
            storyTitle = "Story 8";
            imageMainRes = R.drawable.d8;
        }else if (randomNumber == 9){
            storyTell = "Story_9.txt";
            storyTitle = "Story 9";
            imageMainRes = R.drawable.d9;
        }else if (randomNumber == 10){
            storyTell = "Story_10.txt";
            storyTitle = "Story 10";
            imageMainRes = R.drawable.d10;
        }else {
            storyTell = "Story_1.txt";
            storyTitle = "Story 1";
        }

        storyTitleView.setText(storyTitle);
        mAsShowImage.setImageResource(imageMainRes);


        Calendar calendar = Calendar.getInstance();
        if (!sharedPreferences.getBoolean("firstTime", false)) {

            alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY,9);
            calendar.set(Calendar.MINUTE,1);
            calendar.set(Calendar.SECOND,1);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            editor.putBoolean("firstTime", true);


        }
        try {
            InputStream inputStream = getAssets().open(storyTell);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            story = new String(buffer);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        storyMain.setText(story);
    }

    public void Backed(View view){

        Intent intent = new Intent(this, MainHome.class);
        startActivity(intent);

    }




}
