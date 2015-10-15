package com.phl.emoproject.home;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phl.emoproject.R;
import com.phl.emoproject.pojo.TaskListDetail;
import com.phl.emoproject.widget.BaseListAdapter;

import java.util.List;

public class HistoryNodesAdapter extends BaseListAdapter<TaskListDetail.HistoryNodes> {
    class Holder {
        TextView time;
        TextView name;
        TextView action;
    }
    public HistoryNodesAdapter(Context context, List<TaskListDetail.HistoryNodes> items) {
        super(context, items);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            holder = new Holder();
            view = getInflater().inflate(R.layout.item_history_nodes, viewGroup, false);
            holder.time = (TextView) view.findViewById(R.id.time);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.action = (TextView) view.findViewById(R.id.action);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        TaskListDetail.HistoryNodes info = getItem(i);
        holder.time.setText(info.getFinishedTime());
        holder.name.setText(info.getActualUserName()+":");
        holder.action.setText(info.getIdea());

        return view;
    }
}
