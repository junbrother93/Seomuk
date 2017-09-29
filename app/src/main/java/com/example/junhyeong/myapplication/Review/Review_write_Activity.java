package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-28.
 */

public class Review_write_Activity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_review);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int width = (int) (display.getWidth() * 1.0);

        int height = (int) (display.getHeight() * 0.8);

        getWindow().getAttributes().width = width;

        getWindow().getAttributes().height = height;



    }
}
