package com.antherx.prasenjithiwale.ihero.important;

import java.net.URL;

/**
 * Created by Prasenjit Hiwale on 4/29/2017.
 */

public class Album {

    private String name;
    private int numOfSong;
    private String thumbnail;

    public Album(){

    }

    public Album(String name, int numOfSong, String thumbnail){
        this.name = name;
        this.numOfSong = numOfSong;
        this.thumbnail = thumbnail;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSong;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSong = numOfSongs;
    }

    public String getThumbnail(){
        return thumbnail;
    }
    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

}
