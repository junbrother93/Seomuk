package com.example.junhyeong.myapplication.Main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.junhyeong.myapplication.Adapter.ListViewAdapter;
import com.example.junhyeong.myapplication.R;

/**
 * Created by yeonjin on 2017-09-02.
 */

public class SafeFoodActivity extends Activity{
    int num;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12;
    ListView list;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safefood);
        num=0;
        list = (ListView)findViewById(R.id.가나다순서);
        btn1 = (Button)findViewById(R.id.ㄱ);
        btn2 = (Button)findViewById(R.id.ㄴ);
        btn3 = (Button)findViewById(R.id.ㄷ);
        btn4 = (Button)findViewById(R.id.ㄹ);
        btn5 = (Button)findViewById(R.id.ㅁ);
        btn6 = (Button)findViewById(R.id.ㅂ);
        btn7 = (Button)findViewById(R.id.ㅅ);
        btn8 = (Button)findViewById(R.id.ㅇ);
        btn9 = (Button)findViewById(R.id.ㅈ);
        btn10 = (Button)findViewById(R.id.ㅊ);
        btn11 = (Button)findViewById(R.id.ㅍ);
        btn12 = (Button)findViewById(R.id.ㅎ);
        ListViewAdapter adapter;
        adapter = new ListViewAdapter();
        list.setAdapter(adapter);
        adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_launcher), "","아무개");

        btn1.setOnClickListener(new SafeFoodListener());
        btn2.setOnClickListener(new SafeFoodListener());
        btn3.setOnClickListener(new SafeFoodListener());
        btn4.setOnClickListener(new SafeFoodListener());
        btn5.setOnClickListener(new SafeFoodListener());
        btn6.setOnClickListener(new SafeFoodListener());
        btn7.setOnClickListener(new SafeFoodListener());
        btn8.setOnClickListener(new SafeFoodListener());
        btn9.setOnClickListener(new SafeFoodListener());
        btn10.setOnClickListener(new SafeFoodListener());
        btn11.setOnClickListener(new SafeFoodListener());
        btn12.setOnClickListener(new SafeFoodListener());


    }
    class SafeFoodListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.ㄱ :
                    if(num==0) {
                        btn2.setVisibility(View.GONE);
                        btn3.setVisibility(View.GONE);
                        btn4.setVisibility(View.GONE);
                        btn5.setVisibility(View.GONE);
                        btn6.setVisibility(View.GONE);
                        btn7.setVisibility(View.GONE);
                        btn8.setVisibility(View.GONE);
                        btn9.setVisibility(View.GONE);
                        btn10.setVisibility(View.GONE);
                        btn11.setVisibility(View.GONE);
                        btn12.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        num++;
                    }
                    else
                    {
                        btn2.setVisibility(View.VISIBLE);
                        btn3.setVisibility(View.VISIBLE);
                        btn4.setVisibility(View.VISIBLE);
                        btn5.setVisibility(View.VISIBLE);
                        btn6.setVisibility(View.VISIBLE);
                        btn7.setVisibility(View.VISIBLE);
                        btn8.setVisibility(View.VISIBLE);
                        btn9.setVisibility(View.VISIBLE);
                        btn10.setVisibility(View.VISIBLE);
                        btn11.setVisibility(View.VISIBLE);
                        btn12.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        num=0;
                    }
                    break;
                case R.id.ㄴ :
                    if(num==0) {
                        btn1.setVisibility(View.GONE);
                        btn3.setVisibility(View.GONE);
                        btn4.setVisibility(View.GONE);
                        btn5.setVisibility(View.GONE);
                        btn6.setVisibility(View.GONE);
                        btn7.setVisibility(View.GONE);
                        btn8.setVisibility(View.GONE);
                        btn9.setVisibility(View.GONE);
                        btn10.setVisibility(View.GONE);
                        btn11.setVisibility(View.GONE);
                        btn12.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        num++;
                    }
                    else
                    {
                        btn1.setVisibility(View.VISIBLE);
                        btn3.setVisibility(View.VISIBLE);
                        btn4.setVisibility(View.VISIBLE);
                        btn5.setVisibility(View.VISIBLE);
                        btn6.setVisibility(View.VISIBLE);
                        btn7.setVisibility(View.VISIBLE);
                        btn8.setVisibility(View.VISIBLE);
                        btn9.setVisibility(View.VISIBLE);
                        btn10.setVisibility(View.VISIBLE);
                        btn11.setVisibility(View.VISIBLE);
                        btn12.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        num=0;
                    }
                    break;
                case R.id.ㄷ :
                    if(num==0) {
                        btn2.setVisibility(View.GONE);
                        btn1.setVisibility(View.GONE);
                        btn4.setVisibility(View.GONE);
                        btn5.setVisibility(View.GONE);
                        btn6.setVisibility(View.GONE);
                        btn7.setVisibility(View.GONE);
                        btn8.setVisibility(View.GONE);
                        btn9.setVisibility(View.GONE);
                        btn10.setVisibility(View.GONE);
                        btn11.setVisibility(View.GONE);
                        btn12.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        num++;
                    }
                    else
                    {
                        btn2.setVisibility(View.VISIBLE);
                        btn1.setVisibility(View.VISIBLE);
                        btn4.setVisibility(View.VISIBLE);
                        btn5.setVisibility(View.VISIBLE);
                        btn6.setVisibility(View.VISIBLE);
                        btn7.setVisibility(View.VISIBLE);
                        btn8.setVisibility(View.VISIBLE);
                        btn9.setVisibility(View.VISIBLE);
                        btn10.setVisibility(View.VISIBLE);
                        btn11.setVisibility(View.VISIBLE);
                        btn12.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        num=0;
                    }
                    break;
                case R.id.ㄹ :if(num==0) {
                    btn2.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    btn5.setVisibility(View.GONE);
                    btn6.setVisibility(View.GONE);
                    btn7.setVisibility(View.GONE);
                    btn8.setVisibility(View.GONE);
                    btn9.setVisibility(View.GONE);
                    btn10.setVisibility(View.GONE);
                    btn11.setVisibility(View.GONE);
                    btn12.setVisibility(View.GONE);
                    list.setVisibility(View.VISIBLE);
                    num++;
                }
                else
                {
                    btn2.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    btn5.setVisibility(View.VISIBLE);
                    btn6.setVisibility(View.VISIBLE);
                    btn7.setVisibility(View.VISIBLE);
                    btn8.setVisibility(View.VISIBLE);
                    btn9.setVisibility(View.VISIBLE);
                    btn10.setVisibility(View.VISIBLE);
                    btn11.setVisibility(View.VISIBLE);
                    btn12.setVisibility(View.VISIBLE);
                    list.setVisibility(View.GONE);
                    num=0;
                }
                    break;
                case R.id.ㅁ :
                    if(num==0) {
                        btn2.setVisibility(View.GONE);
                        btn3.setVisibility(View.GONE);
                        btn4.setVisibility(View.GONE);
                        btn1.setVisibility(View.GONE);
                        btn6.setVisibility(View.GONE);
                        btn7.setVisibility(View.GONE);
                        btn8.setVisibility(View.GONE);
                        btn9.setVisibility(View.GONE);
                        btn10.setVisibility(View.GONE);
                        btn11.setVisibility(View.GONE);
                        btn12.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        num++;
                    }
                    else
                    {
                        btn2.setVisibility(View.VISIBLE);
                        btn3.setVisibility(View.VISIBLE);
                        btn4.setVisibility(View.VISIBLE);
                        btn1.setVisibility(View.VISIBLE);
                        btn6.setVisibility(View.VISIBLE);
                        btn7.setVisibility(View.VISIBLE);
                        btn8.setVisibility(View.VISIBLE);
                        btn9.setVisibility(View.VISIBLE);
                        btn10.setVisibility(View.VISIBLE);
                        btn11.setVisibility(View.VISIBLE);
                        btn12.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        num=0;
                    }
                    break;
                case R.id.ㅂ :
                    if(num==0) {
                        btn2.setVisibility(View.GONE);
                        btn3.setVisibility(View.GONE);
                        btn4.setVisibility(View.GONE);
                        btn5.setVisibility(View.GONE);
                        btn1.setVisibility(View.GONE);
                        btn7.setVisibility(View.GONE);
                        btn8.setVisibility(View.GONE);
                        btn9.setVisibility(View.GONE);
                        btn10.setVisibility(View.GONE);
                        btn11.setVisibility(View.GONE);
                        btn12.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        num++;
                    }
                    else
                    {
                        btn2.setVisibility(View.VISIBLE);
                        btn3.setVisibility(View.VISIBLE);
                        btn4.setVisibility(View.VISIBLE);
                        btn5.setVisibility(View.VISIBLE);
                        btn1.setVisibility(View.VISIBLE);
                        btn7.setVisibility(View.VISIBLE);
                        btn8.setVisibility(View.VISIBLE);
                        btn9.setVisibility(View.VISIBLE);
                        btn10.setVisibility(View.VISIBLE);
                        btn11.setVisibility(View.VISIBLE);
                        btn12.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        num=0;
                    }
                    break;
                case R.id.ㅅ :
                    if(num==0) {
                        btn2.setVisibility(View.GONE);
                        btn3.setVisibility(View.GONE);
                        btn4.setVisibility(View.GONE);
                        btn5.setVisibility(View.GONE);
                        btn6.setVisibility(View.GONE);
                        btn1.setVisibility(View.GONE);
                        btn8.setVisibility(View.GONE);
                        btn9.setVisibility(View.GONE);
                        btn10.setVisibility(View.GONE);
                        btn11.setVisibility(View.GONE);
                        btn12.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        num++;
                    }
                    else
                    {
                        btn2.setVisibility(View.VISIBLE);
                        btn3.setVisibility(View.VISIBLE);
                        btn4.setVisibility(View.VISIBLE);
                        btn5.setVisibility(View.VISIBLE);
                        btn6.setVisibility(View.VISIBLE);
                        btn1.setVisibility(View.VISIBLE);
                        btn8.setVisibility(View.VISIBLE);
                        btn9.setVisibility(View.VISIBLE);
                        btn10.setVisibility(View.VISIBLE);
                        btn11.setVisibility(View.VISIBLE);
                        btn12.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        num=0;
                    }
                    break;
                case R.id.ㅇ :
                    if(num==0) {
                        btn2.setVisibility(View.GONE);
                        btn3.setVisibility(View.GONE);
                        btn4.setVisibility(View.GONE);
                        btn5.setVisibility(View.GONE);
                        btn6.setVisibility(View.GONE);
                        btn7.setVisibility(View.GONE);
                        btn1.setVisibility(View.GONE);
                        btn9.setVisibility(View.GONE);
                        btn10.setVisibility(View.GONE);
                        btn11.setVisibility(View.GONE);
                        btn12.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        num++;
                    }
                    else
                    {
                        btn2.setVisibility(View.VISIBLE);
                        btn3.setVisibility(View.VISIBLE);
                        btn4.setVisibility(View.VISIBLE);
                        btn5.setVisibility(View.VISIBLE);
                        btn6.setVisibility(View.VISIBLE);
                        btn7.setVisibility(View.VISIBLE);
                        btn1.setVisibility(View.VISIBLE);
                        btn9.setVisibility(View.VISIBLE);
                        btn10.setVisibility(View.VISIBLE);
                        btn11.setVisibility(View.VISIBLE);
                        btn12.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        num=0;
                    }
                    break;
                case R.id.ㅈ :
                    if(num==0) {
                        btn2.setVisibility(View.GONE);
                        btn3.setVisibility(View.GONE);
                        btn4.setVisibility(View.GONE);
                        btn5.setVisibility(View.GONE);
                        btn6.setVisibility(View.GONE);
                        btn7.setVisibility(View.GONE);
                        btn8.setVisibility(View.GONE);
                        btn1.setVisibility(View.GONE);
                        btn10.setVisibility(View.GONE);
                        btn11.setVisibility(View.GONE);
                        btn12.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        num++;
                    }
                    else
                    {
                        btn2.setVisibility(View.VISIBLE);
                        btn3.setVisibility(View.VISIBLE);
                        btn4.setVisibility(View.VISIBLE);
                        btn5.setVisibility(View.VISIBLE);
                        btn6.setVisibility(View.VISIBLE);
                        btn7.setVisibility(View.VISIBLE);
                        btn8.setVisibility(View.VISIBLE);
                        btn1.setVisibility(View.VISIBLE);
                        btn10.setVisibility(View.VISIBLE);
                        btn11.setVisibility(View.VISIBLE);
                        btn12.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        num=0;
                    }
                    break;case R.id.ㅊ :
                if(num==0) {
                    btn2.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);
                    btn5.setVisibility(View.GONE);
                    btn6.setVisibility(View.GONE);
                    btn7.setVisibility(View.GONE);
                    btn8.setVisibility(View.GONE);
                    btn9.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    btn11.setVisibility(View.GONE);
                    btn12.setVisibility(View.GONE);
                    list.setVisibility(View.VISIBLE);
                    num++;
                }
                else
                {
                    btn2.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.VISIBLE);
                    btn5.setVisibility(View.VISIBLE);
                    btn6.setVisibility(View.VISIBLE);
                    btn7.setVisibility(View.VISIBLE);
                    btn8.setVisibility(View.VISIBLE);
                    btn9.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    btn11.setVisibility(View.VISIBLE);
                    btn12.setVisibility(View.VISIBLE);
                    list.setVisibility(View.GONE);
                    num=0;
                }
                break;
                case R.id.ㅍ :
                    if(num==0) {
                        btn2.setVisibility(View.GONE);
                        btn3.setVisibility(View.GONE);
                        btn4.setVisibility(View.GONE);
                        btn5.setVisibility(View.GONE);
                        btn6.setVisibility(View.GONE);
                        btn7.setVisibility(View.GONE);
                        btn8.setVisibility(View.GONE);
                        btn9.setVisibility(View.GONE);
                        btn10.setVisibility(View.GONE);
                        btn1.setVisibility(View.GONE);
                        btn12.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        num++;
                    }
                    else
                    {
                        btn2.setVisibility(View.VISIBLE);
                        btn3.setVisibility(View.VISIBLE);
                        btn4.setVisibility(View.VISIBLE);
                        btn5.setVisibility(View.VISIBLE);
                        btn6.setVisibility(View.VISIBLE);
                        btn7.setVisibility(View.VISIBLE);
                        btn8.setVisibility(View.VISIBLE);
                        btn9.setVisibility(View.VISIBLE);
                        btn10.setVisibility(View.VISIBLE);
                        btn1.setVisibility(View.VISIBLE);
                        btn12.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        num=0;
                    }
                    break;
                case R.id.ㅎ :
                    if(num==0) {
                        btn2.setVisibility(View.GONE);
                        btn3.setVisibility(View.GONE);
                        btn4.setVisibility(View.GONE);
                        btn5.setVisibility(View.GONE);
                        btn6.setVisibility(View.GONE);
                        btn7.setVisibility(View.GONE);
                        btn8.setVisibility(View.GONE);
                        btn9.setVisibility(View.GONE);
                        btn10.setVisibility(View.GONE);
                        btn11.setVisibility(View.GONE);
                        btn1.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        num++;
                    }
                    else
                    {
                        btn2.setVisibility(View.VISIBLE);
                        btn3.setVisibility(View.VISIBLE);
                        btn4.setVisibility(View.VISIBLE);
                        btn5.setVisibility(View.VISIBLE);
                        btn6.setVisibility(View.VISIBLE);
                        btn7.setVisibility(View.VISIBLE);
                        btn8.setVisibility(View.VISIBLE);
                        btn9.setVisibility(View.VISIBLE);
                        btn10.setVisibility(View.VISIBLE);
                        btn11.setVisibility(View.VISIBLE);
                        btn1.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        num=0;
                    }
                    break;
            }
        }
    }
    }
