package com.phl.emoproject.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.pojo.TaskList;
import com.phl.emoproject.pojo.TaskListDetail;
import com.phl.emoproject.utils.AsyncHttpClientUtils;

import org.apache.http.Header;

import roboguice.activity.RoboActionBarActivity;


public class TaskDetailActivity extends RoboActionBarActivity {
    TaskList task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        task = (TaskList) intent.getSerializableExtra("task");
        if (savedInstanceState != null) {
            task = (TaskList)savedInstanceState.getSerializable("task");
        }

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

    private class TaskDetailResponse extends BaseAsyncHttpResponseHandler<TaskListDetail> {
        public TaskDetailResponse() {
            super();
            setType(new TypeToken<TaskListDetail>(){}.getType());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, TaskListDetail taskListDetail) {

        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

        }
    }
}
