package com.example.siddharthnarayan.layouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.app.Fragment;
import android.view.ViewGroup;

/**
 * Created by Siddharth Narayan on 17-06-2016.
 */
public class Fragment2 extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment2,container,false);
    }
}
