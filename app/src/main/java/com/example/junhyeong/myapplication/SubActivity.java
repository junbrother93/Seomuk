package com.example.junhyeong.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Junhyeong on 2017-09-01.
 */

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        final Intent ActMain = new Intent(this, MainActivity.class);
        startActivity(ActMain);
    }
}