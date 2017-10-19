package com.example.junhyeong.myapplication.Login;

/**
 * Created by wnsgu on 2017-08-04.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.junhyeong.myapplication.Main.MainActivity;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.Select.Select_MenuActivity;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class KakaoSignupActivity extends Activity {
    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
        setContentView(R.layout.activity_loading);
        requestMe();
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */

    protected void requestMe() { //유저의 정보를 받아오는 함수

        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    Log.e("requestMe failure", errorResult.getErrorMessage());
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e("kakao session closed :", "" + errorResult.getErrorMessage());
                Toast.makeText(KakaoSignupActivity.this, "다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
                Log.e("kakao_not_sign_up", "");
            }

            @Override
            public void onSuccess(UserProfile userProfile) {  //성공 시 userProfile 형태로 반환
                Logger.d("UserProfile : " + userProfile);
                Toast.makeText(getApplicationContext(), "카카오톡 로그인 성공", Toast.LENGTH_LONG).show();
                requestAccessTokenInfo_kakao();   // 토큰 정보 요청
                redirectMainActivity();
            }

        });
    }

    private void requestAccessTokenInfo_kakao() {
        AuthService.requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e("onSessionClosed", "onSessionClosed msg=" + "onSessionClosed");
                redirectMainActivity();
            }

            @Override
            public void onNotSignedUp() {
                Log.e("NotSigned", "onNotSignedUp msg=" + "onNotSignedUp");
                // not happened
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.e("fail", "failed to get access token info. msg=" + errorResult);
            }

            @Override
            public void onSuccess(AccessTokenInfoResponse accessTokenInfoResponse) {

                final long kakao_user_id = accessTokenInfoResponse.getUserId();
                Log.d("kakao_user_id", "" + kakao_user_id);



                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest Kakao_sign_up_Request = new StringRequest(Request.Method.POST, "http://13.124.127.124:3000/user/sign_up", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Kakao_sign_up_OK", response);
                        //GlobalApplication GUserID = (GlobalApplication) getApplication();
                       // GUserID.setGlobalUserID(response.optJSONObject(0).optJSONObject("data").optInt("id"));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Kakao_sign_up_ERROR", error.toString());

                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("token", String.valueOf(kakao_user_id));
                        params.put("platform", "kakao");
                        return params;
                    }
                };
                requestQueue.add(Kakao_sign_up_Request);

                // 서버간의 통신 텀을 줌
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest Kakao_sign_in_Request = new JsonObjectRequest(Request.Method.GET, "http://13.124.127.124:3000/user/sign_in", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        GlobalApplication GUserID = (GlobalApplication) getApplication();
                        Log.d("Kakao_sign_in_OK", response.toString());
                        if(response.optString("msg", "").equals("존재하지 않는 회원입니다"))
                        {
                            Log.e("Kakao_sign_in_Error", response.optString("msg"));
                            redirectLoginActivity();
                        }
                        else {
                            Log.d("Server_User_id", "" + response.optJSONObject("data").optInt("id", 0));
                            GUserID.setGlobalUserID(response.optJSONObject("data").optInt("id", 0));
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Kakao_sign_in_ERROR", error.toString());
                    }

                }) {
                    @Override
                    public Map getHeaders() throws AuthFailureError {
                        Map params = new HashMap();
                        params.put("token", String.valueOf(kakao_user_id));
                        params.put("platform", "kakao");
                        return params;
                    }
                };
                requestQueue.add(Kakao_sign_in_Request);
            }
        });
    }


    private void redirectMainActivity() {
        final Intent intent = new Intent(this, Select_MenuActivity.class);
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
}


