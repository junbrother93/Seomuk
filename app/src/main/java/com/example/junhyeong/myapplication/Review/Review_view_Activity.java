package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.junhyeong.myapplication.R;

/**
 * Created by wnsgu on 2017-10-27.
 */

public class Review_view_Activity extends Activity{
    private Intent intent;
    private TextView txtTitle, txtText, txtScore, txtCreated;
    private String Title, Text, Created;
    private int Score;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_view);

        intent = getIntent();
        Title = intent.getStringExtra("review_title");
        Text = intent.getStringExtra("review_body");
        Created = intent.getStringExtra("review_created");
        Score = intent.getIntExtra("review_score",0);

        txtTitle = (TextView) findViewById(R.id.reviewTitle);
        txtText = (TextView) findViewById(R.id.reviewText);
        txtScore = (TextView) findViewById(R.id.reviewScore);
        txtCreated = (TextView) findViewById(R.id.reviewCreated);

        txtTitle.setText(Title);
        txtText.setText(Text);
        txtScore.setText(Integer.toString(Score));
        txtCreated.setText(Created);
    }
}
