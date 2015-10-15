package com.phl.emoproject.home;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phl.emoproject.R;
import com.phl.emoproject.pojo.NewsDetail;
import com.phl.emoproject.pojo.TaskListDetail;
import com.phl.emoproject.widget.BaseListAdapter;

import java.util.List;

public class DetailFileAdapter extends BaseListAdapter<TaskListDetail.TaskFile> {
    class Holder {
        TextView fileName;
    }
    public DetailFileAdapter(Context context, List<TaskListDetail.TaskFile> items) {
        super(context, items);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            holder = new Holder();
            view = getInflater().inflate(R.layout.item_news_files, viewGroup, false);
            holder.fileName = (TextView) view.findViewById(R.id.file_name);

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        TaskListDetail.TaskFile info = getItem(i);
        holder.fileName.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.fileName.setText(info.getName());

        return view;
    }
}
