package com.phl.emoproject.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.phl.emoproject.R;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.core.Constans;
import com.phl.emoproject.home.FilesAdapterListener;
import com.phl.emoproject.home.NewsFileAdapter;
import com.phl.emoproject.pojo.NewsDetail;
import com.phl.emoproject.utils.AsyncHttpClientUtils;
import com.phl.emoproject.utils.DownloadFileUtils;
import com.phl.emoproject.utils.ToolbarUtils;
import com.phl.emoproject.widget.WrapContentHeightListView;


import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_news_detail)
public class NewsDetailActivity extends RoboActionBarActivity implements
    FilesAdapterListener{
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.webview)
    WebView webView;
    @InjectView(R.id.indicator)
    ProgressBar indicator;
    @InjectView(R.id.files_listview)
    WrapContentHeightListView filesListView;
    @InjectView(R.id.title)
    TextView ttile;
    NewsFileAdapter newsFileAdapter;
    String id;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarUtils.normalSetting(this, toolbar);
        ToolbarUtils.setLeftTitleEnable(this, toolbar, true);
        ToolbarUtils.setCenterTitle(toolbar, "新闻详情");

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("title");

        if (savedInstanceState != null) {
            id = savedInstanceState.getString("id");
            name = savedInstanceState.getString("title");
        }
        ttile.setText(name);
        indicator.setVisibility(View.VISIBLE);
        AsyncHttpClientUtils.postNewsDetail(this, id, new PostNewsDetail());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("id", id);
        outState.putString("title", name);
    }

    @Override
    protected void onStop() {
        super.onStop();
        AsyncHttpClientUtils.cancelRequest(this);
    }

    @Override
    public void onDownload(View view, final NewsDetail.FileInfo fileInfo) {
        Toast.makeText(this, "正在下载"+fileInfo.getFileName(), Toast.LENGTH_LONG).show();
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(fileInfo.getFileUrl(), new FileAsyncHttpResponseHandler(NewsDetailActivity.this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(getApplicationContext(), "下载出错", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File response) {
                // Do something with the file `response`
//                String path = DownloadFileUtils.storeFile(response, fileInfo.getFileName());
                Toast.makeText(getApplicationContext(), fileInfo.getFileName()+"已经下载完毕，可以查看。", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onRedirect(View view, NewsDetail.FileInfo fileInfo) {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType(Constans.FILE_PATH);
//        startActivityForResult(intent, 0x11);

        DownloadFileUtils.openFile(this, fileInfo.getFileName());
    }

    private class PostNewsDetail extends BaseAsyncHttpResponseHandler<NewsDetail> {
        public PostNewsDetail() {
            super();
            setType(new TypeToken<NewsDetail>() {
            }.getType());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, NewsDetail newsDetail) {
            indicator.setVisibility(View.GONE);
            if (newsDetail.getMessage().getReturnCode() == 0) {
                String var =newsDetail.getJsonObject().getContent().replaceAll("(\\%u)", "\\\\u");
                String html = org.apache.commons.lang.StringEscapeUtils.unescapeJava(var);
                webView.loadData(html, "text/html; charset=utf-8", "utf-8");
                if(newsFileAdapter == null) {
                    newsFileAdapter = new NewsFileAdapter(NewsDetailActivity.this, newsDetail.getFiles());
                    filesListView.setAdapter(newsFileAdapter);
                    newsFileAdapter.setFilesAdapterListener(NewsDetailActivity.this);
                } else {
                    newsFileAdapter.updateAdapter(newsDetail.getFiles());
                }
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            indicator.setVisibility(View.GONE);
        }
    }
}
