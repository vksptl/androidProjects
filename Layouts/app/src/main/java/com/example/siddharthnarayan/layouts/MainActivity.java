package com.example.siddharthnarayan.layouts;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity  {

    public boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FragmentManager fragmentManager=getFragmentManager();
        setContentView(R.layout.activity_main);
        View v=findViewById(R.id.fragment2);
        final Fragment2 f2=new Fragment2();
        final Fragment5 f3=new Fragment5();
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (!flag) {
                    fragmentTransaction.remove(f3);
                    fragmentTransaction.add(R.id.fragment2,f2);
                    fragmentTransaction.show(f2);
                    flag=true;
                }
                else {
                    //Fragment f=fragmentManager.findFragmentById(R.id.fragment2);
                    fragmentTransaction.remove(f2);
                    System.out.println("CHECK 1");
                    fragmentTransaction.add(R.id.fragment2, f3);
                    fragmentTransaction.show(f3);
                    System.out.println("CHECK 2");
                    flag = false;
                }

                fragmentTransaction.commit();
                System.out.println("COMMITED");
            }
        });
    }
}
