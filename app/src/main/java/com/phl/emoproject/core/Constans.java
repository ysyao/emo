package com.phl.emoproject.core;


public interface Constans {
    String SHARED_PREFERENCE_NAME = "emos";
    String LOGIN_ID = "loginId";
    String PASSWORD = "password";

//    String PATH  = "http://oa.ynrjkj.com/mobile/EomWebService.asmx";
    String PATH  = "http://oa.ynrjkj.com/mobile";

    String LOGIN = PATH + "/login.aspx";
    String NEWS_LIST = PATH + "/getNewsList.aspx";
    String NEWS_DETAIL = PATH + "/getNewInfo.aspx";
    String TASK_LIST = PATH + "/getTaskList.aspx";
    String TASK_DETAIL = PATH + "/getTranactFlowForm.aspx";
}
