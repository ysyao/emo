package com.phl.emoproject.core;

import android.app.Application;

import com.phl.emoproject.pojo.TaskListDetail;

import java.util.List;


public class EmoApplication extends Application {
    private static EmoApplication instance;
    private List<TaskListDetail.Control> controls;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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
}
