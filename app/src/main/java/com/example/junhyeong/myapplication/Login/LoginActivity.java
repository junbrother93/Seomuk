package com.example.junhyeong.myapplication.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.GlobalApplication.GlobalApplication;
import com.example.junhyeong.myapplication.Popup.PopupActiovity_Description;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.Select.Select_MenuActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wnsgu on 2017-08-04.
 */

public class LoginActivity extends Activity {

    private SessionCallback callback;      //콜백 선언 for kakao
    CallbackManager callbackManager;       //콜백 선언 for facebook
    Button unlogin;
    Intent intent,mypage,description;
    ImageView copyright;
    private ImageView fakefacebook, fakekakao;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intent = new Intent(this,Select_MenuActivity.class);
        description = new Intent(this, PopupActiovity_Description.class);

        unlogin = (Button)findViewById(R.id.unlogin);
        copyright = (ImageView)findViewById(R.id.copyright);
        unlogin.setOnClickListener(new AccessListener());

        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(description);
            }
        });



        //for kakao
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);

        //for facebook
        callbackManager = CallbackManager.Factory.create();

        Log.e("test","test"+AccessToken.getCurrentAccessToken());
/*

        Log.e("CurrentAccessToken : ","CurrentAccessToken : "+AccessToken.getCurrentAccessToken());



        if(Session.getCurrentSession().isClosed()==false)
        {
            Toast.makeText(getApplicationContext(), "카카오톡으로 로그인이 되어있음", Toast.LENGTH_LONG).show();
            redirectSignupActivity();
        }
        else
        {
            // 페이스북 로그인 되어있을때....
        }

*/




        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "페이스북 로그인 성공", Toast.LENGTH_LONG).show();
                requestAccessTokenInfo(loginResult);
                redirectSelect_MenuActivity();
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

            GlobalApplication GUserID = (GlobalApplication) getApplication();
            GUserID.setGlobalUserID(1);
            num = GUserID.getGlobalUserID();
            intent.putExtra("mypage",num);
            startActivity(intent);

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
            Toast.makeText(getApplicationContext(), "KAKAO_onSessionOpened", Toast.LENGTH_LONG).show();
            redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
            Toast.makeText(getApplicationContext(), "KAKAO_onSessionOpenFailed", Toast.LENGTH_LONG).show();
            redirectLoginActivity();     // 세션 연결이 실패했을때 로그인 화면을 다시 불러옴
        }
    }

    protected void redirectSignupActivity() {
        final Intent intent = new Intent(this, KakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    protected void redirectSelect_MenuActivity() {
        final Intent intent = new Intent(this, Select_MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }


    private void requestAccessTokenInfo(LoginResult loginResult) {
        final AccessToken token = loginResult.getAccessToken();
        Log.e("fd_ID", token.getUserId());

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest Facebook_sign_up_Request = new StringRequest(Request.Method.POST, "http://13.124.127.124:3000/user/sign_up", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Facebook_sign_up_OK : ","Facebook_sign_up_OK : " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Facebook_sign_up_ERROR:","Facebook_sign_up_ERROR : " + error);

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
        requestQueue.add(Facebook_sign_up_Request);

        JsonObjectRequest Facebook_sign_in_Request = new JsonObjectRequest(Request.Method.GET, "http://13.124.127.124:3000/user/sign_in", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GlobalApplication GUserID = (GlobalApplication) getApplication();
                Log.e("response2 : ","response2 : " + response);
                Log.e("id", "id" + response.optJSONObject("data").optInt("id", 0));
                GUserID.setGlobalUserID(response.optJSONObject("data").optInt("id", 0));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                params.put("platform", "fb");
                params.put("token", token.getUserId());
                return params;
            }
        };
        requestQueue.add(Facebook_sign_in_Request);
    }
}
