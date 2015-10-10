package com.phl.emoproject.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class WrapContentHeightListView extends ListView {
    public WrapContentHeightListView(Context context) {
        super(context);
    }

    public WrapContentHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapContentHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
