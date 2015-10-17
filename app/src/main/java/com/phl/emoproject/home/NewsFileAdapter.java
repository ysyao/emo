package com.phl.emoproject.home;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phl.emoproject.R;
import com.phl.emoproject.pojo.NewsDetail;
import com.phl.emoproject.widget.BaseListAdapter;

import java.util.List;


public class NewsFileAdapter extends BaseListAdapter<NewsDetail.FileInfo> {
    class Holder {
        TextView fileName;
        View download;
        View navi;
    }
    FilesAdapterListener filesAdapterListener;
    public NewsFileAdapter(Context context, List<NewsDetail.FileInfo> items) {
        super(context, items);
    }

    public FilesAdapterListener getFilesAdapterListener() {
        return filesAdapterListener;
    }

    public void setFilesAdapterListener(FilesAdapterListener filesAdapterListener) {
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
        final NewsDetail.FileInfo info = getItem(i);
        holder.fileName.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.fileName.setText(info.getFileName());

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
