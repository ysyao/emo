package com.phl.emoproject.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.phl.emoproject.R;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.home.HomeListType;
import com.phl.emoproject.home.TaskListAdapter;
import com.phl.emoproject.pojo.ListGenericClass;
import com.phl.emoproject.pojo.TaskList;
import com.phl.emoproject.utils.AsyncHttpClientUtils;
import com.phl.emoproject.utils.ViewUtils;

import org.apache.http.Header;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;


public class HomePageListItemFragment extends RoboFragment implements AdapterView.OnItemClickListener{
    @InjectView(R.id.list_page_listview)
    ListView listView;
    @InjectView(R.id.no_data)
    View noData;
    View moreData;
    HomeListType type;
    Activity activity;
    int pageNo = 1;
    int pageSize = 12;
    String taskType = "";
    String status = "";
    String keyWords;
    TaskListAdapter taskListAdapter;

    public static HomePageListItemFragment newInstance(HomeListType type) {
        HomePageListItemFragment homePageListItemFragment = new HomePageListItemFragment();
        homePageListItemFragment.type = type;
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", type);
        homePageListItemFragment.setArguments(bundle);
        return homePageListItemFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (HomeListType)getArguments().getSerializable("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_page_list_item, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setEmptyView(noData);
        moreData = ViewUtils.createListViewFooterView(activity);
        ViewUtils.setListViewFooterIndicatorVisible(moreData, false);
        moreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postTaskListRequest(ListViewOper.MORE);
            }
        });
        listView.addFooterView(moreData);
        listView.setOnItemClickListener(this);
        switch (type) {
            case DAIBAN:
                taskType="1";
                postTaskListRequest(ListViewOper.REFRESH);
                break;
            case BEIAN:
                taskType="4";
                postTaskListRequest(ListViewOper.REFRESH);
                break;
        }
    }

    private void postTaskListRequest(ListViewOper listViewOper) {
        ViewUtils.setListViewFooterIndicatorVisible(moreData, true);
        switch (listViewOper) {
            case REFRESH:
                pageNo = 1;
                break;
            case MORE:
                pageNo += 1;
                break;
        }
        AsyncHttpClientUtils.postTaskList(getActivity(),
                String.valueOf(pageNo),
                String.valueOf(pageSize),
                taskType,
                status,
                keyWords,
                new TaskListResponse(listViewOper));
    }

    @Override
    public void onStop() {
        super.onStop();
        AsyncHttpClientUtils.cancelRequest(activity);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (type) {
            case DAIBAN:
                TaskList task = (TaskList)adapterView.getItemAtPosition(i);
                Intent intent = new Intent(activity, TaskDetailActivity.class);
                intent.putExtra("task", task);
                startActivity(intent);
                break;
            case BEIAN:
                TaskList task1 = (TaskList)adapterView.getItemAtPosition(i);
                Intent intent1 = new Intent(activity, TaskDetailActivity.class);
                intent1.putExtra("task", task1);
                startActivity(intent1);
                break;
        }
    }

    enum ListViewOper {
        REFRESH,MORE
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
            ViewUtils.setListViewFooterIndicatorVisible(moreData, false);
            switch (listViewOper) {
                case REFRESH:
                    if (taskListAdapter == null) {
                        taskListAdapter = new TaskListAdapter(getActivity(), taskListListGenericClass.getJsonList());
                        listView.setAdapter(taskListAdapter);
                    } else {
                        taskListAdapter.updateAdapter(taskListListGenericClass.getJsonList());
                    }
                    break;
                case MORE:
                    if (taskListListGenericClass.getJsonList().size() == 0) {
                        ViewUtils.setListViewFooterTitle(moreData, "已无更多数据");
                        return;
                    }
                    if (taskListAdapter == null) {
                        taskListAdapter = new TaskListAdapter(getActivity(), taskListListGenericClass.getJsonList());
                        listView.setAdapter(taskListAdapter);
                    } else {
                        taskListAdapter.addMoreData(taskListListGenericClass.getJsonList());
                    }
                    break;
            }

        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            ViewUtils.setListViewFooterIndicatorVisible(moreData, false);
        }
    }

}
