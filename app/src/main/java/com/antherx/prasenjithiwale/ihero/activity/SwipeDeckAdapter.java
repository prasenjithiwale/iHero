package com.antherx.prasenjithiwale.ihero.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.antherx.prasenjithiwale.ihero.R;
import com.antherx.prasenjithiwale.ihero.SwipeCard;
import com.antherx.prasenjithiwale.ihero.YoutubeVideos;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Prasenjit Hiwale on 6/3/2017.
 */

public class SwipeDeckAdapter extends BaseAdapter {
    private List<String> mData;
    private Context mContext;

    String[] imageUrl = {"http://eskipaper.com/images/beautiful-girls-24.jpg",
            "http://www.wallpapers-web.com/data/out/145/5053041-most-beautiful-girls-wallpapers.jpg",
    "http://9walls.in/wp-content/uploads/2014/11/beautiful-girl-wearing-hat-wallpaper-hd-backgrounds-for-mobile-and-pc-free-images-download-1024x768.jpg",
    "https://wallpaperstock.net/beautiful-girl_wallpapers_37802_1024x768.jpg",
    "http://images.xartbeauties.com/source_galleries/x-art_chloelynn_sparkly-med/02.jpg"};


    private LayoutInflater layoutInflater;

    public SwipeDeckAdapter(List<String> data, Context context){
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null){
            layoutInflater = LayoutInflater.from(getApplicationContext());
            view = layoutInflater.inflate(R.layout.swipe_card, parent, false);

        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Glide.with(getApplicationContext()).load(imageUrl[position])
                .fitCenter()
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Thanks"+position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
