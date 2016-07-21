package com.example.siddharthnarayan.muzic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Siddharth Narayan on 21-07-2016.
 */
public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongNameViewHolder>{


    private List<Song> songs;
    private Context context;

    public SongListAdapter(List<Song> songs, Context context){
        this.songs = songs;
        this.context=context;
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    @Override
    public SongNameViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.simple_text, viewGroup, false);
        return new SongNameViewHolder(itemView,context,songs);
    }

    @Override
    public void onBindViewHolder(SongNameViewHolder songNameViewHolder, int i) {

        songNameViewHolder.songName.setText(songs.get(i).getTitle());

    }

    public static class SongNameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView songName;
        List<Song> songList;
        Context context;

        SongNameViewHolder(View itemView, Context context, List<Song> songList) {
            super(itemView);
            songName=(TextView)itemView.findViewById(R.id.singleSongName);
            this.context=context;
            this.songList=songList;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MainActivity.playing=getAdapterPosition();

            context.startActivity(new Intent("android.intent.action.PLAYER"));

        }
    }


}
