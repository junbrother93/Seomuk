package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.junhyeong.myapplication.R;

/**
 * Created by wnsgu on 2017-10-27.
 */

public class Review_view_Activity extends Activity{
    private Intent intent;
    private TextView title,body,score,date,id;
    private String Title, Text, Created;
    private int Score, UserId;
    private float convert;
    private String convert_date;
    private RatingBar ratingbar;
    private ImageView Close;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_review_view);

        intent = getIntent();
        Title = intent.getStringExtra("review_title");
        Text = intent.getStringExtra("review_body");
        Created = intent.getStringExtra("review_created");
        Score = intent.getIntExtra("review_score",0);
        UserId = intent.getIntExtra("review_UserId", 0);

        title = (TextView)findViewById(R.id.User_Title);
        body = (TextView)findViewById(R.id.User_Body);
        ratingbar = (RatingBar)findViewById(R.id.User_Rating);
        score = (TextView)findViewById(R.id.User_Score);
        date = (TextView)findViewById(R.id.User_date);
        id = (TextView)findViewById(R.id.User_Id);
        Close = (ImageView)findViewById(R.id.User_Close);

        convert = ((float)Score);
        convert_date = Created.substring(0,10);

        ratingbar.setRating(Score);
        title.setText(Title.toString());
        body.setText(Text.toString());
        score.setText(String.format("%.1f", convert));
        id.setText(Integer.toString(UserId)+ " 님의 리뷰");
        date.setText("최종 수정일 "+convert_date);

        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
