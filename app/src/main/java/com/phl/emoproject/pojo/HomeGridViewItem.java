package com.phl.emoproject.pojo;


public class HomeGridViewItem {
    private String name;
    private int drawable;
    public HomeGridViewItem() {}
    public HomeGridViewItem(String name, int drawable) {
        this.name = name;
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
