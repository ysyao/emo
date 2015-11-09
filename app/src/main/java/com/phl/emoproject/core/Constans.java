package com.phl.emoproject.core;


import android.os.Environment;

public interface Constans {
    String SHARED_PREFERENCE_NAME = "emos";
    String LOGIN_ID = "loginId";
    String LOGIN_ID_STORED = "loginIdsdafsf";
    String PASSWORD = "password";
    String ADDRESS = "address_ip";

    String FILE_PATH = Environment.getExternalStorageDirectory().getPath()+"/EMO_FILES";

//    String PATH  = "http://oa.ynrjkj.com/mobile/EomWebService.asmx";
//    String PATH  = "http://oa.ynrjkj.com/mobile";
    String PATH  = "http://oa.toprivermis.com/mobile";

    String LOGIN =  "/login.aspx";
    String NEWS_LIST ="/getNewsList.aspx";
    String NEWS_DETAIL ="/getNewInfo.aspx";
    String TASK_LIST_PAGE ="/getTaskList.aspx";
    String TASK_LIST ="/getApproveTaskList.aspx";
    String NOTIFY_LIST = "/getNotifyTaskList.aspx";
    String TASK_DETAIL = "/getTranactFlowForm.aspx";
    String CONSULT = "/consult.aspx";
    String USER_SCOPE = "/getUserScope.aspx";
    String ASSIGN = "/assign.aspx";
    String APPROVAL ="/approve.aspx";
    String REJECT = "/reject.aspx";
    String USER_INFO ="/getUserInfo.aspx";

    int REQUEST_CODE_SEARCH = 0x22;
    int REQUEST_CODE_SEARCH_NO_STAFF = 0x23;

}
