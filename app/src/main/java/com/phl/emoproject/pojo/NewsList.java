package com.phl.emoproject.pojo;


public class NewsList {
//    {"id":"2015090100000001","title":"test","click":"0","creatorName":"段家增","createDate":"2015/9/1 18:38:08"}
    private String id;
    private String title;
    private String click;
    private String creatorName;
    private String createDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
