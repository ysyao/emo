package com.phl.emoproject.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.phl.emoproject.R;
import com.phl.emoproject.home.HomePageListFragmentAdapter;
import com.phl.emoproject.widget.NonSwipeableViewPager;
import com.viewpagerindicator.TabPageIndicator;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;


public class HomePageListFragment extends RoboFragment implements
        TabHost.OnTabChangeListener,
        ViewPager.OnPageChangeListener{
    @InjectView(R.id.indicator)
    TabPageIndicator tabPageIndicator;
    @InjectView(R.id.viewpager)
    NonSwipeableViewPager viewPager;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new HomePageListFragmentAdapter(getFragmentManager()));
        tabPageIndicator.setViewPager(viewPager);
        tabPageIndicator.setOnPageChangeListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_page_list, container, false);
    }

    @Override
    public void onTabChanged(String s) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
