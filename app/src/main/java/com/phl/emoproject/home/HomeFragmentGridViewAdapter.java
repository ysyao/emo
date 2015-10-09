package com.phl.emoproject.home;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phl.emoproject.R;
import com.phl.emoproject.pojo.HomeGridViewItem;
import com.phl.emoproject.widget.BaseListAdapter;

import java.util.List;

public class HomeFragmentGridViewAdapter extends BaseListAdapter<HomeGridViewItem> {
    class Holder {
        ImageView pic;
        TextView title;
    }
    public HomeFragmentGridViewAdapter(Context context, List<HomeGridViewItem> items) {
        super(context, items);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            view = getInflater().inflate(R.layout.item_home_gridview, viewGroup, false);
            holder = new Holder();
            holder.pic = (ImageView) view.findViewById(R.id.pic);
            holder.title = (TextView) view.findViewById(R.id.title);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        HomeGridViewItem item = getItem(i);
        holder.title.setText(item.getName());
        holder.pic.setImageResource(item.getDrawable());
        return view;
    }
}
