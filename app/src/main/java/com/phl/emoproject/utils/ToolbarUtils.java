package com.phl.emoproject.utils;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.phl.emoproject.R;

/**
 * Created by JimmyandHurry on 2015/10/7.
 */
public class ToolbarUtils {
    public static void normalSetting(ActionBarActivity context, Toolbar toolbar) {
        ToolbarUtils.setToolbarInsteadOfActionBar(context, toolbar);
        ToolbarUtils.finishAcitivityAsNavigationIconClicked(toolbar, context);
    }
    /**
     * 将默认的action bar设置为不可用，然后将自定义的toolbar设置成为action bar
     * @param context activity
     */
    public static void setToolbarInsteadOfActionBar(ActionBarActivity context, Toolbar toolbar) {
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    /**
     * 设置toolbar上的navigation icon的点击事件为关闭activity
     * @param toolbar
     */
    public static void finishAcitivityAsNavigationIconClicked(Toolbar toolbar, final Activity context) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.finish();
            }
        });
    }

    public static TextView setLeftTitleEnable(Toolbar toolbar, boolean isVisible) {
        TextView v = (TextView)toolbar.findViewById(R.id.left_title);
        if (v.getVisibility() == View.GONE && isVisible) {
            v.setVisibility(View.VISIBLE);
        } else if (v.getVisibility() == View.VISIBLE && !isVisible){
            v.setVisibility(View.GONE);
        }
        return v;
    }

    public static void setLeftTitle(Toolbar toolbar, String title) {
        TextView v = setLeftTitleEnable(toolbar, true);
        v.setText(title);
    }

    public static void setCenterTitle(Toolbar toolbar, String title) {
        TextView v = (TextView) toolbar.findViewById(R.id.center_title);
        v.setText(title);
    }

}
