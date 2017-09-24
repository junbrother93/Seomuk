package com.example.junhyeong.myapplication.Popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.junhyeong.myapplication.Main.MainActivity;
import com.example.junhyeong.myapplication.R;


/**
 * Created by Junhyeong on 2017-08-22.
 */

public class PopupActivity_Menu extends Activity {
    public String url,menu;
    public Intent ActMain;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_menu);
        ActMain = new Intent(this, MainActivity.class);


    }

    public void onClick(View view) {
        Button localId = (Button) findViewById(R.id.pop1_1);

        switch (view.getId())
        {
            case R.id.pop1_1:
                localId = (Button) findViewById(R.id.pop1_1);
                menu = "한식".toString();
                break;
            case R.id.pop2_2:
                localId = (Button) findViewById(R.id.pop2_2);
                menu = "중식".toString();
                break;
            case R.id.pop3_3:
                localId = (Button) findViewById(R.id.pop3_3);
                menu = "일식".toString();
                break;
            case R.id.pop4_4:
                localId = (Button) findViewById(R.id.pop4_4);
                menu = "양식".toString();
                break;
            case R.id.pop5_5:
                localId = (Button) findViewById(R.id.pop5_5);
                menu = "제과".toString();
                break;
            case R.id.pop6_6:
                localId = (Button) findViewById(R.id.pop6_6);
                menu = "카페".toString();
                break;
            case R.id.pop7_7:
                localId = (Button) findViewById(R.id.pop7_7);
                menu = "주점".toString();
                break;
            case R.id.pop8_8:
                localId = (Button) findViewById(R.id.pop8_8);
                menu = "패스트푸드&치킨".toString();
                break;
            case R.id.pop9_9:
                localId = (Button) findViewById(R.id.pop9_9);
                menu = "기타".toString();
                break;
            case R.id.pop10_10:
                localId = (Button) findViewById(R.id.pop10_10);
                menu = "food".toString();
                break;

        }
        Intent intent = getIntent();
        String local = intent.getStringExtra("local");
        url = "http://13.124.127.124:3000/auth/menu/"+ menu + "/loc/" + local.toString();

        // url , local 값 MainActivity로 전송
        ActMain.putExtra("url", url);
        ActMain.putExtra("menu", menu);
        ActMain.putExtra("local",local);
        Log.e(url,"url");
        setResult(RESULT_OK, ActMain);
        finish();



    }
}
