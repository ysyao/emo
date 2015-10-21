package com.phl.emoproject.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.phl.emoproject.R;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.home.HomeListType;
import com.phl.emoproject.home.TaskListAdapter;
import com.phl.emoproject.pojo.ListGenericClass;
import com.phl.emoproject.pojo.TaskList;
import com.phl.emoproject.pojo.TaskListDetail;
import com.phl.emoproject.utils.AsyncHttpClientUtils;
import com.phl.emoproject.utils.ToolbarUtils;
import com.phl.emoproject.utils.ViewUtils;
import com.phl.emoproject.widget.SwitchButton;

import org.apache.http.Header;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_task_list)
public class TaskListActivity extends RoboActionBarActivity implements
        SwipeRefreshLayout.OnRefreshListener,
        AdapterView.OnItemClickListener,
        SwitchButton.SwitchButtonListener{
    enum ListViewOper {
        REFRESH,MORE
    }

    @InjectView(R.id.switch_button)
    SwitchButton switchButton;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.list_page_listview)
    ListView listView;
//    @InjectView(R.id.no_data)
//    View noData;
    @InjectView(R.id.swipe_latyout)
    SwipeRefreshLayout swipeRefreshLayout;
    View moreData;
    HomeListType type;
    int pageNo = 1;
    int pageSize = 12;
    String taskType = "";
    String status = "0";
    String keyWords;
    TaskListAdapter taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarUtils.normalSetting(this, toolbar);
        ToolbarUtils.setLeftTitleEnable(this, toolbar, true);

        moreData = ViewUtils.createListViewFooterView(this);
        ViewUtils.setListViewFooterIndicatorVisible(moreData, false);

        Intent i = getIntent();
        type = (HomeListType)i.getSerializableExtra("type");
        if (savedInstanceState != null) {
            type = (HomeListType)savedInstanceState.getSerializable("type");
        }
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
        moreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postTaskListRequest(ListViewOper.MORE);
            }
        });
        listView.addFooterView(moreData);
        ViewUtils.setNoData(listView);
        listView.setOnItemClickListener(this);
        switchButton.setSwitchButtonListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("type", type);
    }

    private void postTaskListRequest(ListViewOper listViewOper) {
        switch (listViewOper) {
            case REFRESH:
                pageNo = 1;
                break;
            case MORE:
                ViewUtils.setListViewFooterIndicatorVisible(moreData, true);
                pageNo += 1;
                break;
        }
        AsyncHttpClientUtils.postTaskList(
                this,
                String.valueOf(pageNo),
                String.valueOf(pageSize),
                taskType,
                status,
                keyWords,
                new TaskListResponse(listViewOper));
    }

    @Override
    public void onRefresh() {
        switch (type) {
            case DAIBAN:
                ToolbarUtils.setCenterTitle(toolbar, "我的待办");
                taskType="1";
                postTaskListRequest(ListViewOper.REFRESH);
                break;
            case BEIAN:
                ToolbarUtils.setCenterTitle(toolbar, "我的备案");
                taskType="4";
                postTaskListRequest(ListViewOper.REFRESH);
                break;
        }
    }

    @Override
    public void onSwitchItemClicked(int position, View view) {
        if (position == 0) {
            status = "0";
        } else {
            status = "1";
        }
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TaskList task = (TaskList)adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this, TaskDetailActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        AsyncHttpClientUtils.cancelRequest(this);
    }

    private class TaskListResponse extends BaseAsyncHttpResponseHandler<ListGenericClass<TaskList>> {
        ListViewOper listViewOper;
        public TaskListResponse(ListViewOper listViewOper) {
            super();
            this.listViewOper = listViewOper;
            setType(new TypeToken<ListGenericClass<TaskList>>(){}.getType());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, ListGenericClass<TaskList> taskListListGenericClass) {
            swipeRefreshLayout.setRefreshing(false);
            switch (listViewOper) {
                case REFRESH:
                    if (taskListAdapter == null) {
                        taskListAdapter = new TaskListAdapter(getApplicationContext(), taskListListGenericClass.getJsonList());
                        listView.setAdapter(taskListAdapter);
                    } else {
                        taskListAdapter.updateAdapter(taskListListGenericClass.getJsonList());
                    }
                    break;
                case MORE:
                    ViewUtils.setListViewFooterIndicatorVisible(moreData, false);
                    if (taskListListGenericClass.getJsonList().size() == 0) {
                        ViewUtils.setListViewFooterTitle(moreData, "已无更多数据");
                        return;
                    }
                    if (taskListAdapter == null) {
                        taskListAdapter = new TaskListAdapter(getApplicationContext(), taskListListGenericClass.getJsonList());
                        listView.setAdapter(taskListAdapter);
                    } else {
                        taskListAdapter.addMoreData(taskListListGenericClass.getJsonList());
                    }
                    break;
            }

        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            swipeRefreshLayout.setRefreshing(false);
            ViewUtils.setListViewFooterIndicatorVisible(moreData, false);
        }
    }
}
