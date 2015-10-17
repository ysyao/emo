package com.phl.emoproject.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.phl.emoproject.R;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.core.Constans;
import com.phl.emoproject.home.HomeListType;
import com.phl.emoproject.home.NewsListAdapter;
import com.phl.emoproject.pojo.ListGenericClass;
import com.phl.emoproject.pojo.NewsList;
import com.phl.emoproject.utils.AsyncHttpClientUtils;
import com.phl.emoproject.utils.ToolbarUtils;
import com.phl.emoproject.utils.ViewUtils;

import org.apache.http.Header;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_news_list)
public class NewsListActivity extends RoboActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.list_page_listview)
    ListView listView;
    @InjectView(R.id.no_data)
    View noData;
    @InjectView(R.id.swipe_latyout)
    SwipeRefreshLayout swipeRefreshLayout;
    String loginId;
    NewsListAdapter newsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarUtils.normalSetting(this, toolbar);
        ToolbarUtils.setLeftTitleEnable(this, toolbar, true);
        ToolbarUtils.setCenterTitle(toolbar, "新闻列表");

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorScheme(R.color.blue,
                R.color.greenyellow,
                R.color.orange, R.color.red);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
        listView.setEmptyView(noData);
    }

    @Override
    public void onRefresh() {
        if (loginId == null) {
            SharedPreferences sp = getSharedPreferences(Constans.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
            loginId = sp.getString(Constans.LOGIN_ID, "");
        }

        AsyncHttpClientUtils.postNewsList(this, loginId, new NewsListResponse());
    }

    @Override
    protected void onStop() {
        super.onStop();
        AsyncHttpClientUtils.cancelRequest(this);
    }

    private class NewsListResponse extends BaseAsyncHttpResponseHandler<ListGenericClass<NewsList>> {
        public NewsListResponse() {
            super();
            setType(new TypeToken<ListGenericClass<NewsList>>(){}.getType());
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, ListGenericClass<NewsList> newsListListGenericClass) {
            swipeRefreshLayout.setRefreshing(false);
            if (newsListListGenericClass.getMessage().getReturnCode() == 0) {
                //  2015/10/10 Set Adapter
                if (newsListAdapter == null) {
                    newsListAdapter = new NewsListAdapter(getApplicationContext(), newsListListGenericClass.getJsonList());
                    listView.setAdapter(newsListAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            NewsList item = (NewsList)adapterView.getItemAtPosition(i);
                            Intent intent = new Intent(getApplicationContext(), NewsDetailActivity.class);
                            intent.putExtra("id", item.getId());
                            intent.putExtra("title", item.getTitle());
                            startActivity(intent);
                        }
                    });
                } else {
                    newsListAdapter.updateAdapter(newsListListGenericClass.getJsonList());
                }
            }
        }

    }
}
