package com.example.junhyeong.myapplication.Select;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.junhyeong.myapplication.Main.MainActivity;
import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-06.
 */

public class Select_LocationActivity extends Activity implements View.OnClickListener {
    public String Menuurl, Locationurl;
    String menu, local, url;
    int num;
    Intent Main, intent;
    TextView Txt_Loc;
    private Typeface BMJUA;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_location);
        intent = getIntent();
        menu = intent.getStringExtra("menu");
        num = intent.getIntExtra("mypage", 0);
        BMJUA = Typeface.createFromAsset(this.getAssets(), "fonts/BMJUA_ttf.ttf");
        Txt_Loc = (TextView)findViewById(R.id.Txt_Loc);
        Txt_Loc.setTypeface(BMJUA);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.local1:
                local = "강서구".toString();
                break;
            case R.id.local2:
                local = "강남구".toString();
                break;
            case R.id.local3:
                local = "강동구".toString();
                break;
            case R.id.local4:
                local = "강북구".toString();
                break;
            case R.id.local5:
                local = "관악구".toString();
                break;
            case R.id.local6:
                local = "광진구".toString();
                break;
            case R.id.local7:
                local = "구로구".toString();
                break;
            case R.id.local8:
                local = "금천구".toString();
                break;
            case R.id.local9:
                local = "노원구".toString();
                break;
            case R.id.local10:
                local = "도봉구".toString();
                break;
            case R.id.local11:
                local = "동대문구".toString();
                break;
            case R.id.local12:
                local = "동작구".toString();
                break;
            case R.id.local13:
                local = "마포구".toString();
                break;
            case R.id.local14:
                local = "서대문구".toString();
                break;
            case R.id.local15:
                local = "서초구".toString();
                break;
            case R.id.local16:
                local = "성동구".toString();
                break;
            case R.id.local17:
                local = "성북구".toString();
                break;
            case R.id.local18:
                local = "송파구".toString();
                break;
            case R.id.local19:
                local = "양천구".toString();
                break;
            case R.id.local20:
                local = "영등포구".toString();
                break;
            case R.id.local21:
                local = "용산구".toString();
                break;
            case R.id.local22:
                local = "은평구".toString();
                break;
            case R.id.local23:
                local = "종로구".toString();
                break;
            case R.id.local24:
                local = "중구".toString();
                break;
            case R.id.local25:
                local = "중랑구".toString();
                break;


        }
        Main = new Intent(this, MainActivity.class);
        Main.putExtra("mypage", num);
        Main.putExtra("local", local);
        Main.putExtra("menu", menu);
        setResult(RESULT_OK, Main);

        overridePendingTransition(0, 0);
        startActivity(Main);
    }
}
