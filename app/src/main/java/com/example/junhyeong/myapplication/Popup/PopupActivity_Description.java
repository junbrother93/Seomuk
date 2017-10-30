package com.example.junhyeong.myapplication.Popup;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-10-16.
 */

public class PopupActivity_Description extends Activity {

    private ImageView BtnClose;
    private Typeface BMJUA;
    private TextView Txt_Menu;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_copyright);

        BMJUA = Typeface.createFromAsset(this.getAssets(), "fonts/BMJUA_ttf.ttf");
        Txt_Menu = (TextView)findViewById(R.id.textView14);
        Txt_Menu.setTypeface(BMJUA);

        BtnClose = (ImageView)findViewById(R.id.copyright_close);
        BtnClose.setOnClickListener(new View.OnClickListener() {
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
    @Override
    public void onBackPressed() {
        return;
    }
}

