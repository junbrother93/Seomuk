package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.Adapter.ListViewAdapter4;
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
    private ImageView ReviewBtn2, Warn;
    private Intent intent, Review_Write, Popup_login;
    private int store_id, unlogin_value;
    private String classify, image;
    private int sum;
    private float convert;
    private TextView Avg, Menu;
    private ArrayList<Store4> arrayList;
    private IndexableListView2 listview;
    private int total;
    private RatingBar ratingBar3;
    private Typeface BMJUA;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        intent = getIntent();
        unlogin_value = intent.getIntExtra("mypage",0);
        unlogin_value = intent.getIntExtra("mypage", 0);
        store_id = intent.getIntExtra("store_id", 0);
        classify = intent.getStringExtra("classify");
        image = intent.getStringExtra("store_grade");

        BMJUA = Typeface.createFromAsset(this.getAssets(), "fonts/BMJUA_ttf.ttf");

        sum=0;
        convert=0;
        ReviewBtn2 = (ImageView)findViewById(R.id.ReviewBtn2);
        Warn = (ImageView)findViewById(R.id.warn_review);
        Avg = (TextView)findViewById(R.id.Average);
        ratingBar3 = (RatingBar)findViewById(R.id.ratingBar3);
        Menu = (TextView)findViewById(R.id.Txt_Menu);
        Menu.setTypeface(BMJUA);


        Review_Write = new Intent(this,Review_write_Activity.class);
        Popup_login = new Intent(this, PopupActivity_Login.class);

        Review_Write.putExtra("store_id", store_id);
        Review_Write.putExtra("classify", classify);
        Review_Write.putExtra("store_grade", image);

        ReviewBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unlogin_value == 1) {
                    startActivity(Popup_login);
                } else
                    startActivity(Review_Write);
                finish();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest reviewStoreRequest = new JsonObjectRequest(Request.Method.GET, "http://13.124.127.124:3000/review/store", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("reviewResponse", "" + response);

                arrayList = new ArrayList<Store4>();

                final ArrayList<JSONObject> ArrData = new ArrayList<JSONObject>();
                ArrayList<String> ArrTitle = new ArrayList<String>();
                ArrayList<String> ArrText = new ArrayList<String>();
                ArrayList<String> ArrImage = new ArrayList<String>();
                ArrayList<String> ArrCreated = new ArrayList<String>();
                ArrayList<Integer> ArrScore = new ArrayList<Integer>();
                ArrayList<Integer> ArrUser_Id = new ArrayList<Integer>();
                ArrayList<String> ArrStoreName = new ArrayList<String>();

                // 리스트뷰랑 어댑터..
                listview = (IndexableListView2) findViewById(R.id.listview_review);
                ListViewAdapter4 adapter = new ListViewAdapter4();
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
                    ArrScore.add(ArrData.get(i).optInt("score", 0));
                    ArrUser_Id.add(ArrData.get(i).optInt("UserId", 0));
                    ArrStoreName.add(ArrData.get(i).optString("storename", "No Value"));
                    Store4 s = new Store4();

                    s.setArrData(response.optJSONArray("data").optJSONObject(i));
                    s.setTitle(ArrData.get(i).optString("title", "No Value"));
                    s.setText(ArrData.get(i).optString("text", "No Value"));
                    s.setImage(ArrData.get(i).optString("image", "No Value"));
                    s.setCreated(ArrData.get(i).optString("created", "No Value"));
                    s.setScore(ArrData.get(i).optInt("score", 0));
                    s.setUserId(ArrData.get(i).optInt("UserId", 0));
                    s.setStoreName(ArrData.get(i).optString("storename", "No Value"));

                    arrayList.add(s);

                }
                if(total!=0) {
                    for (int i = 0; i <= total - 1; i++) {
                        sum += arrayList.get(i).getScore();
                    }
                    convert = (((float) sum) / ((float) total));
                    ratingBar3.setRating(convert);
                    Avg.setText(String.format("%.1f", convert));
                }
                else
                    Avg.setText("0.0 ");
                if (total == 0) {
                    Warn.setVisibility(View.VISIBLE);
                    listview.setVisibility(View.GONE);
                } else {
                    Warn.setVisibility(View.INVISIBLE);
                    listview.setVisibility(View.VISIBLE);
                    for (int i = 0; i <= total - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                    {

                        if (arrayList.get(i).getImage().toString().equals("저염실천음식점".toString()) || arrayList.get(i).getImage().toString().equals("저염참여음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Review_Activity.this, R.drawable.b7),"제목 : "+arrayList.get(i).getTitle(),"내용 : "+arrayList.get(i).getText(), "최종 수정일 : " + arrayList.get(i).getCreated().substring(0, 10));
                        else if (arrayList.get(i).getImage().toString().equals("먹을만큼적당히".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Review_Activity.this, R.drawable.b2),"제목 : "+arrayList.get(i).getTitle(),"내용 : "+arrayList.get(i).getText(), "최종 수정일 : " + arrayList.get(i).getCreated().substring(0, 10));
                        else if (arrayList.get(i).getImage().toString().equals("건강음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Review_Activity.this, R.drawable.b1),"제목 : "+arrayList.get(i).getTitle(),"내용 : "+arrayList.get(i).getText(), "최종 수정일 : " + arrayList.get(i).getCreated().substring(0, 10));
                        else if (arrayList.get(i).getImage().toString().equals("위생등급제".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Review_Activity.this, R.drawable.b5),"제목 : "+arrayList.get(i).getTitle(),"내용 : "+arrayList.get(i).getText(), "최종 수정일 : " + arrayList.get(i).getCreated().substring(0, 10));
                        else if (arrayList.get(i).getImage().toString().equals("자랑스러운 한국음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Review_Activity.this, R.drawable.b6),"제목 : "+arrayList.get(i).getTitle(),"내용 : "+arrayList.get(i).getText(), "최종 수정일 : " + arrayList.get(i).getCreated().substring(0, 10));
                        else if (arrayList.get(i).getImage().toString().equals("원산지표시 우수음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Review_Activity.this, R.drawable.b4),"제목 : "+arrayList.get(i).getTitle(),"내용 : "+arrayList.get(i).getText(), "최종 수정일 : " + arrayList.get(i).getCreated().substring(0, 10));
                        else if (arrayList.get(i).getImage().toString().equals("채식메뉴음식점".toString()) || arrayList.get(i).getImage().toString().equals("채식전문음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Review_Activity.this, R.drawable.b8),"제목 : "+arrayList.get(i).getTitle(),"내용 : "+arrayList.get(i).getText(), "최종 수정일 : " + arrayList.get(i).getCreated().substring(0, 10));
                       else
                            adapter.addItem(ContextCompat.getDrawable(Review_Activity.this, R.drawable.b3),"제목 : "+arrayList.get(i).getTitle(),"내용 : "+arrayList.get(i).getText(), "최종 수정일 : " + arrayList.get(i).getCreated().substring(0, 10));
                    }

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), Review_view_Activity.class);
                            intent.putExtra("review_title", arrayList.get((int) id).getTitle());
                            intent.putExtra("review_body", arrayList.get((int) id).getText());
                            intent.putExtra("review_created", arrayList.get((int) id).getCreated());
                            intent.putExtra("review_score", arrayList.get((int) id).getScore());
                            intent.putExtra("review_UserId", arrayList.get((int) id).getUserId());
                            startActivity(intent);
                        }
                    });
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("reviewError", "" + error);
            }

        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                params.put("store_id", Integer.toString(store_id));
                params.put("classify", classify);

                Log.d("storeReviewParams", "" + params);
                return params;
            }
        };
        requestQueue.add(reviewStoreRequest);
    }
}
