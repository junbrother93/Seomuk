package com.example.junhyeong.myapplication.Popup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-10-16.
 */

public class PopupActivity_Description extends Activity {

    private ImageView BtnClose;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_copyright);

        BtnClose = (ImageView)findViewById(R.id.copyright_close);
        BtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
