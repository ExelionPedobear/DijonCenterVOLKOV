package com.example.ruslanmanca.dijoncentervolkov;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ruslanmanca.dijoncentervolkov.models.Poi;

public class DetailActivity extends AppCompatActivity {

    TextView txtType;
    TextView txtName;
    TextView txtAdress;
    TextView txtPostalCode;
    TextView txtCity;
    TextView txtLat;
    TextView txtLon;
    LinearLayout llBackground;

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




        Poi poi = (Poi)getIntent().getSerializableExtra("Poi");

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
    }
}
