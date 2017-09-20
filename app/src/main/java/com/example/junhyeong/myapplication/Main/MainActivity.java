package com.example.junhyeong.myapplication.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SectionIndexer;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.junhyeong.myapplication.Adapter.ListViewAdapter;
import com.example.junhyeong.myapplication.Data.Store;
import com.example.junhyeong.myapplication.Google.MapsActivity;
import com.example.junhyeong.myapplication.Popup.PopupActivity_Local;
import com.example.junhyeong.myapplication.R;
import com.example.junhyeong.myapplication.util.StringMatcher;
import com.example.junhyeong.myapplication.widget.IndexableListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity implements Response.Listener<JSONObject>,
        Response.ErrorListener {
    public static final String REQUEST_TAG = "MainActivity";
    private final int DYNAMIC_VIEW_ID = 10000;
    private RequestQueue mQueue;
    private Button mButton,BtnLocalChange;
    private ArrayList<Store> arrayList;
    private IndexableListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = PodVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();
        final Intent ActPop = new Intent(this, PopupActivity_Local.class);
        // final Intent ActLocal = new Intent(this, Select_LocationActivity.class);
        // startActivityForResult(ActLocal, 0); 2017-09-06 로케이션 수정전
        BtnLocalChange = (Button)findViewById(R.id.Location); // 네비게이션바에 있는 "지역" 버튼
        mButton = (Button)findViewById(R.id.mButton); // 글씨바뀌는건 위의 mButton 버튼
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String local = intent.getStringExtra("local");

        jsonRequest(local, url);
        // 지역 선택 버튼 눌렀을 경우 지역 선택 팝업 띄움
        BtnLocalChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(ActPop, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // PopupActivity 에서 보낸 url, local 값을 받음
        String url = data.getStringExtra("url");
        String local = data.getStringExtra("local");

        //
        jsonRequest(local, url);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // 에러 났을경우..
    }

    @Override
    public void onResponse(JSONObject response) {
        int total = response.optInt("total", 0);    // 총 갯수

        arrayList = new ArrayList<Store>();

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
        listview = (IndexableListView)findViewById(R.id.listview);
        ListViewAdapter adapter = new ListViewAdapter();
        listview.setAdapter(adapter);
        listview.setFastScrollEnabled(true);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                Toast.makeText(MainActivity.this ,""+id, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

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



            // 아이템 불러와..
            ArrData.add(response.optJSONArray("data").optJSONObject(i));
            ArrCTF_NAME.add(ArrData.get(i).optString("CTF_NAME", "No Value"));
            ArrCTF_TEL.add(ArrData.get(i).optString("CTF_TEL", "No Value"));
            /*
            ArrCTF_CODE.add(ArrData.get(i).optInt("CTF_CODE", 0));
            ArrCTF_TYPE.add(ArrData.get(i).optInt("CTF_TYPE", 0));
            ArrCTF_TYPE_NAME.add(ArrData.get(i).optString("CTF_TYPE_NAME", "No Value"));
            ArrCTF_NAME.add(ArrData.get(i).optString("CTF_NAME", "No Value"));
            ArrCTF_X.add(ArrData.get(i).optDouble("CTF_X", 0.0));
            ArrCTF_Y.add(ArrData.get(i).optDouble("CTF_Y", 0.0));
            ArrCTF_ADDR.add(ArrData.get(i).optString("CTF_ADDR", "No Value"));
            ArrCTF_TEL.add(ArrData.get(i).optString("CTF_TEL", "No Value"));
            */

            // 아이템 추가
            //adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_launcher), "", ArrCTF_NAME.get(i));

            // 객체 추가
            Store s = new Store();
            s.setArrData(response.optJSONArray("data").optJSONObject(i));
            s.setCTF_CODE(ArrData.get(i).optInt("CTF_CODE", 0));
            s.setCTF_TYPE(ArrData.get(i).optInt("CTF_TYPE", 0));
            s.setCTF_TYPE_NAME(ArrData.get(i).optString("CTF_TYPE_NAME", "No Value"));
            s.setCTF_NAME(ArrData.get(i).optString("CTF_NAME", "No Value"));
            s.setCTF_X(ArrData.get(i).optDouble("CTF_X", 0.0));
            s.setCTF_Y(ArrData.get(i).optDouble("CTF_Y", 0.0));
            s.setCTF_ADDR(ArrData.get(i).optString("CTF_ADDR", "No Value"));
            s.setCTF_TEL(ArrData.get(i).optString("CTF_TEL", "No Value"));
            arrayList.add(s);
        }
        // 정렬
        Collections.sort(arrayList);
      /*  ContentAdapter adapter1 = new ContentAdapter(this,android.R.layout.simple_list_item_1,ArrCTF_NAME);
        listview.setAdapter(adapter1);
*/


        // 정렬 한 것 어댑터에 추가
        for(int i = 0; i <= total-1; i++) // index 값이라서 총 갯수에서 1을 빼줌
        {
            adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_launcher), arrayList.get(i).getCTF_NAME() ,arrayList.get(i).getCTF_TEL());
        }

    }

    protected void jsonRequest(String local, String url) {
        mButton.setText(local);
        final PodJsonRequest jsonRequest = new PodJsonRequest(Request.Method.GET, url, new JSONObject(), MainActivity.this, MainActivity.this);
        jsonRequest.setTag(REQUEST_TAG);
        mQueue.add(jsonRequest);
    }
    public class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer {

        private String mSections = "#ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎ";

        public ContentAdapter(Context context, int textViewResourceId,ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public int getPositionForSection(int section) {
            // If there is no item for current section, previous section will be selected
            for (int i = section; i >= 0; i--) {
                for (int j = 0; j < getCount(); j++) {
                    if (i == 0) {
                        // For numeric section
                        for (int k = 0; k <= 9; k++) {
                            if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(k)))
                                return j;
                        }
                    } else {
                        if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(mSections.charAt(i))))
                            return j;
                    }
                }
            }
            return 0;
        }

        @Override
        public int getSectionForPosition(int position) {
            return 0;
        }

        @Override
        public Object[] getSections() {
            String[] sections = new String[mSections.length()];
            for (int i = 0; i < mSections.length(); i++)
                sections[i] = String.valueOf(mSections.charAt(i));
            return sections;
        }
    }

}

