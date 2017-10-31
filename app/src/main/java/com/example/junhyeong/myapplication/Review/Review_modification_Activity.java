package com.example.junhyeong.myapplication.Review;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.GlobalApplication.GlobalApplication;
import com.example.junhyeong.myapplication.Google.MapsActivity;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.Select.Select_MyPage_Activity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeonjin on 2017-09-28.
 */

public class Review_modification_Activity extends Activity {

    EditText ReviewTitle, ReviewBody;
    ImageView btnModification, btnDelete, btnClose, btnMove;
    Intent intent, ActMypage, MapsActivity;
    String title, body, classify, created, image;
    int index, user_id, width, height, score, store_id;
    RatingBar rating;
    TextView Value;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_review_modification);
        intent = getIntent();
        ActMypage = new Intent(this, Select_MyPage_Activity.class);
        MapsActivity = new Intent(this, MapsActivity.class);
        title = intent.getStringExtra("review_title");
        body = intent.getStringExtra("review_body");
        created = intent.getStringExtra("review_created");
        classify = intent.getStringExtra("review_classify");
        image = intent.getStringExtra("review_image");
        score = intent.getIntExtra("review_score", 0);
        index = intent.getIntExtra("review_index", 0);
        user_id = intent.getIntExtra("review_user_id", 0);
        store_id = intent.getIntExtra("review_store_id", 0);

        Log.d("title", title);
        Log.d("body", body);
        Log.d("score", "" + score);
        Log.d("index", "" + index);
        Log.d("user_id", "" + user_id);
        Log.d("store_id", "" + store_id);
        Log.d("classify", classify);
        Log.d("image", image);

        ReviewTitle = (EditText) findViewById(R.id.ReviewTitle);
        ReviewBody = (EditText) findViewById(R.id.ReviewBody);
        btnModification = (ImageView) findViewById(R.id.btnModification);
        btnDelete = (ImageView) findViewById(R.id.btnDelete);
        btnClose = (ImageView) findViewById(R.id.btnClose);
        rating = (RatingBar) findViewById(R.id.ratingBar2);
        Value = (TextView) findViewById(R.id.Value2);
        btnMove = (ImageView)findViewById(R.id.move);


        // 받아온 데이터로 내용 수정
        if (score == 1)
            Value.setText("1.0 ");
        else if (score == 2)
            Value.setText("2.0 ");
        else if (score == 3)
            Value.setText("3.0 ");
        else if (score == 4)
            Value.setText("4.0 ");
        else
            Value.setText("5.0 ");
        ReviewTitle.setText(title);
        ReviewBody.setText(body);
        rating.setRating((float)score);


        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // 강제로 1넣기
                if (ratingBar.getRating() <= 1.0) {
                    ratingBar.setRating(1);
                    Value.setText("1.0 ");
                    score = 1;
                } else if (ratingBar.getRating() > 1.0 && ratingBar.getRating() <= 2.0) {
                    ratingBar.setRating(2);
                    Value.setText("2.0 ");
                    score = 2;
                } else if (ratingBar.getRating() > 2.0 && ratingBar.getRating() <= 3.0) {
                    ratingBar.setRating(3);
                    Value.setText("3.0 ");
                    score = 3;
                } else if (ratingBar.getRating() > 3.0 && ratingBar.getRating() <= 4.0) {
                    ratingBar.setRating(4);
                    Value.setText("4.0 ");
                    score = 4;
                } else if (ratingBar.getRating() > 4.0 && ratingBar.getRating() <= 5.0) {
                    ratingBar.setRating(5);
                    Value.setText("5.0 ");
                    score = 5;
                }
            }
        });
        ReviewTitle.setText(title);
        ReviewBody.setText(body);


        // 수정 클릭하면 수정 되도록 (수정 버튼 누르면 수정버튼은 확인 버튼으로, 닫기 버튼은 취소 버튼으로)
        // 닫기 클릭하면 닫도록

        btnModification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest reviewDeleteRequest = new StringRequest(Request.Method.DELETE, "http://13.124.127.124:3000/review", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reviewDeleteResponse", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("reviewDeleteResponse", "" + error);
                    }

                }) {
                    @Override
                    public Map getHeaders() throws AuthFailureError {
                        Map params = new HashMap();
                        params.put("user_id", Integer.toString(user_id));
                        params.put("review_id", Integer.toString(index));
                        return params;
                    }
                };
                requestQueue.add(reviewDeleteRequest);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                StringRequest reviewReWriteRequest = new StringRequest(Request.Method.POST, "http://13.124.127.124:3000/review", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reviewReWriteResponse", "" + response);
                        Toast.makeText(Review_modification_Activity.this, "리뷰 수정 완료", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("reviewReWriteError", error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        GlobalApplication GUserID = (GlobalApplication) getApplication();
                        Map<String, String> params = new HashMap<>();
                        params.put("title", ReviewTitle.getText().toString());
                        params.put("text", ReviewBody.getText().toString());
                        params.put("classify", classify);
                        params.put("user_id", String.valueOf(GUserID.getGlobalUserID()));
                        params.put("store_id", String.valueOf(store_id));
                        params.put("score", String.valueOf(score));
                        params.put("image", image);
                        Log.e("body", "body" + params);

                        return params;
                    }
                };
                requestQueue.add(reviewReWriteRequest);
                redirectSelect_MyPage_Activity();
            }
        });

        // 삭제
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest reviewDeleteRequest = new StringRequest(Request.Method.DELETE, "http://13.124.127.124:3000/review", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("reviewDeleteResponse", response.toString());
                        Toast.makeText(Review_modification_Activity.this, "리뷰 삭제 완료", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("reviewDeleteResponse", "" + error);
                    }

                }) {
                    @Override
                    public Map getHeaders() throws AuthFailureError {
                        Map params = new HashMap();
                        params.put("user_id", Integer.toString(user_id));
                        params.put("review_id", Integer.toString(index));
                        return params;
                    }
                };
                requestQueue.add(reviewDeleteRequest);
                redirectSelect_MyPage_Activity();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectSelect_MyPage_Activity();
            }
        });
        //여기 상세페이지로 이동하게
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                if (classify.equals("safe")) {
                    classify = "food";
                }
                String url = "http://13.124.127.124:3000/" + classify + "/" + store_id;
                JsonObjectRequest reviewStoreRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("reviewResponse", "" + response);
                        if (classify.equals("food"))
                        {
                            classify = "safe";
                            MapsActivity.putExtra("store_id", response.optJSONArray("data").optJSONObject(0).optInt("CTF_CODE", 0));
                            MapsActivity.putExtra("store_name", response.optJSONArray("data").optJSONObject(0).optString("CTF_NAME", "No Value"));
                            MapsActivity.putExtra("store_grade", response.optJSONArray("data").optJSONObject(0).optString("CTF_TYPE_NAME", "No Value"));
                            MapsActivity.putExtra("store_call", response.optJSONArray("data").optJSONObject(0).optString("CTF_TEL", "No Value"));
                            MapsActivity.putExtra("CRTFC_CLASS", response.optJSONArray("data").optJSONObject(0).optString("CRTFC_CLASS", "No Value"));
                            MapsActivity.putExtra("X", response.optJSONArray("data").optJSONObject(0).optDouble("CTF_X", 37.5652894));
                            MapsActivity.putExtra("Y", response.optJSONArray("data").optJSONObject(0).optDouble("CTF_Y", 126.8494668));
                            MapsActivity.putExtra("classify", classify);
                            MapsActivity.putExtra("mypage", 0);
                            MapsActivity.putExtra("check", 1);
                        }

                        else if(classify.equals("auth"))
                        {
                            MapsActivity.putExtra("store_id", response.optJSONArray("data").optJSONObject(0).optInt("CRTFC_UPSO_MGT_SNO", 0));
                            MapsActivity.putExtra("store_name", response.optJSONArray("data").optJSONObject(0).optString("UPSO_NM", "No Value"));
                            MapsActivity.putExtra("store_grade", response.optJSONArray("data").optJSONObject(0).optString("CRTFC_GBN_NM", "No Value"));
                            MapsActivity.putExtra("store_call", response.optJSONArray("data").optJSONObject(0).optString("TEL_NO", "No Value"));
                            MapsActivity.putExtra("CRTFC_CLASS", response.optJSONArray("data").optJSONObject(0).optString("CRTFC_CLASS", "No Value"));
                            MapsActivity.putExtra("X", response.optJSONArray("data").optJSONObject(0).optDouble("Y_DNTS", 37.5652894));
                            MapsActivity.putExtra("Y", response.optJSONArray("data").optJSONObject(0).optDouble("X_CNTS", 126.8494668));
                            MapsActivity.putExtra("classify", classify);
                            MapsActivity.putExtra("mypage", 0);
                            MapsActivity.putExtra("check", 1);
                        }

                        startActivity(MapsActivity);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("reviewError", "" + error);
                    }
                });
                requestQueue.add(reviewStoreRequest);
            }
        });
    }

    protected void redirectSelect_MyPage_Activity() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Intent intent = new Intent(this, Select_MyPage_Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        redirectSelect_MyPage_Activity();
    }
}
