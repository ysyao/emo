package com.phl.emoproject.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.phl.emoproject.R;

import roboguice.fragment.RoboFragment;


public class HomePageListFragment extends RoboFragment implements TabHost.OnTabChangeListener{
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentTabHost tabHost = new FragmentTabHost(getActivity());
        tabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_home_page_list);
        tabHost.addTab(tabHost.newTabSpec("a").setIndicator("驾驶舱"),
                HomePageListItemFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("b").setIndicator("EMO代办"),
                HomePageListItemFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("c").setIndicator("EOM备案"),
                HomePageListItemFragment.class, null);
        tabHost.setOnTabChangedListener(this);
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View v = tabHost.getTabWidget().getChildAt(i);
            v.setBackgroundColor(getActivity().getResources().getColor(R.color.lightgray));

            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.sodarkgray));

        }
        return tabHost;
    }

    @Override
    public void onTabChanged(String s) {

    }
}
