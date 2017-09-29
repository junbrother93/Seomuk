package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-28.
 */

public class Review_Activity extends Activity {
    ImageView ReviewBtn2;
    Intent Review_Write;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ReviewBtn2 = (ImageView)findViewById(R.id.ReviewBtn2);
        Review_Write = new Intent(this,Review_write_Activity.class);

        ReviewBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Review_Write);
            }
        });
    }
}
