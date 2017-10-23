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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.GlobalApplication.GlobalApplication;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.Select.Select_MyPage_Activity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeonjin on 2017-09-28.
 */

public class Review_modification_Activity extends Activity {

    EditText ReviewTitle, ReviewBody;
    Button btnModification, btnDelete, btnClose;
    Intent intent, ActMypage;
    String title, body;
    int index, user_id, width, height, score, store_id;
    RatingBar rating;
    TextView Value;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_review_modification);
        intent = getIntent();
        ActMypage = new Intent(this, Select_MyPage_Activity.class);
        title = intent.getStringExtra("review_title");
        body = intent.getStringExtra("review_body");
        score = intent.getIntExtra("review_score", 0);
        index = intent.getIntExtra("review_index", 0);
        user_id = intent.getIntExtra("review_user_id", 0);
        store_id = intent.getIntExtra("review_store_id", 0);

        Log.d("modification_activity", title + " " + body + " " + score + " " + index + " " + user_id + " " + store_id);

        ReviewTitle = (EditText) findViewById(R.id.ReviewTitle);
        ReviewBody = (EditText) findViewById(R.id.ReviewBody);
        btnModification = (Button) findViewById(R.id.btnModification);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);
        rating = (RatingBar) findViewById(R.id.ratingBar);


        /*
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
        */

        ReviewTitle.setText(title);
        ReviewBody.setText(body);

        // 수정 클릭하면 수정 되도록 (수정 버튼 누르면 수정버튼은 확인 버튼으로, 닫기 버튼은 취소 버튼으로)
        // 닫기 클릭하면 닫도록
        btnModification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest reviewDeleteRequest = new StringRequest(Request.Method.DELETE, "http://13.124.127.124:3000/review", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reviewDeleteResponse", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("reviewDeleteResponse", ""+error);
                    }

                }) {
                    @Override
                    public Map getHeaders() throws AuthFailureError {
                        Map params = new HashMap();
                        params.put("user_id", Integer.toString(user_id));
                        params.put("review_id", Integer.toString(index));
                        return params;
                    }
                };
                requestQueue.add(reviewDeleteRequest);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                StringRequest reviewWriteRequest = new StringRequest(Request.Method.POST, "http://13.124.127.124:3000/review", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reviewReWriteResponse", "" + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("reviewReWriteError", error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        GlobalApplication GUserID = (GlobalApplication) getApplication();
                        Map<String, String> params = new HashMap<>();
                        params.put("title", ReviewTitle.getText().toString());
                        params.put("text", ReviewBody.getText().toString());
                        params.put("classify", "인증");
                        params.put("user_id", String.valueOf(GUserID.getGlobalUserID()));
                        params.put("store_id", String.valueOf(store_id));
                        params.put("score", String.valueOf(score));
                        Log.e("body", "body" + params);

                        return params;
                    }

                };
                requestQueue.add(reviewWriteRequest);

                setResult(RESULT_OK, ActMypage);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest reviewDeleteRequest = new StringRequest(Request.Method.DELETE, "http://13.124.127.124:3000/review", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reviewDeleteResponse", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("reviewDeleteResponse", ""+error);
                    }

                }) {
                    @Override
                    public Map getHeaders() throws AuthFailureError {
                        Map params = new HashMap();
                        params.put("user_id", Integer.toString(user_id));
                        params.put("review_id", Integer.toString(index));
                        return params;
                    }
                };
                requestQueue.add(reviewDeleteRequest);
                finish();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }
}
