package com.example.duccm.learningapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by DucCM on 7/25/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    final int TAB_COUNT = 2;
    private String tabTitles[] = new String[] {"Timeline", "Profile"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TabTimelineFragment();
            case 1:
                return new TabProfileFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
