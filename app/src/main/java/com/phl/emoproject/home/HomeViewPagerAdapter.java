package com.phl.emoproject.home;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.phl.emoproject.ui.HomeFragment;
import com.phl.emoproject.ui.MeFragment;
import com.phl.emoproject.ui.NewsFragment;

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {
    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NewsFragment.newInstance();
            default:
                return HomeFragment.newInstance();
            case 2:
                return MeFragment.newInstance("", "");
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
