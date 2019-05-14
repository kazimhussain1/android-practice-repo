package com.example.androidmev20;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter{

    private FragmentManager myFragmentManager;
    public static final String[] tabNames = { "Fragment 1", "Fragment 2", "Fragment 3" };


    public TabAdapter(FragmentManager fm) {
        super(fm);



    }

    @Override
    public Fragment getItem(int i) {
        Fragment myFragment = null;

        i = i%3;

        switch (i){
            case 0:
                myFragment = new Fragment1();
                break;
            case 1:
                myFragment = new Fragment2();
                break;
            case 2:
                myFragment = new Fragment3();
        }

        return myFragment;
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

    public String[] getTabNames() {
        return tabNames;
    }
}
