package com.example.junhyeong.myapplication.Select;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.junhyeong.myapplication.Main.MainActivity;
import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-06.
 */

public class Select_LocationActivity extends Activity implements View.OnClickListener{
    public String Menuurl,Locationurl;
    public String menu;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_location);
        Intent intent = getIntent();
       menu = intent.getStringExtra("menu");

    }
    public void onClick(View v)
    {
        Button localId = (Button) findViewById(R.id.local1);
        String local, url;
        Intent Main = new Intent(this, MainActivity.class);

        switch (v.getId())
        {
            case R.id.local1 :
                localId = (Button) findViewById(R.id.local1);
                break;
            case R.id.local2 :
                localId = (Button) findViewById(R.id.local2);
                break;
            case R.id.local3 :
                localId = (Button) findViewById(R.id.local3);
                break;
            case R.id.local4 :
                localId = (Button) findViewById(R.id.local4);
                break;
            case R.id.local5 :
                localId = (Button) findViewById(R.id.local5);
                break;
            case R.id.local6 :
                localId = (Button) findViewById(R.id.local6);
                break;
            case R.id.local7 :
                localId = (Button) findViewById(R.id.local7);
                break;
            case R.id.local8 :
                localId = (Button) findViewById(R.id.local8);
                break;
            case R.id.local9 :
                localId = (Button) findViewById(R.id.local9);
                break;
            case R.id.local10 :
                localId = (Button) findViewById(R.id.local10);
                break;
            case R.id.local11:
                localId = (Button) findViewById(R.id.local11);
                break;
            case R.id.local12:
                localId = (Button) findViewById(R.id.local12);
                break;
            case R.id.local13 :
                localId = (Button) findViewById(R.id.local13);
                break;
            case R.id.local14 :
                localId = (Button) findViewById(R.id.local14);
                break;
            case R.id.local15 :
                localId = (Button) findViewById(R.id.local15);
                break;
            case R.id.local16 :
                localId = (Button) findViewById(R.id.local16);
                break;
            case R.id.local17 :
                localId = (Button) findViewById(R.id.local17);
                break;
            case R.id.local18 :
                localId = (Button) findViewById(R.id.local18);
                break;
            case R.id.local19 :
                localId = (Button) findViewById(R.id.local19);
                break;
            case R.id.local20 :
                localId = (Button) findViewById(R.id.local20);
                break;
            case R.id.local21 :
                localId = (Button) findViewById(R.id.local21);
                break;
            case R.id.local22 :
                localId = (Button) findViewById(R.id.local22);
                break;
            case R.id.local23 :
                localId = (Button) findViewById(R.id.local23);
                break;
            case R.id.local24 :
                localId = (Button) findViewById(R.id.local24);
                break;
            case R.id.local25 :
                localId = (Button) findViewById(R.id.local25);
                break;


        }
        local = localId.getText().toString();

        Main.putExtra("local", local);
        Main.putExtra("menu",menu);
        setResult(RESULT_OK, Main);


        overridePendingTransition(0, 0);
        startActivity(Main);
    }
}
