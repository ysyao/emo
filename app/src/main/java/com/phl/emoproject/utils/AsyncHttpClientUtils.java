package com.phl.emoproject.utils;


import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;

public class AsyncHttpClientUtils {
    public static AsyncHttpClient createClient() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        return client;
    }

    public static void postLoginRequest(Context context, String account, String password, BaseAsyncHttpResponseHandler baseAsyncHttpResponseHandler) {
        AsyncHttpClient client = AsyncHttpClientUtils.createClient();
        RequestParams params = new RequestParams();
        params.put("LoginId", account);
        params.put("password", password);
        client.post(context, Constans.LOGIN, params, baseAsyncHttpResponseHandler);
    }
}
