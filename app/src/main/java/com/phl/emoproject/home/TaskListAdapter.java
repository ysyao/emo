package com.phl.emoproject.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phl.emoproject.R;
import com.phl.emoproject.pojo.NewsList;
import com.phl.emoproject.pojo.TaskList;
import com.phl.emoproject.widget.BaseListAdapter;

import java.util.List;


public class TaskListAdapter extends BaseListAdapter<TaskList> {
    class Holder {
        TextView name;
        TextView time;
    }

    public TaskListAdapter(Context context, List<TaskList> items) {
        super(context, items);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            view = getInflater().inflate(R.layout.item_task_list, viewGroup, false);
            holder = new Holder();
            holder.name = (TextView) view.findViewById(R.id.item_name);
            holder.time = (TextView) view.findViewById(R.id.item_time);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        TaskList item = getItem(i);
        holder.name.setText(item.getTitle());
        holder.time.setText(item.getCreateDate());
        return view;
    }
}
