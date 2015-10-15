package com.phl.emoproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.gson.reflect.TypeToken;
import com.phl.emoproject.R;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.home.DetailFileAdapter;
import com.phl.emoproject.home.HistoryNodesAdapter;
import com.phl.emoproject.pojo.NewsDetail;
import com.phl.emoproject.pojo.TaskList;
import com.phl.emoproject.pojo.TaskListDetail;
import com.phl.emoproject.utils.AsyncHttpClientUtils;
import com.phl.emoproject.utils.TaskDetailUtils;
import com.phl.emoproject.utils.ToolbarUtils;
import com.phl.emoproject.widget.WrapContentHeightListView;

import org.apache.http.Header;

import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_task_detail)
public class TaskDetailActivity extends RoboActionBarActivity {
    TaskList task;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.content)
    LinearLayout container;
    @InjectView(R.id.indicator)
    View indicator;
    @InjectView(R.id.files_listview)
    WrapContentHeightListView filesListView;
    @InjectView(R.id.nodes_listview)
    WrapContentHeightListView nodesListView;
    @InjectView(R.id.scrollView)
    ScrollView scrollView;

    DetailFileAdapter detailFileAdapter;
    HistoryNodesAdapter historyNodesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarUtils.normalSetting(this, toolbar);
        ToolbarUtils.setCenterTitle(toolbar, "待办详情");
        ToolbarUtils.setLeftTitleEnable(this, toolbar, true);

        Intent intent = getIntent();
        task = (TaskList) intent.getSerializableExtra("task");
        if (savedInstanceState != null) {
            task = (TaskList)savedInstanceState.getSerializable("task");
        }

        indicator.setVisibility(View.VISIBLE);
        AsyncHttpClientUtils.postTaskDetail(this,
                task.getTaskId(),
                task.getNodeId(),
                task.getHistoryNodeId(),
                task.getDiscussid(),
                new TaskDetailResponse());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("task", task);
    }

    @Override
    protected void onStop() {
        super.onStop();
        AsyncHttpClientUtils.cancelRequest(this);
    }

    private void generateViewByControls(List<TaskListDetail.Control> controls) {
        for (TaskListDetail.Control control : controls) {
            TaskDetailUtils.generateViewByControl(this, control,container);
        }
    }

    private class TaskDetailResponse extends BaseAsyncHttpResponseHandler<TaskListDetail> {
        public TaskDetailResponse() {
            super();
            setType(new TypeToken<TaskListDetail>(){}.getType());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, TaskListDetail taskListDetail) {
            indicator.setVisibility(View.GONE);
            if (taskListDetail.getMessage().getReturnCode() == 0) {
                //将controls设置
                generateViewByControls(taskListDetail.getControls());

                //将附件设置
                if(detailFileAdapter == null) {
                    detailFileAdapter = new DetailFileAdapter(TaskDetailActivity.this, taskListDetail.getFiles());
                    filesListView.setAdapter(detailFileAdapter);
                    filesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            TaskListDetail.TaskFile fileInfo = (TaskListDetail.TaskFile)adapterView.getItemAtPosition(i);

                        }
                    });
                } else {
                    detailFileAdapter.updateAdapter(taskListDetail.getFiles());
                }

                //审批意见
                if (historyNodesAdapter == null) {
                    historyNodesAdapter = new HistoryNodesAdapter(TaskDetailActivity.this, taskListDetail.getHistotyNodes());
                    nodesListView.setAdapter(historyNodesAdapter);
                } else {
                    historyNodesAdapter.updateAdapter(taskListDetail.getHistotyNodes());
                }

                //操作按钮

                //滑动到顶部
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       scrollView.scrollTo(0, 0);
                   }
               }, 300);
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            indicator.setVisibility(View.GONE);
        }
    }
}
