package com.antherx.prasenjithiwale.ihero.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antherx.prasenjithiwale.ihero.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainLogin extends AppCompatActivity {

    CallbackManager callbackManager;
    Profile profile;
    String profileName;
    LoginButton loginButton;
    String profilePictureURL;

    TextView profileNameText;
    TextView profileIdText;

    ImageView profilePictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        profileNameText = (TextView)findViewById(R.id.profileNameOnLogin);
        loginButton = (LoginButton)findViewById(R.id.loginButton);
        profilePictureView = (ImageView) findViewById(R.id.profilePictureView);

        profile = Profile.getCurrentProfile();

        if (profile != null){
            profileNameText.setText(profile.getName());
            profilePictureURL = "http://graph.facebook.com/" + profile.getId() + "/picture?type=large";

            Glide.with(getApplicationContext()).load(profilePictureURL)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profilePictureView);
        }

        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                LoginManager.getInstance().logInWithReadPermissions(MainLogin.this, Arrays.asList("public_profile",
                        "user_friends", "email"));
                Toast.makeText(MainLogin.this, "You Are Successfully Log In", Toast.LENGTH_SHORT).show();
                profile = Profile.getCurrentProfile();
                if (profile != null){
                    profileName = profile.getName();
                    profileNameText.setText(profileName);

                    profilePictureURL = "http://graph.facebook.com/" + profile.getId() + "/picture?type=normal";

                    Glide.with(getApplicationContext()).load(profilePictureURL)
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(profilePictureView);
                }

            }

            @Override
            public void onCancel() {
                Toast.makeText(MainLogin.this, "Log In Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainLogin.this, "Error in Log In", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
