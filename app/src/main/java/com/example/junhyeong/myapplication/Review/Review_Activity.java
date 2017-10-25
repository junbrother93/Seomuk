package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.Adapter.ListViewAdapter2;
import com.example.junhyeong.myapplication.Data.Store4;
import com.example.junhyeong.myapplication.Popup.PopupActivity_Login;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.widget.IndexableListView2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeonjin on 2017-09-28.
 */

public class Review_Activity extends Activity {
    private ImageView ReviewBtn2;
    private Intent intent,Review_Write, Popup_login;
    private int store_id, unlogin_value;
    private String classify, image;
    private ArrayList<Store4> arrayList;
    private IndexableListView2 listview;
    private int total;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        intent = getIntent();
        unlogin_value = intent.getIntExtra("mypage",0);
        store_id = intent.getIntExtra("store_id", 0);
        classify = intent.getStringExtra("classify");
        image = intent.getStringExtra("store_grade");


        ReviewBtn2 = (ImageView)findViewById(R.id.ReviewBtn2);
        Review_Write = new Intent(this,Review_write_Activity.class);
        Popup_login = new Intent(this, PopupActivity_Login.class);
        Review_Write.putExtra("store_id", store_id);
        Review_Write.putExtra("classify", classify);

        ReviewBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(unlogin_value==1)
                {
                    startActivity(Popup_login);
                }
                else
                startActivity(Review_Write);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


        JsonObjectRequest reviewStoreRequest = new JsonObjectRequest(Request.Method.GET, "http://13.124.127.124:3000/review/store", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("reviewResponse", ""+response);

                arrayList = new ArrayList<Store4>();

                final ArrayList<JSONObject> ArrData = new ArrayList<JSONObject>();
                ArrayList<String> ArrTitle= new ArrayList<String>();
                ArrayList<String> ArrText= new ArrayList<String>();
                ArrayList<String> ArrImage= new ArrayList<String>();
                ArrayList<String> ArrCreated= new ArrayList<String>();
                ArrayList<String> ArrStoreName= new ArrayList<String>();

                // 리스트뷰랑 어댑터..
                listview = (IndexableListView2) findViewById(R.id.listview_review);
                ListViewAdapter2 adapter = new ListViewAdapter2();
                listview.setAdapter(adapter);
                listview.setFastScrollEnabled(true);

                total = response.optInt("total", 0);

                for (int i = 0; i <= total - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                {
                    ArrData.add(response.optJSONArray("data").optJSONObject(i));
                    ArrTitle.add(ArrData.get(i).optString("title", "No Value"));
                    ArrText.add(ArrData.get(i).optString("text", "No Value"));
                    ArrImage.add(ArrData.get(i).optString("image", "No Value"));
                    ArrCreated.add(ArrData.get(i).optString("created", "No Value"));
                    ArrStoreName.add(ArrData.get(i).optString("name", "No Value"));

                    Store4 s = new Store4();

                    s.setArrData(response.optJSONArray("data").optJSONObject(i));
                    s.setTitle(ArrData.get(i).optString("title", "No Value"));
                    s.setText(ArrData.get(i).optString("text", "No Value"));
                    s.setImage(ArrData.get(i).optString("image", "No Value"));
                    s.setCreated(ArrData.get(i).optString("created", "No Value"));
                    s.setStoreName(ArrData.get(i).optString("name", "No Value"));

                    arrayList.add(s);
                }
                for (int i = 0; i <= total - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                {
                    adapter.addItem(ContextCompat.getDrawable(Review_Activity.this,R.drawable.b6), arrayList.get(i).getStoreName());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("reviewError", ""+error);
            }

        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                params.put("store_id", Integer.toString(store_id));
                params.put("classify", classify);

                Log.d("storeReviewParams", ""+params);
                return params;
            }
        };
        requestQueue.add(reviewStoreRequest);
    }
}
