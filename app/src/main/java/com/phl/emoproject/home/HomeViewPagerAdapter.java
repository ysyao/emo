package com.phl.emoproject.home;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.phl.emoproject.ui.HomeFragment;
import com.phl.emoproject.ui.MeFragment;
import com.phl.emoproject.ui.NewsFragment;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NewsFragment.newInstance("", "");
            case 1:
                return HomeFragment.newInstance();
            case 2:
                return MeFragment.newInstance("", "");
        }
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
