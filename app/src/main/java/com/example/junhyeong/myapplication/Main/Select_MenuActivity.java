package com.example.junhyeong.myapplication.Main;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-02.
 */

public class Select_MenuActivity extends Activity {
ImageView iv;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        intent = new Intent(this,MainActivity.class);
        iv = (ImageView)findViewById(R.id.iv10);
        iv.setOnClickListener(new MyListener());


    }

    class MyListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            startActivity(intent);
        }
    }
    // 종료 할건지 물어보는 다이얼로그 생성
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("끌거야?")
                .setMessage("좀 더 봐줭")
                .setPositiveButton("시러", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("그랭", null)
                .show();
    }
}
