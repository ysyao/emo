package com.phl.emoproject.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.phl.emoproject.R;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.pojo.LoginRes;
import com.phl.emoproject.utils.AsyncHttpClientUtils;
import com.phl.emoproject.core.Constans;
import com.phl.emoproject.widget.radio_button.ILifeRadioButton;

import org.apache.http.Header;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import static com.phl.emoproject.core.Constans.LOGIN_ID;
import static com.phl.emoproject.core.Constans.PASSWORD;
import static com.phl.emoproject.core.Constans.LOGIN_ID_STORED;

@ContentView(R.layout.activity_main)
public class LoginActivity extends RoboActionBarActivity{
    @InjectView(R.id.container)
    View button;
    @InjectView(R.id.account)
    EditText account;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.radio_password)
    ILifeRadioButton isStore;
    @InjectView(R.id.indicator)
    ProgressBar indicator;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  2015/10/10 如果已经存储了账号了密码则直接填写好
        SharedPreferences sp = getSharedPreferences(Constans.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        String accountStr = sp.getString(LOGIN_ID, "");
        String passwordStr = sp.getString(PASSWORD, "");
        if (!checkIsNull(accountStr, passwordStr)) {
            account.setText(accountStr);
            password.setText(passwordStr);
            isStore.setChecked(true);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accountStr = account.getText().toString();
                String passStr = password.getText().toString();
                if (checkIsNull(accountStr, passStr)) {
                    Toast.makeText(LoginActivity.this, "请填写账号和密码", Toast.LENGTH_LONG).show();
                    return;
                }
                indicator.setVisibility(View.VISIBLE);
                button.setClickable(false);
                button.setEnabled(false);

                client = AsyncHttpClientUtils.postLoginRequest(LoginActivity.this,
                        accountStr,
                        passStr,
                        new LoginResponse());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (client != null) {
            client.cancelRequests(this, true);
        }
    }

    private void redirect() {
        //  2015/10/7 Start another activity
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
    }

    private boolean checkIsNull(String accountStr, String passStr) {
        return ("".equals(accountStr) || "".equals(passStr));
    }


    private class LoginResponse extends BaseAsyncHttpResponseHandler<LoginRes> {
        public LoginResponse() {
            super();
            setType(new TypeToken<LoginRes>() {
            }.getType());
        }
        @Override
        public void onSuccess(int statusCode, Header[] headers, LoginRes loginRes) {
            button.setClickable(true);
            button.setEnabled(true);
            indicator.setVisibility(View.GONE);
            if (loginRes.getMessage().getReturnCode() == 0) {
                redirect();

                String accountStr = account.getText().toString();
                String passStr = password.getText().toString();

                SharedPreferences sp = getSharedPreferences(Constans.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString(LOGIN_ID, accountStr);
                ed.putString(PASSWORD, passStr);
                //  2015/10/10 如果用户选择"保存账号密码则保存"
                if (isStore.isChecked()) {
                    ed.putString(LOGIN_ID_STORED, accountStr);
                }

                ed.apply();

                finish();
            } else {
                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            button.setClickable(true);
            button.setEnabled(true);
            indicator.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
