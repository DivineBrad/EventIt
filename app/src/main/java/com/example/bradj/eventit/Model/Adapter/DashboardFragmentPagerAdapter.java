package com.example.bradj.eventit.Model.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bradj.eventit.DashboardFragment;
import com.example.bradj.eventit.EventsFragment;
import com.example.bradj.eventit.MapFragment;

/**
 * Created by ajibd on 1/7/2018.
 */

public class DashboardFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Map View", "Private Events" };
    private Context context;

    public DashboardFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment tab1 = new MapFragment();
                return tab1;
            case 1:
                Fragment tab2 = new EventsFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
