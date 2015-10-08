package com.phl.emoproject.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.phl.emoproject.R;
import com.phl.emoproject.utils.ToolbarUtils;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_home)
public class HomeActivity extends RoboActionBarActivity {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarUtils.normalSetting(this, toolbar);
        ToolbarUtils.setCenterTitle(toolbar, "首页");
    }
}
