package com.example.junhyeong.myapplication.Select;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.example.junhyeong.myapplication.Google.MapsActivity;
import com.example.junhyeong.myapplication.Main.SafeFoodActivity;
import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-02.
 */

public class Select_MenuActivity extends Activity {
    ImageView iv, iv1, iv9;
    Intent intent, main, intent9;
    Intent location;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        intent = new Intent(this,SafeFoodActivity.class);
        intent9 = new Intent(this, MapsActivity.class);
        //main = new Intent(this, MainActivity.class); //2017-09-06 지역선택창 수정 전
        location = new Intent(this, Select_LocationActivity.class);
        iv = (ImageView)findViewById(R.id.iv10);
        iv.setOnClickListener(new MyListener());
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv1.setOnClickListener(new MyListener());
        iv9 = (ImageView)findViewById(R.id.iv9);
        iv9.setOnClickListener(new MyListener());

    }

    // 안심 먹거리 메뉴로 이동
    class MyListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.iv1:
        //           startActivity(main); //2017-09-06 지역선택창 수정 전
                    startActivity(location);
                    break;
                case R.id.iv9:
                    startActivity(intent9);
                    break;
                case R.id.iv10:
                    startActivity(intent);
                    break;
            }
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
