package com.phl.emoproject.home;


import android.view.View;

import com.phl.emoproject.pojo.NewsDetail;
import com.phl.emoproject.pojo.TaskListDetail;

public interface FilesAdapterListener {
    void onDownload(View view, NewsDetail.FileInfo fileInfo);
    void onRedirect(View view, NewsDetail.FileInfo fileInfo);
}
