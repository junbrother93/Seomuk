package com.example.junhyeong.myapplication.Popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.junhyeong.myapplication.Main.MainActivity;
import com.example.junhyeong.myapplication.R;


/**
 * Created by Junhyeong on 2017-08-22.
 */

public class PopupActivity_Local extends Activity {
    public String url;
    public Intent ActMain;
    public String local;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_location);
        ActMain = new Intent(this, MainActivity.class);


    }

    public void onClick(View view) {
        ImageView localId = (ImageView) findViewById(R.id.pop1);

        switch (view.getId())
        {
            case R.id.pop1:
                local = "강서구".toString();
                break;
            case R.id.pop2:
                local = "강남구".toString();
                break;
            case R.id.pop3:
                local = "강동구".toString();
                break;
            case R.id.pop4:
                local = "강북구".toString();
                break;
            case R.id.pop5:
                local = "관악구".toString();
                break;
            case R.id.pop6:
                local = "광진구".toString();
                break;
            case R.id.pop7:
                local = "구로구".toString();
                break;
            case R.id.pop8:
                local = "금천구".toString();
                break;
            case R.id.pop9:
                local = "노원구".toString();
                break;
            case R.id.pop10:
                local = "도봉구".toString();
                break;
            case R.id.pop11:
                local = "동대문구".toString();
                break;
            case R.id.pop12:
                local = "동작구".toString();
                break;
            case R.id.pop13:
                local = "마포구".toString();
                break;
            case R.id.pop14:
                local = "서대문구".toString();
                break;
            case R.id.pop15:
                local = "서초구".toString();
                break;
            case R.id.pop16:
                local = "성동구".toString();
                break;
            case R.id.pop17:
                local = "성북구".toString();
                break;
            case R.id.pop18:
                local = "송파구".toString();
                break;
            case R.id.pop19:
                local = "양천구".toString();
                break;
            case R.id.pop20:
                local = "영등포구".toString();
                break;
            case R.id.pop21:
                local = "용산구".toString();
                break;
            case R.id.pop22:
                local = "은평구".toString();
                break;
            case R.id.pop23:
                local = "종로구".toString();
                break;
            case R.id.pop24:
                local = "중구".toString();
                break;
            case R.id.pop25:
                local = "중랑구".toString();
                break;
        }
        Intent intent = getIntent();

        String menu = intent.getStringExtra("menu");
        url = "http://13.124.127.124:3000/auth/menu/"+ menu.toString() + "/loc/" + local;

        // url , local 값 MainActivity로 전송
        ActMain.putExtra("url", url);
        ActMain.putExtra("local", local);
        ActMain.putExtra("menu",menu);
        Log.e(url,"url");
        setResult(RESULT_OK, ActMain);
        finish();
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
