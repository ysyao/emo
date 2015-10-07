package com.phl.emoproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.phl.emoproject.R;
import com.phl.emoproject.utils.ToolbarUtils;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActionBarActivity {

//    @InjectView(R.id.toolbar)
//    Toolbar toolbar;
    @InjectView(R.id.login_button)
    View button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ToolbarUtils.normalSetting(this, toolbar);
//        ToolbarUtils.setCenterTitle(toolbar, "首页");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2015/10/7 Start another activity

            }
        });
    }

}
