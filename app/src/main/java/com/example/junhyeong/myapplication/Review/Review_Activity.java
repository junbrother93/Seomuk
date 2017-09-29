package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeonjin on 2017-09-28.
 */

public class Review_Activity extends Activity {
    ImageView ReviewBtn2;
    Intent intent, Review_Write;
    int store_id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        intent = getIntent();
        store_id = intent.getIntExtra("store_id", 0);
        ReviewBtn2 = (ImageView)findViewById(R.id.ReviewBtn2);
        Review_Write = new Intent(this,Review_write_Activity.class);
        Review_Write.putExtra("store_id", store_id);

        ReviewBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Review_Write);
            }
        });




    }
}
