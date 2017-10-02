package com.example.junhyeong.myapplication.Select;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.Adapter.ListViewAdapter;
import com.example.junhyeong.myapplication.Data.Review;
import com.example.junhyeong.myapplication.GlobalApplication.GlobalApplication;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.Review.Review_watch_Activity;
import com.example.junhyeong.myapplication.widget.IndexableListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeonjin on 2017-09-26.
 */

public class Select_MyPage_Activity extends Activity {

    private IndexableListView listview;
    private ArrayList<Review> ReviewArrayList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest getReviewRequest = new JsonArrayRequest(Request.Method.GET, "http://13.124.127.124:3000/review/food", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("response : ", "response : " + response);
                ReviewArrayList = new ArrayList<Review>();
                // 리스트 생성
                final ArrayList<JSONObject> ArrReviewData = new ArrayList<JSONObject>();
                final ArrayList<String> ArrTitle = new ArrayList<String>();
                final ArrayList<String> ArrBody = new ArrayList<String>();
                final ArrayList<Integer> ArrScore = new ArrayList<Integer>();
                final ArrayList<Integer> ArrIndex = new ArrayList<Integer>();
                final ArrayList<Integer> ArrUser_id = new ArrayList<Integer>();

                // 리스트뷰랑 어댑터..
                listview = (IndexableListView) findViewById(R.id.listview2);
                ListViewAdapter adapter = new ListViewAdapter();
                listview.setAdapter(adapter);
                listview.setFastScrollEnabled(true);


                // 스크롤 뷰 안에 있는 리스트 뷰 스크롤 되게설정
                final ScrollView scrollview = (ScrollView) findViewById(R.id.scrollview);
                listview.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        scrollview.requestDisallowInterceptTouchEvent(true);
                        return false;
                    }
                });

                //int total = response.optInt("total", 0);    // 총 갯수
                int total = 2;
                for (int i = 0; i <= total - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                {
                     /*
                     getInt 대신 optInt 쓴 이유
                     http://developeryou.blogspot.kr/2015/09/getjsonobject-null.html

                     JSONArray랑 jSONObject 차이
                     http://ddo-o.tistory.com/95
                     */


                    // 아이템 불러와..
                    ArrReviewData.add(response.optJSONObject(i));
                    ArrTitle.add(ArrReviewData.get(i).optString("title", "No Value"));
                    ArrBody.add(ArrReviewData.get(i).optString("body", "No Value"));
                    ArrScore.add(ArrReviewData.get(i).optInt("score", 0));
                    ArrIndex.add(ArrReviewData.get(i).optInt("index", 0));
                    ArrUser_id.add(ArrReviewData.get(i).optInt("user_id", 0));

                    // 쓸지 안쓸지 결정
                    /*
                    ArrCTF_TYPE.add(ArrData.get(i).optInt("CTF_TYPE", 0));
                    ArrCTF_TYPE_NAME.add(ArrData.get(i).optString("CTF_TYPE_NAME", "No Value"));
                    ArrCTF_ADDR.add(ArrData.get(i).optString("CTF_ADDR", "No Value"));
                    */

                    // 객체 추가
                    Review s = new Review();
                    s.setArrReviewData(response.optJSONObject(i));
                    s.setTitle(ArrReviewData.get(i).optString("title", "null"));
                    s.setBody(ArrReviewData.get(i).optString("body", "null"));
                    s.setScore(ArrReviewData.get(i).optInt("score", 0));
                    s.setIndex(ArrReviewData.get(i).optInt("index", 0));
                    s.setUser_id(ArrReviewData.get(i).optInt("user_id", 0));
                    ReviewArrayList.add(s);
                }
                // 정렬
                Collections.sort(ReviewArrayList);
                // 정렬 한 것 어댑터에 추가
                if (total == 0) {
                    adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.warn), "정보가 존재하지 않습니다.");
                } else {
                    for (int i = 0; i <= total - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                    {
                        adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.mipmap.ic_launcher), ReviewArrayList.get(i).getTitle());
                    }

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), Review_watch_Activity.class);

                            Toast.makeText(Select_MyPage_Activity.this, "" + id, Toast.LENGTH_LONG).show();
                            intent.putExtra("review_title", ReviewArrayList.get((int) id).getTitle());
                            intent.putExtra("review_body", ReviewArrayList.get((int) id).getBody());
                            intent.putExtra("review_score", ReviewArrayList.get((int) id).getScore());
                            intent.putExtra("review_index", ReviewArrayList.get((int) id).getIndex());
                            intent.putExtra("review_user_id", ReviewArrayList.get((int) id).getUser_id());

                            startActivity(intent);
                        }
                    });
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error : ", "Error : " + error);
            }

        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                GlobalApplication GUserID = (GlobalApplication) getApplication();
                params.put("user_id", String.valueOf(GUserID.getGlobalUserID()));
                return params;
            }
        };
        requestQueue.add(getReviewRequest);
    }
}
