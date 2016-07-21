package com.example.siddharthnarayan.muzic;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private ImageView playButton;
    private ImageView nextButton;
    private ImageView previousButton;
    private ImageView favouriteButton;
    private ImageView backButton;
    private TextView songName;
    private TextView artistName;
    private SeekBar seekBar;
    public static MediaPlayer mediaPlayer;
    private TextView currentTime;
    private TextView endTime;

    public static int playing;


    Thread progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (ImageView) findViewById(R.id.playButton);
        nextButton = (ImageView) findViewById(R.id.nextButton);
        previousButton = (ImageView) findViewById(R.id.previousButton);
        backButton = (ImageView) findViewById(R.id.backButton);
        favouriteButton = (ImageView) findViewById(R.id.favouriteButton);
        songName = (TextView) findViewById(R.id.songName);
        artistName = (TextView) findViewById(R.id.artistName);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        currentTime = (TextView) findViewById(R.id.currentTime);
        endTime = (TextView) findViewById(R.id.endTime);


        AudioVisualizationFragment frag=new AudioVisualizationFragment();


        android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.visualiser_fragment,frag);
        ft.commit();


        playButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        favouriteButton.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(this);



        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);



        playSong();



    }


    private void playSong() {

        try {
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(SongListActivity.songPathList.get(playing)));
            mediaPlayer.prepare();
            mediaPlayer.start();
            songName.setText(SongListActivity.songList.get(playing).getTitle());
            artistName.setText(SongListActivity.songList.get(playing).getArtist());
            endTime.setText(SongListActivity.songList.get(playing).getDuration());
        } catch (IOException e) {
            Log.e("IOException", "true");
        }

        seekBar.setMax(mediaPlayer.getDuration() / 1000);

        progress = new Thread() {
            public void run() {
                while (mediaPlayer.getDuration() != mediaPlayer.getCurrentPosition()) {

                    try{

                        seekBar.setProgress(mediaPlayer.getCurrentPosition() / 1000);

                    }
                    catch(IllegalStateException e)
                    {
                        finish();
                    }
                }
            }
        };

        progress.start();


    }

    public String convertToTime(int seconds) {
        int min = seconds / 60;
        seconds = seconds % 60;
        return seconds < 10 ? min + ":0" + seconds : min + ":" + seconds;
    }



    @Override
    public void onResume() {
        super.onResume();
        if (mediaPlayer != null) {
            mediaPlayer.start();

            seekBar.setProgress(mediaPlayer.getCurrentPosition()/1000);
        }
    }

    @Override
    public void onPause() {
       if(mediaPlayer!=null)
        {
            mediaPlayer.pause();
        }
        super.onPause();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.playButton:

                if (mediaPlayer.isPlaying()) {
                    playButton.setImageResource(R.drawable.play);
                    mediaPlayer.pause();
                    }

                else {
                    playButton.setImageResource(R.drawable.pause);
                    mediaPlayer.start();

                }

                break;

            case R.id.previousButton:

                previousTrack();

                break;

            case R.id.nextButton:

                nextTrack();

                break;

            case R.id.favouriteButton:

                if(SongListActivity.songList.get(playing).isFavourite())
                {
                    favouriteButton.setImageResource(R.drawable.like_blank);
                    SongListActivity.songList.get(playing).setFavourite(false);
                    Toast.makeText(this,"Removed from favourites",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    favouriteButton.setImageResource(R.drawable.like_filled);
                    SongListActivity.songList.get(playing).setFavourite(true);
                    Toast.makeText(this,"Added to favourites",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.backButton:

                super.onBackPressed();

                break;

        }
    }

    private void nextTrack() {
        playing++;
        mediaPlayer.reset();
        seekBar.setProgress(0);
        if (playing == SongListActivity.songList.size()) {
            playing = 0;

        }
        playSong();
    }

    private void previousTrack() {
        playing--;
        mediaPlayer.reset();
        seekBar.setProgress(0);
        if (playing < 0) {
            playing = SongListActivity.songList.size() - 1;

        }
        playSong();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (fromUser) {

            mediaPlayer.seekTo(progress * 1000);


        }
        currentTime.setText("" + convertToTime(progress));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
