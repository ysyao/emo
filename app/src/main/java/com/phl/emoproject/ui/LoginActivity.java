package com.phl.emoproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.phl.emoproject.R;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class LoginActivity extends RoboActionBarActivity{
    @InjectView(R.id.login_button)
    View button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttpClient client = new AsyncHttpClient();
//                client.addHeader("");
//                client.get(this, )
            }
        });
    }

    private void redirect() {
        //  2015/10/7 Start another activity
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
    }

}
