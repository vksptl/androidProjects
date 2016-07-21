package com.example.siddharthnarayan.muzic;

/**
 * Created by Siddharth Narayan on 21-07-2016.
 */
public class Song {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Song(long id,String title,String artist,int duration)
    {
        this.id=id;
        this.title=title;
        this.artist=artist;
        duration=duration/1000;
        int min=duration/60;
        int sec=duration%60;
        this.duration=sec<10?min+":0"+sec:min+":"+sec;

    }

    private long id;
    private String title;
    private String artist;



    private String duration;

    private boolean favourite;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
