package com.example.junhyeong.myapplication.Google;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.junhyeong.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double x, y;
    private String store_name;
    private Typeface BMJUA;
    private static final int MY_LOCATION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_googlemap);
        BMJUA = Typeface.createFromAsset(this.getAssets(), "fonts/BMJUA_ttf.ttf");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        store_name = intent.getStringExtra("store_name");
        String store_grade = intent.getStringExtra("store_grade");
        String store_address = intent.getStringExtra("store_address");
        String store_call = intent.getStringExtra("store_call"); //전화번호아이콘 만들어지면 사용

        x = intent.getDoubleExtra("X", 0.0);
        y = intent.getDoubleExtra("Y", 0.0);

        TextView tvStore_name, tvStore_address, tvStore_grade, title;
        title = (TextView) findViewById(R.id.textView9);
        title.setTypeface(BMJUA);

        tvStore_name = (TextView) findViewById(R.id.textView6);
        tvStore_address = (TextView) findViewById(R.id.textView7);
        tvStore_grade = (TextView) findViewById(R.id.textView8);

        tvStore_name.setText(store_name);
        tvStore_address.setText(store_address);
        tvStore_grade.setText(store_grade);


        // 좌표계 (준희가 되면 삭제, 안되면 다시 해야함)

        /*
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postStringRequest = new StringRequest(Request.Method.GET, "https://dapi.kakao.com/v2/local/geo/transcoord.json?x=" + x + "&y=" + y + "&input_coord=WTM&output_coord=WGS84", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("asfd","asdf" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                params.put("client_id", "KakaoAk 19ecef4f68dc725c76b07d9302ef942a");
                return params;
            }
        };
        requestQueue.add(postStringRequest);
        */


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
        //mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.addMarker(new MarkerOptions().position(location).title(store_name));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17.0f));
    }
}