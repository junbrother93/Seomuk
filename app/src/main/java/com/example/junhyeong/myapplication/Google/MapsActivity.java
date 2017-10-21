package com.example.junhyeong.myapplication.Google;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.GlobalApplication.GlobalApplication;
import com.example.junhyeong.myapplication.Popup.PopupActivity_Login;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.Review.Review_Activity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double x, y;
    private String store_name;
    private Typeface BMJUA;
    private Typeface BMDOHYEON;
    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private ImageView TelBtn, ReviewBtn, Nomap, FavorBtn;
    private String store_address, store_call, store_class;
    private int num, unlogin_value;
    Intent review;
    private Intent Popup_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap);
        Intent intent = getIntent();
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        num = 0;
        BMJUA = Typeface.createFromAsset(this.getAssets(), "fonts/BMJUA_ttf.ttf");
        BMDOHYEON = Typeface.createFromAsset(this.getAssets(), "fonts/BMDOHYEON_ttf.ttf");

        review = new Intent(this, Review_Activity.class);
        Popup_login = new Intent(this, PopupActivity_Login.class);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        unlogin_value = intent.getIntExtra("mypage", 0);
        store_name = intent.getStringExtra("store_name");
        String store_grade = intent.getStringExtra("store_grade");
        //store_address = intent.getStringExtra("store_address");

        store_call = intent.getStringExtra("store_call"); //전화번호아이콘 만들어지면 사용
        store_class = intent.getStringExtra("CRTFC_CLASS");
        final int store_id = intent.getIntExtra("store_id", 0);
        review.putExtra("store_id", store_id);
        if(store_class.equals("00"))
            store_class="해당사항 없음".toString();

        if(store_class.substring(0,2).equals("01"))
        {
            store_class = store_class.replaceAll("01"," Lv.1");
        }
        else if(store_class.substring(0,2).equals("02"))
        {
            store_class = store_class.replaceAll("02"," Lv.2");
        }
        else if(store_class.substring(0,2).equals("03"))
        {
            store_class = store_class.replaceAll("03"," Lv.3");
        }
        else
            store_class=" 해당사항 없음".toString();

        if (store_call.equals("") || store_call.equals(null)) {
            store_call = "정보를 제공하지 않습니다".toString();
        }
        store_call = store_call.replaceAll("\\p{Z}", "");
        if (store_call.substring(0, 2).equals("02") || store_call.substring(0, 3).equals("010") || store_call.substring(0, 3).equals("016") || store_call.substring(0, 3).equals("019")) {
        } else {

            if (store_call.substring(0, 3).equals("001")) {
                store_call = store_call.replaceAll("001", "01");
            } else if (store_call.substring(0, 3).equals("002")) {
                store_call = store_call.replaceAll("002", "02");
            } else if (store_call.substring(0, 4).equals("0002")) {
                store_call = store_call.replaceAll("0002", "02");
            } else
                store_call = "02".toString() + store_call;

        }

        x = intent.getDoubleExtra("X", 0.0);
        y = intent.getDoubleExtra("Y", 0.0);

        final TextView tvStore_name, tvStore_address, tvStore_grade, title, tvStore_class;
        title = (TextView) findViewById(R.id.textView9);
        title.setTypeface(BMDOHYEON);

        TelBtn = (ImageView) findViewById(R.id.TelBtn);
        TelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + store_call));
                startActivity(intent);
            }
        });

        ReviewBtn = (ImageView) findViewById(R.id.ReviewBtn);
        ReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review.putExtra("mypage", unlogin_value);
                setResult(RESULT_OK, review);
                startActivity(review);
            }
        });

        FavorBtn = (ImageView) findViewById(R.id.FavorBtn);

        // 즐겨찾기 버튼 클릭 이벤트
        FavorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unlogin_value == 1) // 로그인 안되있을 경우 로그인 창으로
                {
                    startActivity(Popup_login);
                } else {
                    if (num == 0) // 비활성화 되어있는 경우 활성화 시킴
                    {

                        JsonObjectRequest addBookmarkRequest = new JsonObjectRequest(Request.Method.POST, "http://13.124.127.124:3000/user/bookmark", new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("addBookmark : ", "addBookmarkResponse : " + response);
                                FavorBtn.setImageResource(R.drawable.favor_btn);
                                num++;
                                // 즐겨찾기 응답에 따른 즐겨찾기 버튼 상태 설정.. if문 사용
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("addBookmarkErr :", "addBookmarkError :" + error);
                            }

                        }) {
                            @Override
                            public Map getHeaders() throws AuthFailureError {
                                Map params = new HashMap();
                                GlobalApplication GUserID = (GlobalApplication) getApplication();
                                params.put("store_id", Integer.toString(store_id));
                                params.put("user_id", Integer.toString(GUserID.getGlobalUserID()));
                                return params;
                            }
                        };
                        requestQueue.add(addBookmarkRequest);
                    } else // 활성화 되어있는 경우 비활성화 시킴
                        {

                        JsonObjectRequest deleteBookmarkRequest = new JsonObjectRequest(Request.Method.DELETE, "http://13.124.127.124:3000/user/bookmark", new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("deleteBookmark : ", "deleteBookmarkResponse : " + response);
                                FavorBtn.setImageResource(R.drawable.favor);
                                num = 0;
                                // 즐겨찾기 응답에 따른 즐겨찾기 버튼 상태 설정.. if문 사용

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("deleteBookmarkErr :", "deleteBookmarkError :" + error);
                            }

                        }) {
                            @Override
                            public Map getHeaders() throws AuthFailureError {
                                Map params = new HashMap();
                                GlobalApplication GUserID = (GlobalApplication) getApplication();
                                params.put("store_id", Integer.toString(store_id));
                                params.put("user_id", Integer.toString(GUserID.getGlobalUserID()));
                                return params;
                            }
                        };
                        requestQueue.add(deleteBookmarkRequest);
                    }
                }
            }
        });


        tvStore_name = (TextView) findViewById(R.id.textView6);
        tvStore_address = (TextView) findViewById(R.id.textView7);
        tvStore_grade = (TextView) findViewById(R.id.textView8);
        tvStore_class = (TextView) findViewById(R.id.textView13);

        tvStore_name.setText(store_name);
        tvStore_grade.setText(store_grade);
        tvStore_class.setText(store_class);

        //Log.d("Xvalue:", "Xvalue : " + x);
        //Log.d("Yvalue:", "Yvalue : " + y);

        // 위치 정보 없을 경우 임의 지정
        if (x == 0.0 && y == 0.0 || (x == 0 && y == 0)) {
            x = 37.5652894;
            y = 126.8494668;
        }


        JsonObjectRequest getXYRequest = new JsonObjectRequest(Request.Method.GET, "https://maps.googleapis.com/maps/api/geocode/json?language=ko&latlng=" + x + "," + y + "&key=AIzaSyCR6PUO1y9JYM6fPjk85fre94xNabcRqsA", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // 위치 정보 없을 경우 처리
                if ((x == 37.5652894 && y == 126.8494668)) {
                    ImageView noMap = (ImageView) findViewById(R.id.noMap);
                    noMap.setVisibility(View.VISIBLE);
                    mapFragment.getView().setVisibility(View.GONE);
                    store_address = "주소정보없음";
                } else {
                    store_address = response.optJSONArray("results").optJSONObject(0).optString("formatted_address").substring(5);
                }
                tvStore_address.setText(store_address);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("getXYError : ", "getXYError : " + error);
            }

        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                return params;
            }
        };
        requestQueue.add(getXYRequest);


        // 즐겨찾기 버튼 상태 서버에서 확인
        StringRequest checkBookmarkRequest = new StringRequest(Request.Method.POST, "http://13.124.127.124:3000/user/bookmark", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("checkBookmark : ", "checkbookmarkResponse : " + response);

                if (response == "OK") {
                    FavorBtn.setImageResource(R.drawable.favor_btn);
                    num++;
                } else {
                    FavorBtn.setImageResource(R.drawable.favor);
                    num = 0;
                }

                // 즐겨찾기 응답에 따른 즐겨찾기 버튼 상태 설정.. if문 사용

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("checkBookmarkErr :", "checkbookmarkError :" + error);
            }

        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                GlobalApplication GUserID = (GlobalApplication) getApplication();
                params.put("store_id", Integer.toString(store_id));
                params.put("user_id", Integer.toString(GUserID.getGlobalUserID()));
                return params;
            }
        };
        requestQueue.add(checkBookmarkRequest);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(x, y);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.addMarker(new MarkerOptions().position(location).title(store_name));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17.0f));
    }
}