package com.phl.emoproject.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.phl.emoproject.R;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.home.NewsListAdapter;
import com.phl.emoproject.pojo.ListGenericClass;
import com.phl.emoproject.pojo.NewsList;
import com.phl.emoproject.utils.AsyncHttpClientUtils;
import com.phl.emoproject.core.Constans;

import org.apache.http.Header;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

public class NewsFragment extends RoboFragment implements
        SwipeRefreshLayout.OnRefreshListener{
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.news_listview)
    ListView newsLv;
    String loginId;
    NewsListAdapter newsListAdapter;

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onRefresh() {
        if (loginId == null) {
            SharedPreferences sp = getActivity().getSharedPreferences(Constans.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
            loginId = sp.getString(Constans.LOGIN_ID, "");
        }

        AsyncHttpClientUtils.postNewsList(getActivity(), loginId, new NewsListResponse());
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
                    newsListAdapter = new NewsListAdapter(getActivity(), newsListListGenericClass.getJsonList());
                    newsLv.setAdapter(newsListAdapter);
                    newsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            NewsList item = (NewsList)adapterView.getItemAtPosition(i);
                            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                            intent.putExtra("id", item.getId());
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
