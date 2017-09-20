package com.example.ruslanmanca.dijoncentervolkov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ruslanmanca.dijoncentervolkov.adapters.PoiAdapter;
import com.example.ruslanmanca.dijoncentervolkov.listadapters.PoiListViewAdapter;
import com.example.ruslanmanca.dijoncentervolkov.models.Poi;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView pois;
    ImageButton imgParcours;
    ImageButton imgAjouterParcours;
    private static PoiListViewAdapter poiLvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pois = (ListView)findViewById(R.id.pois);
        imgParcours = (ImageButton)findViewById(R.id.imgParcours);

        imgParcours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParcoursActivity.class);
                startActivity(intent);
            }
        });

        imgAjouterParcours = (ImageButton)findViewById(R.id.imgAjouterParcours);

        imgAjouterParcours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AjouterParcoursActivity.class);
                startActivity(intent);
            }
        });

        PoiAdapter poiAdapter = new PoiAdapter("https://my-json-server.typicode.com/lpotherat/pois/db");
        final ArrayList<Poi> lstPois = poiAdapter.GetAll();
        poiLvAdapter = new PoiListViewAdapter(lstPois, getApplicationContext());

        pois.setAdapter(poiLvAdapter);
        pois.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Poi poi = lstPois.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("Poi", poi);
                startActivity(intent);
            }
        });
    }
}
