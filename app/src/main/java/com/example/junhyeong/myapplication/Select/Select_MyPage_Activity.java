package com.example.junhyeong.myapplication.Select;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.example.junhyeong.myapplication.Main.PodVolleyRequestQueue;
import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-26.
 */

public class Select_MyPage_Activity extends Activity {
    private RequestQueue mQueue;
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mypage);
       mQueue = PodVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

    }
}
