package com.antherx.prasenjithiwale.ihero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeVideos extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    public static int videoCue = 1;

    ImageView Next;
    ImageView Prev;
    ImageView Special;

    // YouTube player view
    public YouTubePlayerView youTubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_youtube_videos);

        youTubeView = (YouTubePlayerView)findViewById(R.id.youtube_play);
        Next = (ImageView)findViewById(R.id.next);
        Prev = (ImageView)findViewById(R.id.prev);

        youTubeView.initialize(Config.DEVELOPERS_KEY, this);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }else {
            String errorMessage = String.format(
                    getString(R.string.error_player), youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean was) {
        if (!was){

            youTubePlayer.cueVideo(Config.VIDEO_CODE);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

            Next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoCue++;
                    if (videoCue > 5){
                        Toast.makeText(YoutubeVideos.this, "Can't Go Forward More", Toast.LENGTH_SHORT).show();
                        videoCue = 1;
                    }
                }
            });

            Prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoCue--;
                    if (videoCue < 1){
                        Toast.makeText(YoutubeVideos.this, "Can't Go Back More", Toast.LENGTH_SHORT).show();
                        videoCue = 1;
                    }
                }
            });

            if (videoCue == 2){
                youTubePlayer.cueVideo(Config.VIDEO_CODE_2);
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            }else if (videoCue == 3){
                youTubePlayer.cueVideo(Config.VIDEO_CODE_3);
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            }else if (videoCue == 4){
                youTubePlayer.cueVideo(Config.VIDEO_CODE_4);
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            }else if (videoCue == 5){
                youTubePlayer.cueVideo(Config.VIDEO_CODE_5);
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            }else{
                Toast.makeText(this, "Something Gone Wrong", Toast.LENGTH_SHORT).show();
            }



        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Retry initialization if user performed a recovery action
        getYouTubePlayerProvider().initialize(Config.DEVELOPERS_KEY, this);
    }
    private YouTubePlayer.Provider getYouTubePlayerProvider(){
        return (YouTubePlayerView) findViewById(R.id.youtube_play);
    }


    public void Home(View view){
        this.finish();
    }

}
