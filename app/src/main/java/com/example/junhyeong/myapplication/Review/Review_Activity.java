package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.GlobalApplication.GlobalApplication;
import com.example.junhyeong.myapplication.Popup.PopupActivity_Login;
import com.example.junhyeong.myapplication.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeonjin on 2017-09-28.
 */

public class Review_Activity extends Activity {
    ImageView ReviewBtn2;
    Intent intent,Review_Write, Popup_login;
    int store_id, unlogin_value;
    String classify;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        intent = getIntent();
        unlogin_value = intent.getIntExtra("mypage",0);
        store_id = intent.getIntExtra("store_id", 0);
        classify = intent.getStringExtra("classify");



        ReviewBtn2 = (ImageView)findViewById(R.id.ReviewBtn2);
        Review_Write = new Intent(this,Review_write_Activity.class);
        Popup_login = new Intent(this, PopupActivity_Login.class);
        Review_Write.putExtra("store_id", store_id);

        ReviewBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(unlogin_value==1)
                {
                    startActivity(Popup_login);
                }
                else
                startActivity(Review_Write);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


        JsonObjectRequest reviewStoreRequest = new JsonObjectRequest(Request.Method.GET, "http://13.124.127.124:3000/review/food", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("reviewResponse", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("reviewError", ""+error);
            }

        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                params.put("store_id", store_id);
                params.put("classify", classify);
                return params;
            }
        };
        requestQueue.add(reviewStoreRequest);
    }
}
