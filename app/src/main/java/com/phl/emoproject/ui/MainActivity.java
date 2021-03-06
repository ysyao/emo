package com.phl.emoproject.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import static com.phl.emoproject.core.Constans.SHARED_PREFERENCE_NAME;
import static com.phl.emoproject.core.Constans.LOGIN_ID_STORED;
import roboguice.activity.RoboActionBarActivity;

public class MainActivity extends RoboActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        String loginId = sp.getString(LOGIN_ID_STORED, "");
        Intent i;
        if ("".equals(loginId)) {
            i = new Intent(this, LoginActivity.class);
        } else  {
            i = new Intent(this, HomeActivity.class);
        }
        startActivity(i);
        finish();
    }

}
