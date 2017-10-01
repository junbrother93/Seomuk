package com.example.junhyeong.myapplication.Data;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * Created by wnsgu on 2017-09-29.
 */

public class GlobalUserId implements Comparable<GlobalUserId>{
    private int GlobalUserID;
    public int getGlobalUserID()
    {
        return GlobalUserID;
    }
    public void setGlobalUserID(int globalUserID)
    {
        this.GlobalUserID = globalUserID;
    }

    @Override
    public int compareTo(@NonNull GlobalUserId o) {
        return 0;
    }
}
