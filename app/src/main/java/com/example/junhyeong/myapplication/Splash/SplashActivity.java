package com.example.junhyeong.myapplication.Splash;

import android.app.Activity;
import android.os.Bundle;

import com.example.junhyeong.myapplication.R;


public class SplashActivity extends Activity {
    @Override

    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap);
     /*  try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, LoginActivity.class));
        finish();*/
    }
}
