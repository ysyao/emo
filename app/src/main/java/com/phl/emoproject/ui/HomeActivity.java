package com.phl.emoproject.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.phl.emoproject.R;
import com.phl.emoproject.core.Constans;
import com.phl.emoproject.core.EmoApplication;
import com.phl.emoproject.home.HomeViewPagerAdapter;
import com.phl.emoproject.utils.ToolbarUtils;
import com.phl.emoproject.widget.bottom_bar.OnSelectableTextViewClickedListener;
import com.phl.emoproject.widget.bottom_bar.SelectableBottomTextView;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_home)
public class HomeActivity extends RoboActionBarActivity implements
        OnSelectableTextViewClickedListener,
        ViewPager.OnPageChangeListener {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.viewpager)
    ViewPager viewPager;
    @InjectView(R.id.news)
    SelectableBottomTextView newsTv;
    @InjectView(R.id.home)
    SelectableBottomTextView homeTv;
    @InjectView(R.id.me)
    SelectableBottomTextView meTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarUtils.normalSetting(this, toolbar);
        ToolbarUtils.setCenterTitle(toolbar, "首页");
        ToolbarUtils.setLeftTitleEnable(this, toolbar, false);
        View right = ToolbarUtils.setRightTitleEnable(this, toolbar, true);
        ToolbarUtils.setRightTitle(this, toolbar, "ip配置");
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(HomeActivity.this).inflate(R.layout.edittext_dialog, null);
                final EditText et = (EditText) v.findViewById(R.id.input_ip);
                new AlertDialog.Builder(HomeActivity.this).setTitle("ip配置").setView(v).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        String ip = et.getText().toString();
                        if (ip == null || "".equals(ip)) {
                            return;
                        }
                        EmoApplication.getInstance().setPath(ip);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });

        newsTv.setOnTextViewClickedListener(this);
        meTv.setOnTextViewClickedListener(this);
        homeTv.setOnTextViewClickedListener(this);

        viewPager.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnPageChangeListener(this);
        homeTv.performClick();
    }

    @Override
    public void onTextViewClicked(View v) {
        switch (v.getId()) {
            case R.id.news:
                homeTv.setTextViewSelected(false);
                meTv.setTextViewSelected(false);
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.home:
                newsTv.setTextViewSelected(false);
                meTv.setTextViewSelected(false);
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.me:
                newsTv.setTextViewSelected(false);
                homeTv.setTextViewSelected(false);
                viewPager.setCurrentItem(2, false);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                newsTv.setTextViewSelected(true);
                homeTv.setTextViewSelected(false);
                meTv.setTextViewSelected(false);
                ToolbarUtils.setCenterTitle(toolbar, "新闻列表");
                break;
            case 1:
                newsTv.setTextViewSelected(false);
                homeTv.setTextViewSelected(true);
                meTv.setTextViewSelected(false);
                ToolbarUtils.setCenterTitle(toolbar, "首页");
                break;
            case 2:
                newsTv.setTextViewSelected(false);
                homeTv.setTextViewSelected(false);
                meTv.setTextViewSelected(true);
                ToolbarUtils.setCenterTitle(toolbar, "个人中心");
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
