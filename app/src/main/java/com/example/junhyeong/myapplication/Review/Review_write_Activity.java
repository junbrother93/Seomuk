package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.Data.GlobalUserId;
import com.example.junhyeong.myapplication.GlobalApplication.GlobalApplication;
import com.example.junhyeong.myapplication.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeonjin on 2017-09-28.
 */

public class Review_write_Activity extends Activity {

    TextView ReviewTitle, ReviewBody;
    String strReviewTitle, strReviewBody;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_review);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int width = (int) (display.getWidth() * 1.0);

        int height = (int) (display.getHeight() * 0.8);

        getWindow().getAttributes().width = width;

        getWindow().getAttributes().height = height;

        ReviewTitle = (TextView) findViewById(R.id.ReviewTitle);
        ReviewBody = (TextView) findViewById(R.id.ReviewBody);



    }

    public void onClick(View view){
        switch(view.getId())
        {
            case R.id.YesBtn:
                final int store_id = intent.getIntExtra("store_id",0);
                strReviewTitle = ReviewTitle.getText().toString();
                strReviewBody = ReviewBody.getText().toString();

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postStringRequest = new StringRequest(Request.Method.POST, "http://13.124.127.124:3000/review", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("1","1"+response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        GlobalApplication GUserID = (GlobalApplication) getApplication();
                        Map<String, String> params = new HashMap<>();
                        //params.put("title", strReviewTitle);
                        params.put("text", strReviewBody);
                        params.put("classify", "안심");
                        params.put("user_id", String.valueOf(GUserID.getGlobalUserID()));
                        params.put("store_id", String.valueOf(store_id));
                        Log.e("body", "body" + params);

                        return params;
                    }
                };
                requestQueue.add(postStringRequest);
                finish();
                break;
            case R.id.NoBtn:
                finish();
                break;
        }
    }
}
