package com.phl.emoproject.utils;


import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.ListView;
import android.widget.TextView;

import com.phl.emoproject.R;

public class ViewUtils {
    public static View createListViewFooterView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.listview_footer_view, null);
    }

    public static View getListViewFooterIndicator(View footerView) {
        View moreIndicator = footerView.findViewById(R.id.more_indicator);
        moreIndicator.setVisibility(View.GONE);
        return moreIndicator;
    }

    public static void setListViewFooterIndicatorVisible(View footerView, boolean visible) {
        View v = getListViewFooterIndicator(footerView);
        if (visible) {
            v.setVisibility(View.VISIBLE);
        } else {
            v.setVisibility(View.GONE);
        }
    }

    public static void setListViewFooterTitle(View footerView, String text) {
        TextView title = (TextView) footerView.findViewById(R.id.title_footer);
        title.setText(text);
    }

    public static void setNoData(ListView listView) {
        View v = LayoutInflater.from(listView.getContext()).inflate(R.layout.no_data, null);
        listView.setEmptyView(v);
    }


}
