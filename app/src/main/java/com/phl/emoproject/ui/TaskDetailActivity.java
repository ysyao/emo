package com.phl.emoproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.phl.emoproject.R;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.core.Constans;
import com.phl.emoproject.core.EmoApplication;
import com.phl.emoproject.home.DetailFileAdapter;
import com.phl.emoproject.home.DetailFilesAdapterListener;
import com.phl.emoproject.home.FilesAdapterListener;
import com.phl.emoproject.home.HistoryNodesAdapter;
import com.phl.emoproject.pojo.ActionListHolder;
import com.phl.emoproject.pojo.ListGenericClass;
import com.phl.emoproject.pojo.Message;
import com.phl.emoproject.pojo.NewsDetail;
import com.phl.emoproject.pojo.TaskList;
import com.phl.emoproject.pojo.TaskListDetail;
import com.phl.emoproject.utils.AsyncHttpClientUtils;
import com.phl.emoproject.utils.DownloadFileUtils;
import com.phl.emoproject.utils.TaskDetailUtils;
import com.phl.emoproject.utils.ToolbarUtils;
import com.phl.emoproject.widget.WrapContentHeightListView;

import org.apache.http.Header;

import java.io.File;
import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_task_detail)
public class TaskDetailActivity extends RoboActionBarActivity implements
        View.OnClickListener,
        DetailFilesAdapterListener {
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
    @InjectView(R.id.idea_control)
    ViewGroup ideaContainer;

    DetailFileAdapter detailFileAdapter;
    HistoryNodesAdapter historyNodesAdapter;

    ActionListHolder actionListHolder;

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

        postAllData();
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

    @Override
    public void onClick(View view) {
        TaskListDetail.Control control = (TaskListDetail.Control)view.getTag();
        String id = control.getId();
        if (id.equals("argeeButton")) {

        } else if (id.equals("rejectButton")) {

        } else if (id.equals("consultButton")) {
            postConsult();
        } else if (id.equals("assignButton")) {
            postAssign();
        } else if (id.equals("submitConsultButton")) {
            postConsultSuggestion();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constans.REQUEST_CODE_SEARCH) {
            View staffRootView = TaskDetailUtils.getControlViewById(container, "renyuanxuanze");
            EditText staff = TaskDetailUtils.getTextFieldValue(staffRootView);
            String user = data.getStringExtra("user");
            String[] values = user.split("#");
            staff.setText(values[2]);
            TaskListDetail.Control control = (TaskListDetail.Control)staffRootView.getTag();
            control.setValue(user);
            staffRootView.setTag(control);
        }
    }

    private void setActionListOnClickListener(View view) {
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    /**
     * 通过control数组建立输入框等ui
     * @param controls control数组
     */
    private void generateViewByControls(List<TaskListDetail.Control> controls) {
        for (TaskListDetail.Control control : controls) {
            TaskDetailUtils.generateViewByControl(this, control, container);
        }
    }

    /**
     * 获取全部数据
     */
    private void postAllData() {
        indicator.setVisibility(View.VISIBLE);

        ideaContainer.removeAllViews();
        container.removeAllViews();
        AsyncHttpClientUtils.postTaskDetail(this,
                task.getTaskId(),
                task.getNodeId(),
                task.getHistoryNodeId(),
                task.getDiscussid(),
                new TaskDetailResponse());
    }
    /**
     * 发起协商
     */
    private void postConsult() {
        View staffRootView = TaskDetailUtils.getControlViewById(container, "renyuanxuanze");
//        EditText staff = TaskDetailUtils.getTextFieldValue(staffRootView);
        //这里需要取tag中的值才比较准确
        TaskListDetail.Control staffControl = (TaskListDetail.Control)staffRootView.getTag();
        String staff = staffControl.getValue();
        if (staff == null || "".equals(staff)) {
            Toast.makeText(this, "请选择人员", Toast.LENGTH_LONG).show();
            return;
        }
        indicator.setVisibility(View.VISIBLE);
        View noticeRootView = TaskDetailUtils.getControlViewById(container, "tongzhifangshi");
        String notice = getNotice(noticeRootView);
        AsyncHttpClientUtils.postConsult(
                this,
                task.getHistoryNodeId(),
                task.getTitle(),
                staffControl.getValue(),
                task.getUrl(),
                notice,
                new PostConsult());
    }

    /**
     * 协商意见
     */
    private void postConsultSuggestion() {
        indicator.setVisibility(View.VISIBLE);
        View noticeRootView = TaskDetailUtils.getControlViewById(container, "tongzhifangshi");
        String notice = getNotice(noticeRootView);
        String consultText = actionListHolder.getSubmitConsultText().getText().toString();

        AsyncHttpClientUtils.postConsultSuggestion(
                this,
                task.getHistoryNodeId(),
                task.getTitle(),
                task.getDiscussid(),
                task.getUrl(),
                notice,
                consultText,
                new PostConsult());
    }

    private void postAssign() {
//        View flowRootView = TaskDetailUtils.getControlViewById(container, "xianshiliucheng");
//        EditText flowEt = TaskDetailUtils.getTextFieldValue(flowRootView);
        List<TaskListDetail.Control> controls = EmoApplication.getInstance().getControls();
        String instanceId = TaskDetailUtils.getNodeId(controls);

        View staffRootView = TaskDetailUtils.getControlViewById(container, "renyuanxuanze");
        //这里需要取tag中的值才比较准确
        TaskListDetail.Control staffControl = (TaskListDetail.Control)staffRootView.getTag();
        String[] values = staffControl.getValue().split("#");
        if ( values.length != 3) {
            Toast.makeText(this, "请选择人员", Toast.LENGTH_LONG).show();
            return;
        }
        indicator.setVisibility(View.VISIBLE);
        String scope = values[0] + "#" + values[1];
        String userDescription = values[2];

        AsyncHttpClientUtils.postAssign(
                this,
                instanceId,
                task.getNodeId(),
                scope,
                userDescription,
                new PostConsult());
    }

    private String getNotice(View noticeRootView) {
        boolean duanxin = TaskDetailUtils.isNotifyDuanXinSelected(noticeRootView);
        boolean email = TaskDetailUtils.isNotifyEmailSelected(noticeRootView);

        String notice = "";
        if (duanxin && !email) {
            notice = "1";
        } else if (!duanxin && email) {
            notice = "2";
        } else if (duanxin && email) {
            notice = "12";
        }
        return notice;
    }


    @Override
    public void onDownload(View view, final TaskListDetail.TaskFile fileInfo) {
        Toast.makeText(this, "正在下载"+fileInfo.getName(), Toast.LENGTH_LONG).show();
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(fileInfo.getFileUrl(), new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(getApplicationContext(), "下载出错", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File response) {
                // Do something with the file `response`
//                String path = DownloadFileUtils.storeFile(response, fileInfo.getFileName());
                Toast.makeText(getApplicationContext(), fileInfo.getName()+"已经下载完毕，可以查看。", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onRedirect(View view, TaskListDetail.TaskFile fileInfo) {
        DownloadFileUtils.openFile(this, fileInfo.getName());
    }

    /**
     * 获取详情数据
     */
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
                List<TaskListDetail.Control> controls = taskListDetail.getControls();
                EmoApplication.getInstance().setControls(controls);
                generateViewByControls(controls);

                //将附件设置
                if(detailFileAdapter == null) {
                    detailFileAdapter = new DetailFileAdapter(TaskDetailActivity.this, taskListDetail.getFiles());
                    filesListView.setAdapter(detailFileAdapter);
                    detailFileAdapter.setFilesAdapterListener(TaskDetailActivity.this);
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
                actionListHolder = TaskDetailUtils.generateIdeaByControls(TaskDetailActivity.this, taskListDetail.getIdeaControls(), ideaContainer);
                setActionListOnClickListener(actionListHolder.getApproval());
                setActionListOnClickListener(actionListHolder.getAssign());
                setActionListOnClickListener(actionListHolder.getConsult());
                setActionListOnClickListener(actionListHolder.getReject());
                setActionListOnClickListener(actionListHolder.getSubmitConsultButton());

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

    private class PostConsult extends BaseAsyncHttpResponseHandler<ListGenericClass<Void>> {
        public PostConsult() {
            super();
            setType(new TypeToken<ListGenericClass<Void>>(){}.getType());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, ListGenericClass<Void> listGenericClass) {
            indicator.setVisibility(View.GONE);
            if (listGenericClass.getMessage().getReturnCode() == 0) {
                Toast.makeText(TaskDetailActivity.this, "操作成功", Toast.LENGTH_LONG).show();
                postAllData();
            } else {
                Toast.makeText(TaskDetailActivity.this, "提交失败", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            indicator.setVisibility(View.GONE);
        }
    }
}
