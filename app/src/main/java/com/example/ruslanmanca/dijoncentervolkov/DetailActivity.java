package com.example.ruslanmanca.dijoncentervolkov;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ruslanmanca.dijoncentervolkov.models.Poi;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView txtType;
    TextView txtName;
    TextView txtAdress;
    TextView txtPostalCode;
    TextView txtCity;
    TextView txtLat;
    TextView txtLon;
    LinearLayout llBackground;
    GoogleMap mMap;
    Poi poi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtType = (TextView)findViewById(R.id.txtType);
        txtName = (TextView)findViewById(R.id.txtName);
        txtAdress = (TextView)findViewById(R.id.txtAdress);
        txtPostalCode = (TextView)findViewById(R.id.txtPostalCode);
        txtCity = (TextView)findViewById(R.id.txtCity);
        txtLat = (TextView)findViewById(R.id.txtLat);
        txtLon = (TextView)findViewById(R.id.txtLon);

        llBackground = (LinearLayout) findViewById(R.id.llBackground);


        poi = (Poi)getIntent().getSerializableExtra("Poi");

        txtType.setText(poi.getType());
        txtName.setText(poi.getName());
        txtAdress.setText(poi.getLocation().getAdress());
        txtPostalCode.setText(poi.getLocation().getPostalCode());
        txtCity.setText(poi.getLocation().getCity());
        txtLat.setText(String.valueOf(poi.getLocation().getPosition().getLat()));
        txtLon.setText(String.valueOf(poi.getLocation().getPosition().getLon()));

        if (poi.getType().equals("CINE")){
            llBackground.setBackgroundColor(Color.parseColor("#fd8469"));
        }
        if (poi.getType().equals("REST")){
            llBackground.setBackgroundColor(Color.parseColor("#52bed8"));
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng poiLatLng = new LatLng(poi.getLocation().getPosition().getLat(), poi.getLocation().getPosition().getLon());
        int pin = 0;
        if (poi.getType().equals("CINE")){
            pin = R.mipmap.pin_cine;
        }
        else{
            pin = R.mipmap.pin_resto;
        }
        mMap.addMarker(new MarkerOptions()
                .position(poiLatLng)
                .title(poi.getType() + " - " + poi.getName())
                .icon(BitmapDescriptorFactory.fromResource(pin))
        );

        CameraPosition cameraPosition = new CameraPosition.Builder().target(poiLatLng).zoom(14.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }
}
