package com.example.junhyeong.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity implements Response.Listener<JSONObject>,
        Response.ErrorListener {
    public static final String REQUEST_TAG = "MainActivity";
    private final int DYNAMIC_VIEW_ID = 10000;
    private RequestQueue mQueue;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = PodVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();
        final Intent ActLocal = new Intent(this, PopupActivity_Local.class);

        mButton = (Button)findViewById(R.id.mButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(ActLocal, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // PopupActivity 에서 보낸 url, local 값을 받음
        String url = data.getStringExtra("url");
        String local = data.getStringExtra("local");

        //
        mButton.setText(local);
        final PodJsonRequest jsonRequest = new PodJsonRequest(Request.Method.GET, url, new JSONObject(), MainActivity.this, MainActivity.this);
        jsonRequest.setTag(REQUEST_TAG);
        mQueue.add(jsonRequest);
        onBackPressed();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // 에러 났을경우..
    }

    @Override
    public void onResponse(JSONObject response) {
        int total = response.optInt("total", 0);    // 총 갯수
        // 리스트 생성
        ArrayList<JSONObject> ArrData = new ArrayList<JSONObject>();
        ArrayList<Integer> ArrCTF_CODE = new ArrayList<Integer>();
        ArrayList<Integer> ArrCTF_TYPE = new ArrayList<Integer>();
        ArrayList<String> ArrCTF_TYPE_NAME = new ArrayList<String>();
        ArrayList<String> ArrCTF_NAME = new ArrayList<String>();
        ArrayList<Double> ArrCTF_X = new ArrayList<Double>();
        ArrayList<Double> ArrCTF_Y = new ArrayList<Double>();
        ArrayList<String> ArrCTF_ADDR = new ArrayList<String>();
        ArrayList<String> ArrCTF_TEL = new ArrayList<String>();

        // 리스트뷰랑 어댑터..
        ListView listview = (ListView)findViewById(R.id.listview1);
        ListViewAdapter adapter;
        adapter = new ListViewAdapter();
        listview.setAdapter(adapter);

        // 스크롤 뷰 안에 있는 리스트 뷰 스크롤 되게설정
        final ScrollView scrollview = (ScrollView)findViewById(R.id.scrollview);
        listview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollview.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        for(int i = 0; i <= total-1; i++) // index 값이라서 총 갯수에서 1을 빼줌
        {
            /*
            getInt 대신 optInt 쓴 이유
             http://developeryou.blogspot.kr/2015/09/getjsonobject-null.html

             JSONArray랑 jSONObject 차이
             http://ddo-o.tistory.com/95
             */
            ArrData.add(response.optJSONArray("data").optJSONObject(i));
            ArrCTF_CODE.add(ArrData.get(i).optInt("CTF_CODE", 0));
            ArrCTF_TYPE.add(ArrData.get(i).optInt("CTF_TYPE", 0));
            ArrCTF_TYPE_NAME.add(ArrData.get(i).optString("CTF_TYPE_NAME", "No Value"));
            ArrCTF_NAME.add(ArrData.get(i).optString("CTF_NAME", "No Value"));
            ArrCTF_X.add(ArrData.get(i).optDouble("CTF_X", 0.0));
            ArrCTF_Y.add(ArrData.get(i).optDouble("CTF_Y", 0.0));
            ArrCTF_ADDR.add(ArrData.get(i).optString("CTF_ADDR", "No Value"));
            ArrCTF_TEL.add(ArrData.get(i).optString("CTF_TEL", "No Value"));

            // 아이템 추가
            adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_launcher), "", ArrCTF_NAME.get(i));

            /* 동적으로 텍스트 뷰 추가
            TextView dynamicTextView = new TextView(this);
            dynamicTextView.setId(DYNAMIC_VIEW_ID + i);
            dynamicTextView.setText(dynamicTextView.getId() + " " + ArrCTF_NAME.get(i));
            dynamicLayout.addView(dynamicTextView, new android.support.v7.app.ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
            */

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
