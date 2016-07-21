package com.example.siddharthnarayan.practiceintents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClick1(View view)
    {
        startActivity(new Intent("net.learn2develop.SecondActivity"));
    }

    public void onClick2(View view)
    {
        startActivity(new Intent("net.learn2develop.ThirdActivity"));
    }

    public void onClick3(View view)
    {
        startActivity(new Intent("net.learn2develop.FourthActivity"));
    }
}
