package com.example.junhyeong.myapplication.Select;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.Adapter.ListViewAdapter2;
import com.example.junhyeong.myapplication.Adapter.ListViewAdapter3;
import com.example.junhyeong.myapplication.Data.Review;
import com.example.junhyeong.myapplication.Data.Store3;
import com.example.junhyeong.myapplication.GlobalApplication.GlobalApplication;
import com.example.junhyeong.myapplication.Google.MapsActivity;
import com.example.junhyeong.myapplication.Main.MainActivity;
import com.example.junhyeong.myapplication.Popup.PopupActivity_Logout;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.Review.Review_modification_Activity;
import com.example.junhyeong.myapplication.widget.IndexableListView2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeonjin on 2017-09-26.
 */

public class Select_MyPage_Activity extends Activity implements View.OnClickListener {
    private IndexableListView2 listview;
    private ArrayList<Review> ReviewArrayList;
    private ArrayList<Store3> StoreArrayList;
    private ImageView warn,warn_favor,warn_review;
    private ImageView favor, review, logout;
    private Intent PopLogout, intent, MapsActivity;
    int num_favor, num_review,total_favor,total_review,store_id;
    private String classify;
    private ViewPager mPager;
    private TextView Mypage_name;
    private Typeface BMJUA;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        num_favor = 0;
        num_review = 0;
        warn = (ImageView) findViewById(R.id.warn2);


        BMJUA = Typeface.createFromAsset(this.getAssets(), "fonts/BMJUA_ttf.ttf");
        Mypage_name = (TextView)findViewById(R.id.Mypage_Name);
        logout = (ImageView) findViewById(R.id.logout);

        PopLogout = new Intent(this, PopupActivity_Logout.class);
        intent = new Intent(this, MainActivity.class);
        MapsActivity = new Intent(this, MapsActivity.class);
        Mypage_name.setTypeface(BMJUA);

        classify = intent.getStringExtra("classify");

