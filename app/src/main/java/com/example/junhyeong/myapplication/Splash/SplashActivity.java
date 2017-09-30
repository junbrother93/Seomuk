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
import java.security.NoSuchAlgorithmException;


public class SplashActivity extends Activity {

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.junhyeong.myapplication", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

      try
        {
            Thread.sleep(1500);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, LoginActivity.class));
        finish();


    }


}
