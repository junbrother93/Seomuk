package com.example.junhyeong.myapplication.Main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.junhyeong.myapplication.Adapter.ListViewAdapter;
import com.example.junhyeong.myapplication.Data.Store;
import com.example.junhyeong.myapplication.Data.Store2;
import com.example.junhyeong.myapplication.Google.MapsActivity;
import com.example.junhyeong.myapplication.Popup.PopupActivity_Explain;
import com.example.junhyeong.myapplication.Popup.PopupActivity_Local;
import com.example.junhyeong.myapplication.Popup.PopupActivity_Login;
import com.example.junhyeong.myapplication.Popup.PopupActivity_Menu;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.Select.Select_MyPage_Activity;
import com.example.junhyeong.myapplication.widget.IndexableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity implements Response.Listener<JSONObject>,
        Response.ErrorListener {
    public static final String REQUEST_TAG = "MainActivity";
    private RequestQueue mQueue;
    private ImageView BtnLocalChange, BtnMenuChange, BtnMyPage, warn, BtnInfo;
    private TextView Text;
    private ArrayList<Store> arrayList;
    private ArrayList<Store2> arrayList2;
    private IndexableListView listview;
    private int AnsimValue, num;
    private Intent ActPop_Location, ActPop_Menu, MyPage, LoginPop, PopExplain;
    private Typeface Tmon;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        warn = (ImageView) findViewById(R.id.warn);
        Tmon = Typeface.createFromAsset(this.getAssets(), "fonts/TmonMonsori.ttf.ttf");

        mQueue = PodVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

        ActPop_Location = new Intent(this, PopupActivity_Local.class);
        ActPop_Menu = new Intent(this, PopupActivity_Menu.class);
        MyPage = new Intent(this, Select_MyPage_Activity.class);
        LoginPop = new Intent(this, PopupActivity_Login.class);
        PopExplain = new Intent(this, PopupActivity_Explain.class);

        BtnLocalChange = (ImageView) findViewById(R.id.Location); // 네비게이션바에 있는 "지역" 버튼
        BtnMenuChange = (ImageView) findViewById(R.id.Menu);
        BtnMyPage = (ImageView) findViewById((R.id.MyPage));
        BtnInfo = (ImageView) findViewById(R.id.info);

        Text = (TextView) findViewById(R.id.Text); // 글씨바뀌는건 위의 mButton 버튼
        Text.setTypeface(Tmon);

        Intent intent = getIntent();

        String local = intent.getStringExtra("local");
        String menu = intent.getStringExtra("menu");

        try {
            local = URLEncoder.encode(local, "UTF-8");
            menu = URLEncoder.encode(menu, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        url = "http://13.124.127.124:3000/auth/menu/" + menu.toString() + "/loc/" + local.toString();

        num = intent.getIntExtra("mypage", 0);


        // 안심 먹거리일경우 url 변경
        if (menu.toString().equals("food")) {
            AnsimValue = 1;
            url = "http://13.124.127.124:3000/food/loc/" + local.toString();
        } else
            AnsimValue = 0;
        ActPop_Menu.putExtra("local", local);
        setResult(RESULT_OK, ActPop_Menu);
        ActPop_Location.putExtra("menu", menu);
        setResult(RESULT_OK, ActPop_Location);

        jsonRequest(local, url);

        // 지역 선택 버튼 눌렀을 경우 지역 선택 팝업 띄움
        BtnLocalChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(ActPop_Location, 0);
            }
        });

        //메뉴 선택 버튼 눌렀을 경우 메뉴 선택 팝업 띄움
        BtnMenuChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(ActPop_Menu, 0);
            }
        });


        BtnMyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == 1)
                    startActivity(LoginPop);
                else
                    startActivity(MyPage);
            }
        });
        BtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PopExplain);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // PopupActivity 에서 보낸 url, local 값을 받음
        String url;
        String local;
        String menu;
        url = data.getStringExtra("url");
        local = data.getStringExtra("local");
        menu = data.getStringExtra("menu");

        try {
            local = URLEncoder.encode(local, "UTF-8");
            menu = URLEncoder.encode(menu, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (menu.toString().equals("food")) {
            AnsimValue = 1;
            url = "http://13.124.127.124:3000/food/loc/" + local.toString();
        } else
            AnsimValue = 0;
        ActPop_Location.putExtra("menu", menu);
        setResult(RESULT_OK, ActPop_Location);
        ActPop_Menu.putExtra("local", local);
        setResult(RESULT_OK, ActPop_Menu);

        jsonRequest(local, url);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Log.e("error", "" + error); // 에러 났을경우..
        Log.e("error url", "" + url);
    }


    @Override
    public void onResponse(JSONObject response) {


        Log.d("url", "url :" + url);
        Log.d("response", "response" + response);

        int total = response.optInt("total", 0);    // 총 갯수
        Log.d("total", "totalValue :" + total);
        // 안심먹거리일 경우
        if (AnsimValue == 1) {
            arrayList = new ArrayList<Store>();

            // 리스트 생성
            final ArrayList<JSONObject> ArrData = new ArrayList<JSONObject>();
            ArrayList<Integer> ArrCTF_CODE = new ArrayList<Integer>();
            ArrayList<Integer> ArrCTF_TYPE = new ArrayList<Integer>();
            ArrayList<String> ArrCTF_TYPE_NAME = new ArrayList<String>();
            ArrayList<String> ArrCTF_NAME = new ArrayList<String>();
            final ArrayList<Double> ArrCTF_X = new ArrayList<Double>();
            final ArrayList<Double> ArrCTF_Y = new ArrayList<Double>();
            ArrayList<String> ArrCTF_ADDR = new ArrayList<String>();
            ArrayList<String> ArrCTF_TEL = new ArrayList<String>();

            // 리스트뷰랑 어댑터..
            listview = (IndexableListView) findViewById(R.id.listview);
            ListViewAdapter adapter = new ListViewAdapter();
            listview.setAdapter(adapter);
            listview.setFastScrollEnabled(true);


            // 스크롤 뷰 안에 있는 리스트 뷰 스크롤 되게설정


            for (int i = 0; i <= total - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
            {
             /*
             getInt 대신 optInt 쓴 이유
             http://developeryou.blogspot.kr/2015/09/getjsonobject-null.html

             JSONArray랑 jSONObject 차이
             http://ddo-o.tistory.com/95
             */


                // 아이템 불러와..
                ArrData.add(response.optJSONArray("data").optJSONObject(i));
                ArrCTF_NAME.add(ArrData.get(i).optString("CTF_NAME", "No Value"));
                ArrCTF_TEL.add(ArrData.get(i).optString("CTF_TEL", "No Value"));
                ArrCTF_X.add(ArrData.get(i).optDouble("CTF_X", 0.0));
                ArrCTF_Y.add(ArrData.get(i).optDouble("CTF_Y", 0.0));
                ArrCTF_CODE.add(ArrData.get(i).optInt("CTF_CODE", 0));

                // 쓸지 안쓸지 결정
                /*
                ArrCTF_TYPE.add(ArrData.get(i).optInt("CTF_TYPE", 0));
                ArrCTF_TYPE_NAME.add(ArrData.get(i).optString("CTF_TYPE_NAME", "No Value"));
                ArrCTF_ADDR.add(ArrData.get(i).optString("CTF_ADDR", "No Value"));
                */

                // 객체 추가
                Store s = new Store();
                s.setArrData(response.optJSONArray("data").optJSONObject(i));
                s.setCTF_CODE(ArrData.get(i).optInt("CTF_CODE", 0));
                //s.setCTF_TYPE(ArrData.get(i).optInt("CTF_TYPE", 0));
                s.setCTF_TYPE_NAME(ArrData.get(i).optString("CTF_TYPE_NAME", "No Value"));
                s.setCTF_NAME(ArrData.get(i).optString("CTF_NAME", "No Value"));
                s.setCTF_X(ArrData.get(i).optDouble("CTF_X", 37.5652894));
                s.setCTF_Y(ArrData.get(i).optDouble("CTF_Y", 126.8494668));
                //s.setCTF_ADDR(ArrData.get(i).optString("CTF_ADDR", "No Value"));
                s.setCTF_TEL(ArrData.get(i).optString("CTF_TEL", "No Value"));
                arrayList.add(s);
            }
            // 정렬
            Collections.sort(arrayList);
            // 정렬 한 것 어댑터에 추가
            if (total == 0) {
                warn.setVisibility(View.VISIBLE);
                listview.setVisibility(View.GONE);
            } else {
                warn.setVisibility(View.INVISIBLE);
                listview.setVisibility(View.VISIBLE);
                for (int i = 0; i <= total - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                {
                    if (arrayList.get(i).getCTF_TYPE_NAME().toString().equals("자랑스러운 한국음식점".toString()))
                        adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.b6), arrayList.get(i).getCTF_NAME());
                    else if (arrayList.get(i).getCTF_TYPE_NAME().toString().equals("원산지표시 우수음식점".toString()))
                        adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.b4), arrayList.get(i).getCTF_NAME());
                    else
                        adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.b3), arrayList.get(i).getCTF_NAME());
                }
            }

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                    intent.putExtra("store_name", arrayList.get((int) id).getCTF_NAME());
                    //intent.putExtra("store_address", arrayList.get((int) id).getCTF_ADDR());
                    intent.putExtra("store_grade", arrayList.get((int) id).getCTF_TYPE_NAME());
                    intent.putExtra("store_call", arrayList.get((int) id).getCTF_TEL());
                    intent.putExtra("X", arrayList.get((int) id).getCTF_X());
                    intent.putExtra("Y", arrayList.get((int) id).getCTF_Y());
                    intent.putExtra("store_id", arrayList.get((int) id).getCTF_CODE());
                    intent.putExtra("mypage", num);
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                }
            });

        }

        // 지정 업소인 경우
        else {
            arrayList2 = new ArrayList<Store2>();

            // 리스트 생성
            ArrayList<JSONObject> ArrData = new ArrayList<JSONObject>();
            ArrayList<Integer> CRTFC_UPSO_MGT_SNO = new ArrayList<Integer>();
            ArrayList<String> UPSO_NM = new ArrayList<String>();
            ArrayList<String> CGG_CODE_NM = new ArrayList<String>();
            ArrayList<String> COB_CODE_NM = new ArrayList<String>();
            ArrayList<String> BIZCND_CODE_NM = new ArrayList<String>();
            ArrayList<String> OWNER_NM = new ArrayList<String>();
            ArrayList<String> CRTFC_GBN_NM = new ArrayList<String>();
            ArrayList<String> CRTFC_CHR_NM = new ArrayList<String>();
            ArrayList<String> CRTFC_YMD = new ArrayList<String>();
            ArrayList<String> USE_YN = new ArrayList<String>();
            ArrayList<Double> Y_DNTS = new ArrayList<Double>();
            ArrayList<Double> X_CNTS = new ArrayList<Double>();
            ArrayList<String> TEL_NO = new ArrayList<String>();
            ArrayList<String> RDN_DETAIL_ADDR = new ArrayList<String>();
            ArrayList<String> RDN_CODE_NM = new ArrayList<String>();
            ArrayList<String> CRT_USR = new ArrayList<String>();
            ArrayList<String> FOOD_MENU = new ArrayList<String>();

            // 리스트뷰랑 어댑터..
            listview = (IndexableListView) findViewById(R.id.listview);
            ListViewAdapter adapter = new ListViewAdapter();
            listview.setAdapter(adapter);
            listview.setFastScrollEnabled(true);


            // 스크롤 뷰 안에 있는 리스트 뷰 스크롤 되게설정

            listview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });

            for (int i = 0; i <= total - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
            {
                // 아이템 불러와..
                ArrData.add(response.optJSONArray("data").optJSONObject(i));
                UPSO_NM.add(ArrData.get(i).optString("UPSO_NM", "No Value"));
                TEL_NO.add(ArrData.get(i).optString("TEL_NO", "No Value"));


                // 객체 추가
                Store2 s = new Store2();

                s.setArrData2(response.optJSONArray("data").optJSONObject(i));

                s.setCRTFC_UPSO_MGT_SNO(ArrData.get(i).optInt("CRTFC_UPSO_MGT_SNO", 0));
                s.setUPSO_NM((ArrData.get(i)).optString("UPSO_NM", "No Value"));
                s.setCGG_CODE_NM((ArrData.get(i)).optString("CGG_CODE_NM", "No Value"));
                s.setCOB_CODE_NM((ArrData.get(i)).optString("COB_CODE_NM", "No Value"));
                s.setBIZCND_CODE_NM((ArrData.get(i)).optString("BIZCND_CODE_NM", "No Value"));
                s.setOWNER_NM((ArrData.get(i)).optString("OWNER_NM", "No Value"));
                s.setCRTFC_GBN_NM((ArrData.get(i)).optString("CRTFC_GBN_NM", "No Value"));
                s.setCRTFC_CHR_NM((ArrData.get(i)).optString("CRTFC_CHR_NM", "No Value"));
                s.setCRTFC_YMD((ArrData.get(i)).optString("CRTFC_YMD", "No Value"));
                s.setUSE_YN((ArrData.get(i)).optString("USE_YN", "No Value"));
                s.setY_DNTS(ArrData.get(i).optDouble("Y_DNTS", 37.5652894));
                s.setX_CNTS(ArrData.get(i).optDouble("X_CNTS", 126.8494668));
                s.setTEL_NO(ArrData.get(i).optString("TEL_NO", "No Value"));
                s.setRDN_DETAIL_ADDR(ArrData.get(i).optString("RDN_DETAIL_ADDR", "No Value"));
                s.setRDN_CODE_NM(ArrData.get(i).optString("RDN_CODE_NM", "No Value"));
                s.setCRT_USR(ArrData.get(i).optString("CRT_USR", "No Value"));
                s.setFOOD_MENU(ArrData.get(i).optString("FOOD_MENU", "No Value"));
                arrayList2.add(s);
            }
            // 정렬
            Collections.sort(arrayList2);

            // 정렬 한 것 어댑터에 추가
            if (total == 0) {
                warn.setVisibility(View.VISIBLE);
                listview.setVisibility(View.GONE);
            } else {
                warn.setVisibility(View.INVISIBLE);
                listview.setVisibility(View.VISIBLE);
                for (int i = 0; i <= total - 1; i++) // index 값이라서 총 갯수에서 1을 빼줌
                {

                    if (arrayList2.get(i).getCRTFC_GBN_NM().toString().equals("저염실천음식점".toString()) || arrayList2.get(i).getCRTFC_GBN_NM().toString().equals("저염참여음식점".toString()))
                        adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.b7), arrayList2.get(i).getUPSO_NM());
                    else if (arrayList2.get(i).getCRTFC_GBN_NM().toString().equals("먹을만큼적당히".toString()))
                        adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.b2), arrayList2.get(i).getUPSO_NM());
                    else if (arrayList2.get(i).getCRTFC_GBN_NM().toString().equals("건강음식점".toString()))
                        adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.b1), arrayList2.get(i).getUPSO_NM());
                    else if (arrayList2.get(i).getCRTFC_GBN_NM().toString().equals("위생등급제".toString()))
                        adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.b5), arrayList2.get(i).getUPSO_NM());
                    else if (arrayList2.get(i).getCRTFC_GBN_NM().toString().equals("자랑스러운 한국음식점".toString()))
                        adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.b6), arrayList2.get(i).getUPSO_NM());
                    else if (arrayList2.get(i).getCRTFC_GBN_NM().toString().equals("원산지표시 우수음식점".toString()))
                        adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.b4), arrayList2.get(i).getUPSO_NM());
                    else if (arrayList2.get(i).getCRTFC_GBN_NM().toString().equals("채식메뉴음식점".toString()) || arrayList2.get(i).getCRTFC_GBN_NM().toString().equals("채식전문음식점".toString()))
                        adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.b8), arrayList2.get(i).getUPSO_NM());
                    else
                        adapter.addItem(ContextCompat.getDrawable(MainActivity.this, R.drawable.b3), arrayList2.get(i).getUPSO_NM());
                }

            }
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                    intent.putExtra("store_name", arrayList2.get((int) id).getUPSO_NM());
                    intent.putExtra("store_address", arrayList2.get((int) id).getRDN_DETAIL_ADDR());
                    intent.putExtra("store_grade", arrayList2.get((int) id).getCRTFC_GBN_NM());
                    intent.putExtra("store_call", arrayList2.get((int) id).getTEL_NO());
                    intent.putExtra("X", arrayList2.get((int) id).getY_DNTS());
                    intent.putExtra("Y", arrayList2.get((int) id).getX_CNTS());
                    intent.putExtra("store_id", arrayList2.get((int) id).getCRTFC_UPSO_MGT_SNO());
                    intent.putExtra("mypage", num);
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                }
            });

        }
    }

    protected void jsonRequest(String local, String url) {
        try {
            local = URLDecoder.decode(local, "UTF-8");
            Log.d("UTF-8", local);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Text.setText(local.toString());

        final PodJsonRequest jsonRequest = new PodJsonRequest(Request.Method.GET, url, new JSONObject(), MainActivity.this, MainActivity.this);
        jsonRequest.setTag(REQUEST_TAG);
        mQueue.add(jsonRequest);
    }
}