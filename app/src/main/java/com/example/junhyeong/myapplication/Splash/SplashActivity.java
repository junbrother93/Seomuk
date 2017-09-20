package com.example.junhyeong.myapplication.Splash;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.example.junhyeong.myapplication.Login.LoginActivity;

import java.security.MessageDigest;


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
            getAppKeyHash();
            Thread.sleep(1800);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }

    }
}
