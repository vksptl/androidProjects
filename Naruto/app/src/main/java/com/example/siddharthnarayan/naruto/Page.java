package com.example.siddharthnarayan.naruto;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Siddharth Narayan on 11-05-2016.
 */
public class Page extends AppCompatActivity {
     int pageNumber=1;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);
    }

    public void goToHome(View view)
    {
    }

    public void nextPagess(View view)
    {
        if(pageNumber<16)
            ++pageNumber;
        String uri="@drawable/page2";
        display(uri);

    }

    public void display(String pg)
    {
        int imageResource=getResources().getIdentifier(pg, null, getPackageName());
        ImageView imageview=(ImageView)findViewById(R.id.image_view);
        Drawable res= ResourcesCompat.getDrawable(getResources(),imageResource,null);
        imageview.setImageDrawable(res);
        setContentView(R.layout.page);
    }

    public void previousPage(View view)
    {
        if(pageNumber>0)
            --pageNumber;
        String uri="@drawable/page"+pageNumber;
        int imageResource=getResources().getIdentifier(uri, "drawable", this.getPackageName());
        ImageView imageview=(ImageView)findViewById(R.id.image_view);
        Drawable res=getResources().getDrawable(imageResource);
        imageview.setImageDrawable(res);
        setContentView(R.layout.page);
    }
}
