package com.phl.emoproject.core;


public interface Constans {
    String SHARED_PREFERENCE_NAME = "emos";
    String LOGIN_ID = "loginId";
    String PASSWORD = "password";

    String PATH  = "http://oa.ynrjkj.com/mobile/EomWebService.asmx";

    String LOGIN = PATH + "/Login";
    String NEWS_LIST = PATH + "/GetNewsList";
    String NEWS_DETAIL = PATH + "/GetNewInfo";
}
