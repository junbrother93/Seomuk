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

public class PopupActivity_Local extends Activity {
    public String url;
    public Intent ActMain;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_location);
        ActMain = new Intent(this, MainActivity.class);


    }

    public void onClick(View view) {
        Button localId = (Button) findViewById(R.id.pop1);
        String local;
        switch (view.getId())
        {
            case R.id.pop1:
                localId = (Button) findViewById(R.id.pop1);
                break;
            case R.id.pop2:
                localId = (Button) findViewById(R.id.pop2);
                break;
            case R.id.pop3:
                localId = (Button) findViewById(R.id.pop3);
                break;
            case R.id.pop4:
                localId = (Button) findViewById(R.id.pop4);
                break;
            case R.id.pop5:
                localId = (Button) findViewById(R.id.pop5);
                break;
            case R.id.pop6:
                localId = (Button) findViewById(R.id.pop6);
                break;
            case R.id.pop7:
                localId = (Button) findViewById(R.id.pop7);
                break;
            case R.id.pop8:
                localId = (Button) findViewById(R.id.pop8);
                break;
            case R.id.pop9:
                localId = (Button) findViewById(R.id.pop9);
                break;
            case R.id.pop10:
                localId = (Button) findViewById(R.id.pop10);
                break;
            case R.id.pop11:
                localId = (Button) findViewById(R.id.pop11);
                break;
            case R.id.pop12:
                localId = (Button) findViewById(R.id.pop12);
                break;
            case R.id.pop13:
                localId = (Button) findViewById(R.id.pop13);
                break;
            case R.id.pop14:
                localId = (Button) findViewById(R.id.pop14);
                break;
            case R.id.pop15:
                localId = (Button) findViewById(R.id.pop15);
                break;
            case R.id.pop16:
                localId = (Button) findViewById(R.id.pop16);
                break;
            case R.id.pop17:
                localId = (Button) findViewById(R.id.pop17);
                break;
            case R.id.pop18:
                localId = (Button) findViewById(R.id.pop18);
                break;
            case R.id.pop19:
                localId = (Button) findViewById(R.id.pop19);
                break;
            case R.id.pop20:
                localId = (Button) findViewById(R.id.pop20);
                break;
            case R.id.pop21:
                localId = (Button) findViewById(R.id.pop21);
                break;
            case R.id.pop22:
                localId = (Button) findViewById(R.id.pop22);
                break;
            case R.id.pop23:
                localId = (Button) findViewById(R.id.pop23);
                break;
            case R.id.pop24:
                localId = (Button) findViewById(R.id.pop24);
                break;
            case R.id.pop25:
                localId = (Button) findViewById(R.id.pop25);
                break;
        }
        Intent intent = getIntent();
        local = localId.getText().toString();
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
}
