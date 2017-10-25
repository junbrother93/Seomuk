package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.GlobalApplication.GlobalApplication;
import com.example.junhyeong.myapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeonjin on 2017-09-28.
 */

public class Review_write_Activity extends Activity {

    EditText ReviewTitle, ReviewBody;
    RatingBar rating;
    TextView Value;
    int score;
    String strReviewTitle;
    String strReviewBody;
    String classify;
    Intent intent;
    int width;
    Display display;
    int store_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        classify = intent.getStringExtra("classify");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_review);

        rating = (RatingBar) findViewById(R.id.ratingBar);
        ReviewTitle = (EditText) findViewById(R.id.ReviewTitle);
        ReviewBody = (EditText) findViewById(R.id.ReviewBody);
        Value = (TextView) findViewById(R.id.Value);

        score = 1;
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // 강제로 1넣기
                if (ratingBar.getRating() <= 1.0) {
                    ratingBar.setRating(1);
                    Value.setText("1.0");
                    score = 1;
                } else if (ratingBar.getRating() > 1.0 && ratingBar.getRating() <= 2.0) {
                    ratingBar.setRating(2);
                    Value.setText("2.0");
                    score = 2;
                } else if (ratingBar.getRating() > 2.0 && ratingBar.getRating() <= 3.0) {
                    ratingBar.setRating(3);
                    Value.setText("3.0");
                    score = 3;
                } else if (ratingBar.getRating() > 3.0 && ratingBar.getRating() <= 4.0) {
                    ratingBar.setRating(4);
                    Value.setText("4.0");
                    score = 4;
                } else if (ratingBar.getRating() > 4.0 && ratingBar.getRating() <= 5.0) {
                    ratingBar.setRating(5);
                    Value.setText("5.0");
                    score = 5;
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.YesBtn:
                store_id = intent.getIntExtra("store_id", 0);
                strReviewTitle = ReviewTitle.getText().toString();
                strReviewBody = ReviewBody.getText().toString();

                if(strReviewTitle.equals("") || strReviewBody.equals(""))
                {

                }

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest reviewWriteRequest = new StringRequest(Request.Method.POST, "http://13.124.127.124:3000/review", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reviewWriteResponse", "" + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("reviewWriteError", error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        GlobalApplication GUserID = (GlobalApplication) getApplication();
                        Map<String, String> params = new HashMap<>();
                        params.put("title", strReviewTitle);
                        params.put("text", strReviewBody);
                        params.put("classify", classify);
                        params.put("user_id", String.valueOf(GUserID.getGlobalUserID()));
                        params.put("store_id", String.valueOf(store_id));
                        params.put("score", String.valueOf(score));
                        Log.e("body", "body" + params);

                        return params;
                    }

                };
                requestQueue.add(reviewWriteRequest);
                finish();
                break;
            case R.id.NoBtn:
                finish();
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}



