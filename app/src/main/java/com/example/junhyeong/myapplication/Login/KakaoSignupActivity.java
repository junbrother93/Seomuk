package com.example.junhyeong.myapplication.Login;

/**
 * Created by wnsgu on 2017-08-04.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.Data.GlobalUserId;
import com.example.junhyeong.myapplication.GlobalApplication.GlobalApplication;
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


public class KakaoSignupActivity extends AppCompatActivity {
    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                    Toast.makeText(getApplicationContext(), "카카오톡 로그인 실패", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "카카오톡 로그인 실패2", Toast.LENGTH_LONG).show();
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(getApplicationContext(), "카카오톡 세션 닫힘", Toast.LENGTH_LONG).show();
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
                Toast.makeText(getApplicationContext(), "카카오톡 회원이 아님", Toast.LENGTH_LONG).show();
            } // 카카오톡 회원이 아닐 시 showSignup(); 호출해야함

            @Override
            public void onSuccess(UserProfile userProfile) {  //성공 시 userProfile 형태로 반환
                Logger.d("UserProfile : " + userProfile);
                Toast.makeText(getApplicationContext(), "카카오톡 로그인 성공", Toast.LENGTH_LONG).show();
                requestAccessTokenInfo_kakao();   // 토큰 정보 요청
                redirectMainActivity();
            }

        });
    }

    private void redirectMainActivity() {
        startActivity(new Intent(this, Select_MenuActivity.class));
        finish();
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
    private void requestAccessTokenInfo_kakao() {
        AuthService.requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
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

                final long userId = accessTokenInfoResponse.getUserId();
                Log.e("userId", "userId=" + userId);

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest postStringRequest = new JsonObjectRequest(Request.Method.POST, "http://13.124.127.124:3000/user/sign_up", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // 서버 응답

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("platform", "kakao");
                        params.put("token", String.valueOf(userId));

                        return params;
                    }
                };
                requestQueue.add(postStringRequest);

                JsonObjectRequest postStringRequest2 = new JsonObjectRequest(Request.Method.GET, "http://13.124.127.124:3000/user/sign_in", new Response.Listener<JSONObject>() {
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
                        params.put("platform", "kakao");
                        params.put("token", String.valueOf(userId));
                        return params;
                    }
                };
                requestQueue.add(postStringRequest2);
            }
        });
    }
}


