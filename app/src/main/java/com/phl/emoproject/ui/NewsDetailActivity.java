package com.phl.emoproject.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.google.gson.reflect.TypeToken;
import com.phl.emoproject.R;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.pojo.NewsDetail;
import com.phl.emoproject.utils.AsyncHttpClientUtils;
import com.phl.emoproject.utils.Constans;
import com.phl.emoproject.utils.ToolbarUtils;

import org.apache.http.Header;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_news_detail)
public class NewsDetailActivity extends RoboActionBarActivity{
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.webview)
    WebView webView;
    @InjectView(R.id.indicator)
    ProgressBar indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ToolbarUtils.normalSetting(this, toolbar);
        ToolbarUtils.setLeftTitleEnable(toolbar, true);
        ToolbarUtils.setCenterTitle(toolbar, "新闻详情");

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        indicator.setVisibility(View.VISIBLE);
        AsyncHttpClientUtils.postNewsDetail(this, id, new PostNewsDetail());
    }

    private class PostNewsDetail extends BaseAsyncHttpResponseHandler<NewsDetail> {
        public PostNewsDetail() {
            super();
            setType(new TypeToken<NewsDetail>(){}.getType());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, NewsDetail newsDetail) {
            indicator.setVisibility(View.GONE);
            if (newsDetail.getMessage().getReturnCode() == 0) {
//                webView.getSettings().setDefaultTextEncodingName("utf-8");
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
//                } else {
//                    webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//                }
                webView.loadData(getHtmlData(newsDetail.getJsonObject().getContent()), "text/html; charset=utf-8", "utf-8");
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            indicator.setVisibility(View.GONE);
        }

        private String getHtmlData(String bodyHTML) {
            String head = "<head>" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                    "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                    "</head>";
            return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
        }
    }
}
