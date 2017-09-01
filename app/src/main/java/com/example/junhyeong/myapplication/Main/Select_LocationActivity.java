package com.example.junhyeong.myapplication.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-02.
 */

public class Select_LocationActivity extends Activity {
ImageView iv;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        intent = new Intent(this,MainActivity.class);
        iv = (ImageView)findViewById(R.id.iv10);
        iv.setOnClickListener(new MyListener());


    }

    class MyListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {

            startActivity(intent);
            finish();
        }
    }
}
