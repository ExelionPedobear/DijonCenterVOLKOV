package com.example.ruslanmanca.dijoncentervolkov;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruslanmanca.dijoncentervolkov.adapters.PoiAdapter;
import com.example.ruslanmanca.dijoncentervolkov.models.Poi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class CarteActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        PoiAdapter poiAdapter = new PoiAdapter("https://my-json-server.typicode.com/lpotherat/pois/db");
        final ArrayList<Poi> lstPois = poiAdapter.GetAll();
        LatLng poiLatLng = new LatLng(0,0);
        mMap = googleMap;
        for (Poi poi : lstPois) {
            poiLatLng = new LatLng(poi.getLocation().getPosition().getLat(), poi.getLocation().getPosition().getLon());
            int pin = 0;
            if (poi.getType().equals("CINE")) {
                pin = R.mipmap.pin_cine;
            } else {
                pin = R.mipmap.pin_resto;
            }
            mMap.addMarker(new MarkerOptions()
                    .position(poiLatLng)
                    .title(poi.getType() + " - " + poi.getName())
                    .icon(BitmapDescriptorFactory.fromResource(pin))
            );
        }
            CameraPosition cameraPosition = new CameraPosition.Builder().target(poiLatLng).zoom(14.0f).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mMap.moveCamera(cameraUpdate);


    }
}
