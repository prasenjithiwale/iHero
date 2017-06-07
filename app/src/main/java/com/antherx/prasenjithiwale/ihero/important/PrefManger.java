package com.antherx.prasenjithiwale.ihero.important;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Prasenjit Hiwale on 4/2/2017.
 */

public class PrefManger {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context contextPref;

    //Shared Preferences Mode

    int PRIVATE_MODE = 0;

    //Shared Preferences Name

    private static final String PREF_NAME = "Motivation_Welcome";

    private static final String IS_WELCOME_SHOWS = "IS_WELCOME_REALLY_SHOWS";

    public PrefManger(Context context){
        this.contextPref = context;
        preferences = contextPref.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setWelcomeLaunch(boolean isWelcome){
        editor.putBoolean(IS_WELCOME_SHOWS, isWelcome);
        editor.commit();
    }

    public boolean isWelcomeLaunch(){
        return preferences.getBoolean(IS_WELCOME_SHOWS,true);
    }
}
