package com.example.junhyeong.myapplication.Select;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.Adapter.ListViewAdapter2;
import com.example.junhyeong.myapplication.Data.Review;
import com.example.junhyeong.myapplication.Data.Store;
import com.example.junhyeong.myapplication.Data.Store2;
import com.example.junhyeong.myapplication.GlobalApplication.GlobalApplication;
import com.example.junhyeong.myapplication.Popup.PopupActivity_Logout;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.Review.Review_watch_Activity;
import com.example.junhyeong.myapplication.widget.IndexableListView2;
import com.kakao.auth.network.response.JSONObjectResponse;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeonjin on 2017-09-26.
 */

public class Select_MyPage_Activity extends Activity {

    private IndexableListView2 listview;
    private ArrayList<Review> ReviewArrayList;
    private ArrayList<Store> StoreArrayList;
    private ArrayList<Store2> Store2ArrayList;
    private ImageView warn;
    private ImageView favor,review,logout;
    private Intent PopLogout;
    int num_favor,num_review;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        num_favor=0;
        num_review=0;
        warn = (ImageView) findViewById(R.id.warn2);
        favor = (ImageView)findViewById(R.id.Favorite);
        review = (ImageView)findViewById(R.id.Riview);
        logout = (ImageView)findViewById(R.id.logout);
        PopLogout = new Intent(this, PopupActivity_Logout.class);;
        favor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    favor.setImageResource(R.drawable.star_click);
                    review.setImageResource(R.drawable.mypage_review);

            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    favor.setImageResource(R.drawable.star);
                    review.setImageResource(R.drawable.mypage_review_click);
                    num_favor=0;

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(PopLogout);

            }
        });

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 즐겨찾기

        JsonObjectRequest checkBookmarkRequest = new JsonObjectRequest(Request.Method.POST, "http://13.124.127.124:3000/user/bookmark", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("checkBookmark : ", "checkBookmarkResponse : " + response);
                int total = 1;
                int store_id = 8888;
                // response.optInt("total", 0);
                // for() 문을 이용해 total 값만큼 수행
                for(int i = 0; i < total; i ++) {
                    JsonObjectRequest addStoreInfoRequest = new JsonObjectRequest(Request.Method.GET, "http://13.124.127.124:3000/auth/" + store_id, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("addStoreInfoResponse : ", "addStoreInfoResponse : " + response);
                            // if(store_id <= 10000) {
                            // 안심먹거리 데이터 추가
                            // }
                            // else() {
                            // 인증업소 데이터 추가
                            // }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("addStoreInfoError :", "addStoreInfoError :" + error);
                        }

                    }) {
                        @Override
                        public Map getHeaders() throws AuthFailureError {
                            Map params = new HashMap();
                            GlobalApplication GUserID = (GlobalApplication) getApplication();
                            params.put("user_id", Integer.toString(GUserID.getGlobalUserID()));
                            return params;
                        }
                    };
                    requestQueue.add(addStoreInfoRequest);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("checkBookmarkError :", "checkBookmarkError :" + error);
            }

        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                GlobalApplication GUserID = (GlobalApplication) getApplication();
                params.put("user_id", Integer.toString(GUserID.getGlobalUserID()));
                return params;
            }
        };
        requestQueue.add(checkBookmarkRequest);

        // 리뷰

        JsonObjectRequest getReviewRequest = new JsonObjectRequest(Request.Method.GET, "http://13.124.127.124:3000/review/food", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Review : ", "Review : " + response);
                ReviewArrayList = new ArrayList<Review>();
                // 리스트 생성
                final ArrayList<JSONObject> ArrReviewData = new ArrayList<JSONObject>();
                final ArrayList<String> ArrTitle = new ArrayList<String>();
                final ArrayList<String> ArrBody = new ArrayList<String>();
                final ArrayList<Double> ArrScore = new ArrayList<Double>();
                final ArrayList<Integer> ArrIndex = new ArrayList<Integer>();
                final ArrayList<Integer> ArrUser_id = new ArrayList<Integer>();

                // 리스트뷰랑 어댑터..
                listview = (IndexableListView2) findViewById(R.id.listview2);
                ListViewAdapter2 adapter = new ListViewAdapter2();
                listview.setAdapter(adapter);
                listview.setFastScrollEnabled(true);



                int total = response.optInt("total", 0);    // 총 갯수

                for (int i = 0; i <= total - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                {

                    ArrReviewData.add(response.optJSONArray("data").optJSONObject(i));
                    ArrTitle.add(ArrReviewData.get(i).optString("title", "No Value"));
                    //ArrBody.add(ArrReviewData.get(i).optString("body", "No Value"));
                    ArrBody.add(ArrReviewData.get(i).optString("text", "No Value"));
                    ArrScore.add(ArrReviewData.get(i).optDouble("score", 0));
                    ArrIndex.add(ArrReviewData.get(i).optInt("index", 0));
                    ArrUser_id.add(ArrReviewData.get(i).optInt("user_id", 0));

                    Review s = new Review();

                    s.setArrReviewData(response.optJSONArray("data").optJSONObject(i));
                    s.setTitle(ArrReviewData.get(i).optString("title", "No value"));
                    //s.setBody(ArrReviewData.get(i).optString("body", "No value"));
                    s.setBody(ArrReviewData.get(i).optString("text", "No value"));
                    s.setScore(ArrReviewData.get(i).optDouble("score", 0.0));
                    s.setIndex(ArrReviewData.get(i).optInt("index", 0));
                    s.setUser_id(ArrReviewData.get(i).optInt("user_id", 0));
                    ReviewArrayList.add(s);
                }
                // 정렬
                Collections.sort(ReviewArrayList);
                // 정렬 한 것 어댑터에 추가
                if (total == 0) {
                    warn.setVisibility(View.VISIBLE);
                    listview.setVisibility(View.GONE);
                } else {
                    warn.setVisibility(View.INVISIBLE);
                    listview.setVisibility(View.VISIBLE);
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
