package com.phl.emoproject.ui;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TabHost;

import com.phl.emoproject.R;
import com.phl.emoproject.home.HomeFragmentGridViewAdapter;
import com.phl.emoproject.home.HomeListType;
import com.phl.emoproject.pojo.HomeGridViewItem;
import com.phl.emoproject.pojo.NewsList;

import java.util.ArrayList;
import java.util.List;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

public class HomeFragment extends RoboFragment implements AdapterView.OnItemClickListener{
    @InjectView(R.id.gridview)
    GridView gridView;
    Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity =activity;
    }

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
        gridView.setOnItemClickListener(this);
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
        items.add(new HomeGridViewItem("EOM待办", R.drawable.pic_1));
        items.add(new HomeGridViewItem("EOM备案", R.drawable.pic_2));
        items.add(new HomeGridViewItem("预警督办", R.drawable.pic_3));
        items.add(new HomeGridViewItem("入职公告", R.drawable.pic_4));
        items.add(new HomeGridViewItem("管控体系", R.drawable.pic_5));
        items.add(new HomeGridViewItem("知识", R.drawable.pic_6));

        return items;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent;
        switch (i) {
            case 0:
                intent = new Intent(activity, TaskListActivity.class);
                intent.putExtra("type", HomeListType.DAIBAN);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(activity, TaskListActivity.class);
                intent.putExtra("type", HomeListType.BEIAN);
                startActivity(intent);
                break;
//            case 2:
//                intent = new Intent(activity, NewsListActivity.class);
//                startActivity(intent);
//                break;
        }
    }
}
