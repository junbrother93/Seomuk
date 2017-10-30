package com.example.junhyeong.myapplication.Popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.junhyeong.myapplication.Login.LoginActivity;
import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-10-06.
 */

public class PopupActivity_end extends Activity{
    private ImageView Yes, No;
    private Intent logout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_end);
        logout = new Intent(this, LoginActivity.class);

        Yes = (ImageView)findViewById(R.id.pop_end);
        No = (ImageView)findViewById(R.id.pop_end_cancel);

        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
