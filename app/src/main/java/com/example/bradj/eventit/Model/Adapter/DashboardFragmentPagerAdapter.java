package com.example.bradj.eventit.Model.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bradj.eventit.DashboardFragment;
import com.example.bradj.eventit.EventsFragment;
import com.example.bradj.eventit.MapFragment;
import com.example.bradj.eventit.OrganizationsFragment;

/**
 * Created by ajibd on 1/7/2018.
 */

public class DashboardFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Map View", "Events", "Organizations" };
    private Context context;

    public DashboardFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    // overriding getPageTitle()
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
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
            case 2:
                Fragment tab3 = new OrganizationsFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
