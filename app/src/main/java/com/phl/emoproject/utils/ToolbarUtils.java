package com.phl.emoproject.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.phl.emoproject.R;

public class ToolbarUtils {
    public static void normalSetting(ActionBarActivity context, Toolbar toolbar) {
        ToolbarUtils.setToolbarInsteadOfActionBar(context, toolbar);
//        ToolbarUtils.finishAcitivityAsNavigationIconClicked(toolbar, context);
    }
    /**
     * 将默认的action bar设置为不可用，然后将自定义的toolbar设置成为action bar
     * @param context activity
     */
    public static void setToolbarInsteadOfActionBar(ActionBarActivity context, Toolbar toolbar) {
//        context.setSupportActionBar(toolbar);
//        context.getSupportActionBar().setDisplayUseLogoEnabled(false);
//        context.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        context.getSupportActionBar().setDisplayShowTitleEnabled(false);
//        context.getSupportActionBar().setDisplayShowHomeEnabled(false);
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

    public static TextView setLeftTitleEnable(final Activity context, Toolbar toolbar, boolean isVisible) {
        TextView v = (TextView)toolbar.findViewById(R.id.left_title);
        if (isVisible) {
            v.setVisibility(View.VISIBLE);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.finish();
                }
            });
        } else if (v.getVisibility() == View.VISIBLE && !isVisible){
            v.setVisibility(View.GONE);
        }
        return v;
    }

    public static TextView setRightTitleEnable(final Activity context, Toolbar toolbar, boolean isVisible) {
        TextView v = (TextView)toolbar.findViewById(R.id.right_title);
        if (isVisible) {
            v.setVisibility(View.VISIBLE);
        } else if (v.getVisibility() == View.VISIBLE && !isVisible){
            v.setVisibility(View.GONE);
        }
        return v;
    }

    public static void setLeftTitle(Activity context, Toolbar toolbar, String title) {
        TextView v = setLeftTitleEnable(context, toolbar, true);
        v.setText(title);
    }

    public static void setRightTitle(Activity context, Toolbar toolbar, String title) {
        TextView v = setRightTitleEnable(context, toolbar, true);
        v.setText(title);
    }

    public static void setCenterTitle(Toolbar toolbar, String title) {
        TextView v = (TextView) toolbar.findViewById(R.id.center_title);
        v.setText(title);
    }

}
