package com.example.junhyeong.myapplication.Select;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.junhyeong.myapplication.Main.MainActivity;
import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-02.
 */

public class Select_MenuActivity extends Activity implements View.OnClickListener{
    ImageView iv;
    Intent location,Main;
    String menu, Menuurl;
    int AnsimValue =0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);
        location = new Intent(this, Select_LocationActivity.class);//지역선택창
        Main = new Intent(this, MainActivity.class);//지역선택창
    }

    @Override
    public void onClick(View v) {
        iv = (ImageView)findViewById(R.id.iv1);

        switch(v.getId())
        {
            case R.id.iv1:
                iv = (ImageView) findViewById(R.id.iv1);
                menu ="한식".toString();
                break;
            case R.id.iv2:
                iv = (ImageView) findViewById(R.id.iv2);
                menu ="중식".toString();
                break;
            case R.id.iv3:
                iv = (ImageView) findViewById(R.id.iv3);
                menu ="일식".toString();
                break;
            case R.id.iv4:
                iv = (ImageView) findViewById(R.id.iv4);
                menu ="양식".toString();
                break;
            case R.id.iv5:
                iv = (ImageView) findViewById(R.id.iv5);
                menu ="제과".toString();
                break;
            case R.id.iv6:
                iv = (ImageView) findViewById(R.id.iv6);
                menu ="카페".toString();
                break;
            case R.id.iv7:
                iv = (ImageView) findViewById(R.id.iv7);
                menu ="주점".toString();
                break;
            case R.id.iv8:
                iv = (ImageView) findViewById(R.id.iv8);
                menu ="패스트푸드&치킨".toString();
                break;
            case R.id.iv9:
                iv = (ImageView) findViewById(R.id.iv9);
                menu ="기타".toString();
                break;
            case R.id.iv10:
                iv = (ImageView) findViewById(R.id.iv10);
                menu ="food".toString();
                AnsimValue=1;
                break;

        }
        Menuurl = "http://13.124.127.124:3000/auth/menu/"+menu+"/loc/";
        location.putExtra("Menuurl",Menuurl);//로케이션으로 보내기
        Main.putExtra("AnsimValue",AnsimValue);//안심먹거리일때 value값 변환된거 보내기
        setResult(RESULT_OK, Main);
        setResult(RESULT_OK, location);
        finish();
        overridePendingTransition(0, 0);
        startActivity(location);
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
