package com.phl.emoproject.core;


import android.os.Environment;

public interface Constans {
    String SHARED_PREFERENCE_NAME = "emos";
    String LOGIN_ID = "loginId";
    String LOGIN_ID_STORED = "loginIdsdafsf";
    String PASSWORD = "password";

    String FILE_PATH = Environment.getExternalStorageDirectory().getPath()+"/EMO_FILES";

//    String PATH  = "http://oa.ynrjkj.com/mobile/EomWebService.asmx";
    String PATH  = "http://oa.ynrjkj.com/mobile";

    String LOGIN = PATH + "/login.aspx";
    String NEWS_LIST = PATH + "/getNewsList.aspx";
    String NEWS_DETAIL = PATH + "/getNewInfo.aspx";
    String TASK_LIST = PATH + "/getTaskList.aspx";
    String TASK_DETAIL = PATH + "/getTranactFlowForm.aspx";
    String CONSULT = PATH + "/consult.aspx";
    String USER_SCOPE = PATH + "/getUserScope.aspx";
    String ASSIGN = PATH + "/assign.aspx";
    String APPROVAL = PATH + "/approve.aspx";
    String REJECT = PATH + "/reject.aspx";

    int REQUEST_CODE_SEARCH = 0x22;

}
