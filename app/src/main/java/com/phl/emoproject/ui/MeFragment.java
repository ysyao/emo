package com.phl.emoproject.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.phl.emoproject.R;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.core.Constans;
import com.phl.emoproject.pojo.JsonObjectRes;
import com.phl.emoproject.pojo.UserInfo;
import com.phl.emoproject.utils.AsyncHttpClientUtils;

import org.apache.http.Header;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

import static com.phl.emoproject.core.Constans.LOGIN_ID_STORED;

public class MeFragment extends RoboFragment {
    @InjectView(R.id.userName)
    TextView userName;
    @InjectView(R.id.postName)
    TextView postName;
    @InjectView(R.id.departmentName)
    TextView departmentName;
    @InjectView(R.id.corporationName)
    TextView corporationName;
    @InjectView(R.id.indicator)
    View indicator;

    @InjectView(R.id.btn_exit)
    Button exitBtn;
    Activity activity;
    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        indicator.setVisibility(View.VISIBLE);
        AsyncHttpClientUtils.postUserInfo(activity, new UserInfoResPost());
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getActivity().getSharedPreferences(Constans.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(Constans.LOGIN_ID, "");
                editor.putString(Constans.PASSWORD, "");
                editor.putString(Constans.LOGIN_ID_STORED, "");
                editor.apply();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
    }

    private class UserInfoResPost extends BaseAsyncHttpResponseHandler<JsonObjectRes<UserInfo>> {
        public UserInfoResPost() {
            super();
            setType(new TypeToken<JsonObjectRes<UserInfo>>(){}.getType());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JsonObjectRes<UserInfo> userInfoJsonObjectRes) {
            indicator.setVisibility(View.GONE);
            if (userInfoJsonObjectRes.getMessage().getReturnCode() == 0) {
                UserInfo userInfo = userInfoJsonObjectRes.getJsonObject();
                userName.setText(userInfo.getUserName());
                postName.setText(userInfo.getPostName());
                departmentName.setText(userInfo.getDepartmentName());
                corporationName.setText(userInfo.getCorporationName());
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            indicator.setVisibility(View.GONE);
        }
    }
}
