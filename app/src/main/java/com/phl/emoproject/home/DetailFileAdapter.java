package com.phl.emoproject.home;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phl.emoproject.R;
import com.phl.emoproject.core.Constans;
import com.phl.emoproject.pojo.NewsDetail;
import com.phl.emoproject.pojo.TaskListDetail;
import com.phl.emoproject.widget.BaseListAdapter;

import java.io.File;
import java.util.List;

public class DetailFileAdapter extends BaseListAdapter<TaskListDetail.TaskFile> {
    class Holder {
        TextView fileName;
        View download;
        View navi;
    }

    DetailFilesAdapterListener filesAdapterListener;
    public DetailFileAdapter(Context context, List<TaskListDetail.TaskFile> items) {
        super(context, items);
    }

    public DetailFilesAdapterListener getFilesAdapterListener() {
        return filesAdapterListener;
    }

    public void setFilesAdapterListener(DetailFilesAdapterListener filesAdapterListener) {
        this.filesAdapterListener = filesAdapterListener;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            holder = new Holder();
            view = getInflater().inflate(R.layout.item_news_files, viewGroup, false);
            holder.fileName = (TextView) view.findViewById(R.id.file_name);
            holder.download = view.findViewById(R.id.download);
            holder.navi = view.findViewById(R.id.navi_files);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        final TaskListDetail.TaskFile info = getItem(i);
        holder.fileName.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
//        holder.fileName.setText(info.getName());
        String path = Constans.FILE_PATH + "/" + info.getName();
        File file = new File(path);
        if (file.exists()) {
            holder.fileName.setText(info.getName()+"(已下载)");
        } else {
            holder.fileName.setText(info.getName());
        }

        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filesAdapterListener == null) {
                    return;
                }
                filesAdapterListener.onDownload(view, info);
            }
        });
        holder.navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filesAdapterListener == null) {
                    return;
                }
                filesAdapterListener.onRedirect(view, info);
            }
        });
        return view;
    }
}