        setLayout();
        mPager = (ViewPager) findViewById(R.id.pager_mypage);
        mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PopLogout);
            }
        });

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 즐겨찾기
        JsonObjectRequest checkBookmarkRequest = new JsonObjectRequest(Request.Method.GET, "http://13.124.127.124:3000/user/like", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("checkBookmark", response.toString());
                total_favor = response.optInt("total",0);

                StoreArrayList = new ArrayList<Store3>();

                final ArrayList<JSONObject> ArrData = new ArrayList<JSONObject>();
                ArrayList<String> ArrName = new ArrayList<String>();
                ArrayList<String> ArrImage = new ArrayList<String>();
                final ArrayList<String> ArrClassify = new ArrayList<String>();
                ArrayList<Integer> ArrStoreId = new ArrayList<Integer>();

                // 리스트뷰랑 어댑터..
                listview = (IndexableListView2) findViewById(R.id.listview_favor);
                ListViewAdapter2 adapter = new ListViewAdapter2();
                listview.setAdapter(adapter);
                listview.setFastScrollEnabled(true);

                for (int i = 0; i <= total_favor - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                {
                    // 아이템 불러와..
                    ArrData.add(response.optJSONArray("data").optJSONObject(i));
                    ArrName.add(ArrData.get(i).optString("name", "No Value"));
                    ArrImage.add(ArrData.get(i).optString("image", "No Value"));
                    ArrClassify.add(ArrData.get(i).optString("classify", "No Value"));
                    ArrStoreId.add(ArrData.get(i).optInt("StoreId", 0));

                    // 객체 추가
                    Store3 s = new Store3();
                    s.setArrData(response.optJSONArray("data").optJSONObject(i));
                    s.setStoreId(ArrData.get(i).optInt("StoreId", 0));
                    s.setName(ArrData.get(i).optString("name", "No Value"));
                    s.setImage(ArrData.get(i).optString("image", "No Value"));
                    s.setClassify(ArrData.get(i).optString("classify", "No Value"));
                    StoreArrayList.add(s);
                }

               //일단 건들지 말기
               if (total_favor == 0) {
                    listview.setVisibility(View.GONE);
                } else {
                    warn.setVisibility(View.GONE);
                    listview.setVisibility(View.VISIBLE);
                    for (int i = 0; i <= total_favor - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                    {

                         if (StoreArrayList.get(i).getImage().toString().equals("저염실천음식점".toString()) || StoreArrayList.get(i).getImage().toString().equals("저염참여음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b7), StoreArrayList.get(i).getName(), ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.bow));
                        else if (StoreArrayList.get(i).getImage().toString().equals("먹을만큼적당히".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b2), StoreArrayList.get(i).getName(), ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.bow));
                        else if (StoreArrayList.get(i).getImage().toString().equals("건강음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b1), StoreArrayList.get(i).getName(), ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.bow));
                        else if (StoreArrayList.get(i).getImage().toString().equals("위생등급제".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b5), StoreArrayList.get(i).getName(), ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.bow));
                        else if (StoreArrayList.get(i).getImage().toString().equals("자랑스러운 한국음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b6), StoreArrayList.get(i).getName(), ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.bow));
                        else if (StoreArrayList.get(i).getImage().toString().equals("원산지표시 우수음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b4), StoreArrayList.get(i).getName(), ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.bow));
                        else if (StoreArrayList.get(i).getImage().toString().equals("채식메뉴음식점".toString()) || StoreArrayList.get(i).getImage().toString().equals("채식전문음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b8), StoreArrayList.get(i).getName(), ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.bow));
                        else if (StoreArrayList.get(i).getImage().toString().equals("자랑스러운 한국음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b6), StoreArrayList.get(i).getName(), ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.bow));
                        else
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b3), StoreArrayList.get(i).getName(), ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.bow));

                    }

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                            classify = StoreArrayList.get((int) id).getClassify();
                            String storeid = Integer.toString(StoreArrayList.get((int) id).getStoreId());
                            if (classify.equals("safe")) {
                                classify = "food";
                            }
                            String url = "http://13.124.127.124:3000/" + classify + "/" + storeid;


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
           }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("checkBookmarkError", error.toString());
            }

        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                GlobalApplication GUserID = (GlobalApplication) getApplication();
                Map params = new HashMap();
                params.put("user_id", Integer.toString(GUserID.getGlobalUserID()));
                return params;
            }
        };
        requestQueue.add(checkBookmarkRequest);

        JsonObjectRequest getReviewRequest = new JsonObjectRequest(Request.Method.GET, "http://13.124.127.124:3000/review/user", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Review : ", response.toString());
                ReviewArrayList = new ArrayList<Review>();
                // 리스트 생성
                final ArrayList<JSONObject> ArrReviewData = new ArrayList<JSONObject>();
                final ArrayList<String> ArrTitle = new ArrayList<String>();
                final ArrayList<String> ArrBody = new ArrayList<String>();
                final ArrayList<String> ArrStoreName = new ArrayList<String>();
                final ArrayList<String> ArrCreated = new ArrayList<String>();
                final ArrayList<String> ArrClassify = new ArrayList<String>();
                final ArrayList<String> ArrImage = new ArrayList<String>();
                final ArrayList<Integer> ArrScore = new ArrayList<Integer>();
                final ArrayList<Integer> ArrIndex = new ArrayList<Integer>();
                final ArrayList<Integer> ArrUser_id = new ArrayList<Integer>();
                final ArrayList<Integer> ArrStore_id = new ArrayList<Integer>();


                // 리스트뷰랑 어댑터..
                listview = (IndexableListView2) findViewById(R.id.listview_review);
                ListViewAdapter3 adapter = new ListViewAdapter3();
                listview.setAdapter(adapter);
                listview.setFastScrollEnabled(true);

                total_review= response.optInt("total", 0);    // 총 갯수

                for (int i = 0; i <= total_review - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                {

                    ArrReviewData.add(response.optJSONArray("data").optJSONObject(i));
                    ArrTitle.add(ArrReviewData.get(i).optString("title", "No Value"));
                    ArrBody.add(ArrReviewData.get(i).optString("text", "No Value"));
                    ArrStoreName.add(ArrReviewData.get(i).optString("storename", "No Value"));
                    ArrCreated.add(ArrReviewData.get(i).optString("created", "No Value"));
                    ArrClassify.add(ArrReviewData.get(i).optString("classify", "No Value"));
                    ArrImage.add(ArrReviewData.get(i).optString("image", "No Value"));
                    ArrScore.add(ArrReviewData.get(i).optInt("score", 0));
                    ArrIndex.add(ArrReviewData.get(i).optInt("id", 0));
                    ArrUser_id.add(ArrReviewData.get(i).optInt("UserId", 0));
                    ArrStore_id.add(ArrReviewData.get(i).optInt("StoreId", 0));

                    Review s = new Review();

                    s.setArrReviewData(response.optJSONArray("data").optJSONObject(i));
                    s.setTitle(ArrReviewData.get(i).optString("title", "No value"));
                    s.setBody(ArrReviewData.get(i).optString("text", "No value"));
                    s.setStore_name(ArrReviewData.get(i).optString("storename", "No value"));
                    s.setCreated(ArrReviewData.get(i).optString("created", "No Value"));
                    s.setClassify(ArrReviewData.get(i).optString("classify", "No Value"));
                    s.setImage(ArrReviewData.get(i).optString("image", "No Value"));
                    s.setScore(ArrReviewData.get(i).optInt("score", 0));
                    s.setReview_id(ArrReviewData.get(i).optInt("id", 0));
                    s.setUser_id(ArrReviewData.get(i).optInt("UserId", 0));
                    s.setStore_id(ArrReviewData.get(i).optInt("StoreId", 0));

                    ReviewArrayList.add(s);
                }
                // 정렬
                Collections.sort(ReviewArrayList);
                // 정렬 한 것 어댑터에 추가
                if (total_review== 0) {
                    listview.setVisibility(View.GONE);
                } else {
                    listview.setVisibility(View.VISIBLE);
                    for (int i = 0; i <= total_review- 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                    {
                        if (ReviewArrayList.get(i).getImage().toString().equals("저염실천음식점".toString()) || ReviewArrayList.get(i).getImage().toString().equals("저염참여음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b7), ReviewArrayList.get(i).getTitle(),"최종 수정일 : "+ReviewArrayList.get(i).getCreated().substring(0,10));
                        else if (ReviewArrayList.get(i).getImage().toString().equals("먹을만큼적당히".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b2), ReviewArrayList.get(i).getTitle(),"최종 수정일 : "+ReviewArrayList.get(i).getCreated().substring(0,10));
                        else if (ReviewArrayList.get(i).getImage().toString().equals("건강음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b1), ReviewArrayList.get(i).getTitle(),"최종 수정일 : "+ReviewArrayList.get(i).getCreated().substring(0,10));
                        else if (ReviewArrayList.get(i).getImage().toString().equals("위생등급제".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b5), ReviewArrayList.get(i).getTitle(),"최종 수정일 : "+ReviewArrayList.get(i).getCreated().substring(0,10));
                        else if (ReviewArrayList.get(i).getImage().toString().equals("자랑스러운 한국음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b6), ReviewArrayList.get(i).getTitle(),"최종 수정일 : "+ReviewArrayList.get(i).getCreated().substring(0,10));
                        else if (ReviewArrayList.get(i).getImage().toString().equals("원산지표시 우수음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b4), ReviewArrayList.get(i).getTitle(),"최종 수정일 : "+ReviewArrayList.get(i).getCreated().substring(0,10));
                        else if (ReviewArrayList.get(i).getImage().toString().equals("채식메뉴음식점".toString()) || ReviewArrayList.get(i).getImage().toString().equals("채식전문음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b8), ReviewArrayList.get(i).getTitle(),"최종 수정일 : "+ReviewArrayList.get(i).getCreated().substring(0,10));
                        else if (ReviewArrayList.get(i).getImage().toString().equals("자랑스러운 한국음식점".toString()))
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b6), ReviewArrayList.get(i).getTitle(),"최종 수정일 : "+ReviewArrayList.get(i).getCreated().substring(0,10));
                        else
                            adapter.addItem(ContextCompat.getDrawable(Select_MyPage_Activity.this, R.drawable.b3), ReviewArrayList.get(i).getTitle(),"최종 수정일 : "+ReviewArrayList.get(i).getCreated().substring(0,10));


                    }

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), Review_modification_Activity.class);
                            intent.putExtra("review_title", ReviewArrayList.get((int) id).getTitle());
                            intent.putExtra("review_body", ReviewArrayList.get((int) id).getBody());
                            intent.putExtra("review_created", ReviewArrayList.get((int) id).getCreated());
                            intent.putExtra("review_classify", ReviewArrayList.get((int) id).getClassify());
                            intent.putExtra("review_image", ReviewArrayList.get((int) id).getImage());
                            intent.putExtra("review_score", ReviewArrayList.get((int) id).getScore());
                            intent.putExtra("review_index", ReviewArrayList.get((int) id).getReview_id());
                            intent.putExtra("review_user_id", ReviewArrayList.get((int) id).getUser_id());
                            intent.putExtra("review_store_id", ReviewArrayList.get((int) id).getStore_id());

                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
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

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if(position==0)
                    {
                        Mypage_name.setText("즐겨찾기");
                        favor.setImageResource(R.drawable.star_click);
                        review.setImageResource(R.drawable.mypage_review);
                    }
                    else if(position==1)
                    {
                        Mypage_name.setText("리뷰");
                        favor.setImageResource(R.drawable.star);
                        review.setImageResource(R.drawable.mypage_review_click);
                    }
            }
            @Override
            public void onPageSelected(int position) {
                if(position==0)
                {
                    if(total_favor==0)
                        warn.setVisibility(View.VISIBLE);
                    else
                        warn.setVisibility(View.GONE);

                }
                else if(position==1)
                {
                    if(total_review==0)
                        warn.setVisibility(View.VISIBLE);
                    else
                        warn.setVisibility(View.GONE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Favorite:
               setCurrentInflateItem(0);
                break;
            case R.id.Review:
                setCurrentInflateItem(1);
                break;
        }
    }

    private void setCurrentInflateItem(int type) {
        if (type == 0) {
            mPager.setCurrentItem(0);
        } else if(type == 1)
            mPager.setCurrentItem(1);
    }

    private void setLayout() {

        favor = (ImageView) findViewById(R.id.Favorite);
        review = (ImageView) findViewById(R.id.Review);

        favor.setOnClickListener(this);
        review.setOnClickListener(this);
    }


    private View.OnClickListener mPagerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Favorite:
                    favor.setImageResource(R.drawable.star_click);
                    review.setImageResource(R.drawable.mypage_review);
                    mPager.setCurrentItem(0);
                    break;
                case R.id.Review:
                    favor.setImageResource(R.drawable.star);
                    review.setImageResource(R.drawable.mypage_review_click);
                    mPager.setCurrentItem(1);
                    break;
            }
        }
    };

    /**
     * PagerAdapter
     */
    public class PagerAdapterClass extends PagerAdapter {

        private LayoutInflater mInflater;

        public PagerAdapterClass(Context c) {
            super();
            mInflater = LayoutInflater.from(c);
        }


        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object instantiateItem(View pager, int position) {
            View v = null;
            if(position==0){
                v = mInflater.inflate(R.layout.inflate_favor, null);
                favor.setOnClickListener(mPagerListener);
            }
            else if(position==1){
                v = mInflater.inflate(R.layout.inflate_review, null);
                review.setOnClickListener(mPagerListener);
            }
            ((ViewPager) pager).addView(v, 0);

            return v;
        }

        @Override
        public void destroyItem(View pager, int position, Object view) {
            ((ViewPager) pager).removeView((View) view);
        }

        @Override
        public boolean isViewFromObject(View pager, Object obj) {
            return pager == obj;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }

        @Override
        public void finishUpdate(View arg0) {
        }
    }
}

