package com.example.junhyeong.myapplication.Data;

import android.app.Application;

/**
 * Created by wnsgu on 2017-09-29.
 */

public class GlobalUserId extends Application{
    private String GlobalUserID;
    public String getGlobalUserID()
    {
        return GlobalUserID;
    }
    public void setGlobalUserID(String globalUserID)
    {
        this.GlobalUserID = globalUserID;
    }
}
