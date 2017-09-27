package com.example.junhyeong.myapplication.Popup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.Main.MainActivity;
import com.example.junhyeong.myapplication.R;

import java.util.HashMap;
import java.util.Map;


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
                menu = "한식&분식".toString();
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
<<<<<<< HEAD
            case R.id.close:
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postStringRequest = new StringRequest(Request.Method.POST, "http://13.124.127.124:3000/review", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> body = new HashMap<>();
                        body.put("text", "이 음식점의 밑반찬은 굉장히 맛있습니다.");
                        body.put("classify", "안심");
                        body.put("user_id", "1");
                        body.put("store_id", "1760");

                        return body;
                    }
                };
                requestQueue.add(postStringRequest);
                break;

=======
>>>>>>> c6af3ddaff9cb6006de03862d68ecd27c66d031f
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

}
