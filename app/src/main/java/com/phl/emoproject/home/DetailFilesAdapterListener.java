package com.phl.emoproject.home;

import android.view.View;

import com.phl.emoproject.pojo.NewsDetail;
import com.phl.emoproject.pojo.TaskListDetail;


public interface DetailFilesAdapterListener {
    void onDownload(View view, TaskListDetail.TaskFile fileInfo);
    void onRedirect(View view, TaskListDetail.TaskFile fileInfo);
}
