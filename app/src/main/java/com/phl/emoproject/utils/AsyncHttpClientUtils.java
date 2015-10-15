package com.phl.emoproject.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.core.Constans;

public class AsyncHttpClientUtils {
    final static AsyncHttpClient client = new AsyncHttpClient();
    public static AsyncHttpClient createClient() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        return client;
    }

    public static AsyncHttpClient postLoginRequest(Context context, String account, String password, BaseAsyncHttpResponseHandler baseAsyncHttpResponseHandler) {
        AsyncHttpClient client = AsyncHttpClientUtils.createClient();
        RequestParams params = new RequestParams();
        params.put("LoginId", account);
        params.put("password", password);
//        client.post(context, Constans.LOGIN, params, baseAsyncHttpResponseHandler);
        return postRequest(context, client, Constans.LOGIN, params, baseAsyncHttpResponseHandler);
    }

    public static AsyncHttpClient postNewsList(Context context, String account, BaseAsyncHttpResponseHandler baseAsyncHttpResponseHandler) {
        AsyncHttpClient client = AsyncHttpClientUtils.createClient();
        RequestParams params = new RequestParams();
        params.put("LoginId", account);
//        client.post(context, Constans.NEWS_LIST, params, baseAsyncHttpResponseHandler);
        return postRequest(context, client, Constans.NEWS_LIST, params, baseAsyncHttpResponseHandler);
    }

    public static AsyncHttpClient postNewsDetail(Context context, String id, BaseAsyncHttpResponseHandler baseAsyncHttpResponseHandler) {
        AsyncHttpClient client = AsyncHttpClientUtils.createClient();
        RequestParams params = new RequestParams();
        params.put("id", id);
//        client.get(context, Constans.NEWS_DETAIL, params, baseAsyncHttpResponseHandler);
//        Log.d("ssssssssssss", client.getUrlWithQueryString(true, Constans.NEWS_DETAIL, params));
        return postRequest(context, client, Constans.NEWS_DETAIL, params, baseAsyncHttpResponseHandler);
    }

    public static AsyncHttpClient postTaskList(Context context, String pageNo, String pageSize, String taskType, String status, String keyWords, BaseAsyncHttpResponseHandler baseAsyncHttpResponseHandler) {
        AsyncHttpClient client = AsyncHttpClientUtils.createClient();
        RequestParams params = new RequestParams();
        SharedPreferences sp = context.getSharedPreferences(Constans.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String loginId = sp.getString(Constans.LOGIN_ID, "");
        params.put("LoginId", loginId);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("taskType", taskType);
        params.put("status", status);
        params.put("keyWords", keyWords);
        return postRequest(context, client, Constans.TASK_LIST, params, baseAsyncHttpResponseHandler);
    }

    public static AsyncHttpClient postTaskDetail(Context context, String taskId, String nodeId, String historyNodeId, String discussId, BaseAsyncHttpResponseHandler baseAsyncHttpResponseHandler) {
        AsyncHttpClient client = AsyncHttpClientUtils.createClient();
        RequestParams params = new RequestParams();
        SharedPreferences sp = context.getSharedPreferences(Constans.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String loginId = sp.getString(Constans.LOGIN_ID, "");
        params.put("LoginId", loginId);
        params.put("taskId", taskId);
        params.put("nodeId", nodeId);
        params.put("historyNodeId", historyNodeId);
        params.put("discussId", discussId);
        return postRequest(context, client, Constans.TASK_DETAIL, params, baseAsyncHttpResponseHandler);
    }

    public static void cancelRequest(Context context) {
        client.cancelRequests(context, true);
    }

    public static AsyncHttpClient postRequest(Context context, AsyncHttpClient client, String url, RequestParams params, BaseAsyncHttpResponseHandler baseAsyncHttpResponseHandler) {
        Log.d("ssssssssssss", client.getUrlWithQueryString(true, url, params));
        client.post(context, url, params, baseAsyncHttpResponseHandler);
        return client;
    }

    public static AsyncHttpClient getRequest(Context context, AsyncHttpClient client, String url, RequestParams params, BaseAsyncHttpResponseHandler baseAsyncHttpResponseHandler) {
        Log.d("ssssssssssss", client.getUrlWithQueryString(true, url, params));
        client.get(context, url, params, baseAsyncHttpResponseHandler);
        return client;
    }
}
