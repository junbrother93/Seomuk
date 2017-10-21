package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-28.
 */

public class Review_modification_Activity extends Activity {

    EditText ReviewTitle, ReviewBody;
    Button btnModification, btnClose;
    Intent intent;
    String title, body;
    int index, user_id, width, height;
    double score;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_review);
        intent = getIntent();

        title = intent.getStringExtra("review_title");
        body = intent.getStringExtra("review_body");
        score = intent.getDoubleExtra("review_score", 0);
        index = intent.getIntExtra("review_index", 0);
        user_id = intent.getIntExtra("review_user_id", 0);



        ReviewTitle = (EditText) findViewById(R.id.ReviewTitle);
        ReviewBody = (EditText) findViewById(R.id.ReviewBody);
        btnModification = (Button) findViewById(R.id.YesBtn);
        btnClose = (Button) findViewById(R.id.NoBtn);

        ReviewTitle.setText(title);
        ReviewBody.setText(body);
        btnModification.setText("수정");
        btnClose.setText("닫기");

        // 수정 클릭하면 수정 되도록 (수정 버튼 누르면 수정버튼은 확인 버튼으로, 닫기 버튼은 취소 버튼으로)
        // 닫기 클릭하면 닫도록
        btnModification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

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
