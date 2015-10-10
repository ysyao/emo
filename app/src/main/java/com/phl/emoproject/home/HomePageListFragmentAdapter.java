package com.phl.emoproject.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.phl.emoproject.ui.HomeFragment;
import com.phl.emoproject.ui.HomePageListItemFragment;
import com.phl.emoproject.ui.MeFragment;
import com.phl.emoproject.ui.NewsFragment;

public class HomePageListFragmentAdapter extends FragmentPagerAdapter {
    public HomePageListFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
         return new HomePageListItemFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "驾驶舱";
            case 1:
                return "EOM待办";
            case 2:
                return "EOM备案";
            case 3:
                return "预警督办";
        }
        return super.getPageTitle(position);
    }
}
