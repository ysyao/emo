package com.phl.emoproject.ui;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TabHost;

import com.phl.emoproject.R;
import com.phl.emoproject.home.HomeFragmentGridViewAdapter;
import com.phl.emoproject.pojo.HomeGridViewItem;

import java.util.ArrayList;
import java.util.List;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

public class HomeFragment extends RoboFragment {
    @InjectView(R.id.gridview)
    GridView gridView;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView.setAdapter(new HomeFragmentGridViewAdapter(getActivity(), craeteItems()));
        getFragmentManager().beginTransaction().add( R.id.fragment_container, new HomePageListFragment()).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    private List<HomeGridViewItem> craeteItems() {
        List<HomeGridViewItem> items = new ArrayList<>();
        items.add(new HomeGridViewItem("EOM待办列表", R.drawable.pic_1));
        items.add(new HomeGridViewItem("EOM备案列表", R.drawable.pic_2));
        items.add(new HomeGridViewItem("新闻列表", R.drawable.pic_3));
        items.add(new HomeGridViewItem("新员工入职通告", R.drawable.pic_4));
        items.add(new HomeGridViewItem("通知", R.drawable.pic_5));
        items.add(new HomeGridViewItem("公告", R.drawable.pic_6));

        return items;
    }

}
