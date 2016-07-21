package com.example.siddharthnarayan.muzic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleveroad.audiovisualization.AudioVisualization;
import com.cleveroad.audiovisualization.DbmHandler;
import com.cleveroad.audiovisualization.GLAudioVisualizationView;

/**
 * Created by Siddharth Narayan on 21-07-2016.
 */
public class AudioVisualizationFragment extends Fragment {

    public static AudioVisualizationFragment newInstance() {
        return new AudioVisualizationFragment();
    }

    private AudioVisualization audioVisualization;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        float footer;
        float wave;

        String wave_height=(SongListActivity.height*70/640)+"dp";
        try {
            wave = DimensionConverter.stringToDimension(wave_height, getResources().getDisplayMetrics());
        }
        catch(NumberFormatException e)
        {
            wave=R.dimen.wave;
        }

        String footer_height=(SongListActivity.height*150/640)+"dp";

        try {

            footer = DimensionConverter.stringToDimension(footer_height, getResources().getDisplayMetrics());
        }
        catch(NumberFormatException e)
        {
            footer=R.dimen.footer;
        }

        int ar[]={R.array.av_colors,R.array.av_colorsF,R.array.av_colorsL,R.array.av_colorsW,R.array.av_colorsS};

        GLAudioVisualizationView view = new GLAudioVisualizationView.Builder(getContext())
                .setBubbleSize(R.dimen.bubble)
                .setRandomizeBubbleSize(true)
                .setWaveHeight(wave)
                .setFooterHeight(footer)
                .setWavesCount(7)
                .setLayersCount(4)
                .setBackgroundColorRes(R.color.av_color_bg)
                .setLayerColors(ar[MainActivity.playing%5])
                .setBubblesPerLayer(16)
                .build();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        audioVisualization = (AudioVisualization) view;
        audioVisualization.linkTo(DbmHandler.Factory.newVisualizerHandler(getContext(), 0));
    }

    @Override
    public void onResume() {
        super.onResume();
        audioVisualization.onResume();
    }

    @Override
    public void onPause() {
        audioVisualization.onPause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        audioVisualization.release();
        super.onDestroyView();
    }
}