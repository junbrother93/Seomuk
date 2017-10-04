package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-28.
 */

public class Review_watch_Activity extends Activity {

    EditText ReviewTitle, ReviewBody;

    int score;

    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_review);
        intent = getIntent();

        String title, body;
        int score, index, user_id;

        title = intent.getStringExtra("review_title");
        body = intent.getStringExtra("review_body");
        score = intent.getIntExtra("review_score", 0);
        index = intent.getIntExtra("review_index", 0);
        user_id = intent.getIntExtra("review_user_id", 0);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int width = (int) (display.getWidth() * 1.0);

        int height = (int) (display.getHeight() * 0.8);

        getWindow().getAttributes().width = width;

        getWindow().getAttributes().height = height;

        ReviewTitle = (EditText) findViewById(R.id.ReviewTitle);
        ReviewBody = (EditText) findViewById(R.id.ReviewBody);

        ReviewTitle.setText(title);
        ReviewBody.setText(body);

    }
}
