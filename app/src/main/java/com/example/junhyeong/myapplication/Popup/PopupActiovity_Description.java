package com.example.junhyeong.myapplication.Popup;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-10-16.
 */

public class PopupActiovity_Description extends Activity {

    private ImageView BtnClose;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_copyright);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int) (display.getWidth() * 1.0);

        getWindow().getAttributes().width = width;

        BtnClose = (ImageView)findViewById(R.id.copyright_close);
        BtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
