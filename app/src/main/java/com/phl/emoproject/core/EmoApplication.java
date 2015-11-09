package com.phl.emoproject.core;

import android.app.Application;
import android.content.SharedPreferences;

import com.phl.emoproject.pojo.TaskListDetail;

import java.util.List;


public class EmoApplication extends Application {
    private static EmoApplication instance;
    private List<TaskListDetail.Control> controls;
    private String path = "http://oa.toprivermis.com/mobile";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SharedPreferences sp = getSharedPreferences(Constans.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        path = sp.getString(Constans.ADDRESS, path);
    }

    public static synchronized EmoApplication getInstance() {
        return instance;
    }

    public List<TaskListDetail.Control> getControls() {
        return controls;
    }

    public void setControls(List<TaskListDetail.Control> controls) {
        this.controls = controls;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        SharedPreferences sp = getSharedPreferences(Constans.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constans.ADDRESS, path);
        editor.apply();
        this.path = path;
    }
}
