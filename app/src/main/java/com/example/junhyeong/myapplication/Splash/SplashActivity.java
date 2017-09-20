package com.example.junhyeong.myapplication.Splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.junhyeong.myapplication.Login.LoginActivity;
import com.example.junhyeong.myapplication.MapActivity;
import com.example.junhyeong.myapplication.R;


public class SplashActivity extends Activity {
    @Override

    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        /*
        //Intent intent = new Intent(this,MapActivity.class);
        //startActivity(intent);
        //setContentView(R.layout.activity_googlemap);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_login);
        finish();

        */
      try
        {
            Thread.sleep(1800);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
