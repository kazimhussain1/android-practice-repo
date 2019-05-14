package com.example.androidmev20;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity{

    TabAdapter myTabAdapter;
    ViewPager myViewPager;
    TabLayout myTabLayout;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        myViewPager = findViewById(R.id.mainViewPager);
        myTabAdapter = new TabAdapter(getSupportFragmentManager());
        myTabLayout = findViewById(R.id.tab_layout);

        myViewPager.setAdapter(myTabAdapter);
        myTabLayout.setupWithViewPager(myViewPager);

        myTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        //String[] myTabs = myTabAdapter.getTabNames();
        //for (String i: myTabs){
        // myTabLayout.addTab(myTabLayout.newTab().setText(i));
        //}

        /*myViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        myTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }

}
