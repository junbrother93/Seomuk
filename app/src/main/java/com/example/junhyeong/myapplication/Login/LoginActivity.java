package com.example.junhyeong.myapplication.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.Select.Select_MenuActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wnsgu on 2017-08-04.
 */

public class LoginActivity extends Activity {

    private SessionCallback callback;      //콜백 선언 for kakao
    CallbackManager callbackManager;       //콜백 선언 for facebook
    Button unlogin;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intent = new Intent(this,Select_MenuActivity.class);
        unlogin = (Button)findViewById(R.id.unlogin);
        unlogin.setOnClickListener(new AccessListener());

        callback = new SessionCallback();                // 이 두개의 함수 중요함 for kakao
        Session.getCurrentSession().addCallback(callback);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();


        //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        // 페이스북 로그인 확인
        //Session.getCurrentSession();


        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "페이스북 로그인 성공", Toast.LENGTH_LONG).show();
                final AccessToken token = loginResult.getAccessToken();
                Log.e("fd_ID", token.getUserId());


                /**********************************************************************************
                 포스트
                **********************************************************************************/
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postStringRequest = new StringRequest(Request.Method.POST, "http://13.124.127.124:3000/user/sign_up", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("platform", "fb");
                        params.put("token", token.getUserId());

                        return params;
                    }
                };
                requestQueue.add(postStringRequest);
                /*
                StringRequest deleteStringRequest = new StringRequest(Request.Method.DELETE, "http://13.124.127.124:3000/review", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });
                */


                /**********************************************************************************/


                redirectMainActivity();
                // App code
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "페이스북 로그인 취소", Toast.LENGTH_LONG).show();
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "페이스북 로그인 실패", Toast.LENGTH_LONG).show();
                // App code
            }
        });


    }

    // 비회원 로그인
    class AccessListener implements Button.OnClickListener{
        @Override
        public void onClick(View v) {

            startActivity(intent);
            finish();
        }
    }


    // kakao
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }   // kakao
        super.onActivityResult(requestCode, resultCode, data);  // kakao, facebook 같이 사용
        callbackManager.onActivityResult(requestCode, resultCode, data);    // facebook
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }


    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Toast.makeText(getApplicationContext(), "세션 연결 성공", Toast.LENGTH_LONG).show();
            redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
            Toast.makeText(getApplicationContext(), "세션 연결 실패", Toast.LENGTH_LONG).show();
            redirectMainActivity();     // 세션 연결이 실패했을때
        }                               // 로그인화면을 다시 불러옴
    }

    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
        final Intent intent = new Intent(this, KakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    protected void redirectMainActivity() {
       final Intent intent = new Intent(this, Select_MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
