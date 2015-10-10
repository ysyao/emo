package com.phl.emoproject.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phl.emoproject.R;
import com.phl.emoproject.pojo.HomeGridViewItem;
import com.phl.emoproject.pojo.NewsList;
import com.phl.emoproject.widget.BaseListAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsListAdapter extends BaseListAdapter<NewsList> {
    class Holder {
        TextView day;
        TextView month;
        TextView title;
        TextView description;
    }

    public NewsListAdapter(Context context, List<NewsList> items) {
        super(context, items);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            view = getInflater().inflate(R.layout.item_news_list, viewGroup, false);
            holder = new Holder();
            holder.day = (TextView) view.findViewById(R.id.day);
            holder.month = (TextView) view.findViewById(R.id.month);
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.description = (TextView) view.findViewById(R.id.description);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        NewsList item = getItem(i);
        String[] dateArray1 = item.getCreateDate().split(" ");
        String[] dateArray2 = dateArray1[0].split("/");
        holder.day.setText(""+dateArray2[2]);
        holder.month.setText(""+dateArray2[0]+"."+dateArray2[1]);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getCreatorName());
        return view;
    }
}
