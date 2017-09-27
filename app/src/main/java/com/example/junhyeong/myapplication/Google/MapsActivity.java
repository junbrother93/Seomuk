package com.example.junhyeong.myapplication.Google;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.junhyeong.myapplication.R;
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
    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private String store_address;
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
        //store_address = intent.getStringExtra("store_address");
        String store_call = intent.getStringExtra("store_call"); //전화번호아이콘 만들어지면 사용

        x = intent.getDoubleExtra("X", 0.0);
        y = intent.getDoubleExtra("Y", 0.0);

        final TextView tvStore_name, tvStore_address, tvStore_grade, title;
        title = (TextView) findViewById(R.id.textView9);
        title.setTypeface(BMJUA);

        tvStore_name = (TextView) findViewById(R.id.textView6);
        tvStore_address = (TextView) findViewById(R.id.textView7);
        tvStore_grade = (TextView) findViewById(R.id.textView8);

        tvStore_name.setText(store_name);
        tvStore_grade.setText(store_grade);


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest postStringRequest = new JsonObjectRequest(Request.Method.GET, "https://maps.googleapis.com/maps/api/geocode/json?language=ko&latlng=" +x+ "," +y+ "&key=AIzaSyCR6PUO1y9JYM6fPjk85fre94xNabcRqsA", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", "response :" + response.optJSONArray("results").optJSONObject(0).optString("formatted_address"));
                store_address = response.optJSONArray("results").optJSONObject(0).optString("formatted_address").substring(5);
                tvStore_address.setText(store_address);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                return params;
            }
        };
        requestQueue.add(postStringRequest);
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