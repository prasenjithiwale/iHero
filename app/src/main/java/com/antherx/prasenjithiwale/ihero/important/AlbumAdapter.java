package com.antherx.prasenjithiwale.ihero.important;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.antherx.prasenjithiwale.ihero.R;
import com.antherx.prasenjithiwale.ihero.activity.MainActivity;
import com.antherx.prasenjithiwale.ihero.activity.MainHome;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by Prasenjit Hiwale on 4/29/2017.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title, count;
        public ImageView thumbnail, overflow, like, liked;
        public int a = 0;

        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            liked = (ImageView) view.findViewById(R.id.liked);
        }
    }

    public AlbumAdapter(Context mContext, List<Album> albumList){
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position){
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + " likes");

        //Glide Library for loading images in Imageview

        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(holder.overflow);
            }
        });

        holder.liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.a == 0){
                holder.liked.setImageResource(R.drawable.ic_favorite_border_red_48dp);
                    holder.a = 1;
                }else {
                    holder.liked.setImageResource(R.drawable.ic_favorite_border_black_48dp);
                    holder.a = 0;
                }
            }
        });
    }
    //Showing popupmenu on click that three dots
    private void showPopupMenu(View view){
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemCLickListner());
        popup.show();
    }

    class MyMenuItemCLickListner implements PopupMenu.OnMenuItemClickListener{


        public MyMenuItemCLickListner(){
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem){
            switch (menuItem.getItemId()){
                case R.id.action_add_favourite :
                    Toast.makeText(mContext, "Add to Favourites", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next :
                    Toast.makeText(mContext, "PLay Next", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.StoryLine:
                    Intent intent = new Intent(mContext, MainActivity.class);
                    mContext.startActivity(intent);
                    Toast.makeText(mContext, "Read Story", Toast.LENGTH_SHORT).show();
                    return true;
                default :
            }
            return false;
        }

    }
    @Override
    public int getItemCount(){

        return albumList.size();
    }



}
