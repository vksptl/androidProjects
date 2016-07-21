package com.example.siddharthnarayan.muzic;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SongListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static ArrayList<Song> songList;
    public static List<String> songPathList;
    public static float height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels/getResources().getDisplayMetrics().density;

        Log.w("height", "" + height);

        getAllSongs();

        recyclerView=(RecyclerView)findViewById(R.id.recyclerForSongList);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);


        recyclerView.setAdapter(new SongListAdapter(songList,this));
    }

    public void getAllSongs()
    {

        songList=new ArrayList<Song>();

        songPathList=new ArrayList<String>();


        ContentResolver musicResolver=getContentResolver();
        Uri musicUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        Cursor musicCursor=musicResolver.query(musicUri,null,selection,null,null);

        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);

            int durationColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);


            int pathColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            //add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                int thisDuration = musicCursor.getInt(durationColumn);
                songPathList.add(musicCursor.getString(pathColumn));

                Song songModel =new Song(thisId, thisTitle, thisArtist,thisDuration);

                songList.add(songModel);

            }
            while (musicCursor.moveToNext());
        }

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        MainActivity.mediaPlayer.stop();
        MainActivity.mediaPlayer.release();
        MainActivity.mediaPlayer=null;
    }


}
