package com.phl.emoproject.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.phl.emoproject.ui.BlankFragment;
import com.phl.emoproject.ui.HomeFragment;
import com.phl.emoproject.ui.HomePageListItemFragment;
import com.phl.emoproject.ui.MeFragment;
import com.phl.emoproject.ui.NewsFragment;

public class HomePageListFragmentAdapter extends FragmentStatePagerAdapter {
    public HomePageListFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BlankFragment();
            case 1:
                return HomePageListItemFragment.newInstance(HomeListType.DAIBAN);
            case 2:
                return HomePageListItemFragment.newInstance(HomeListType.BEIAN);
            case 3:
                return new BlankFragment();
        }
        return null;
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
