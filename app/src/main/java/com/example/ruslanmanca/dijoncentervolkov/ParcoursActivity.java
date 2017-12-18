package com.example.ruslanmanca.dijoncentervolkov;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ruslanmanca.dijoncentervolkov.adapters.PoiAdapter;
import com.example.ruslanmanca.dijoncentervolkov.adapters.database.DatabaseAdapter;
import com.example.ruslanmanca.dijoncentervolkov.adapters.database.ParcoursAdapter;
import com.example.ruslanmanca.dijoncentervolkov.adapters.database.StatutAdapter;
import com.example.ruslanmanca.dijoncentervolkov.listadapters.ParcoursListViewAdapter;
import com.example.ruslanmanca.dijoncentervolkov.listadapters.PoiListViewAdapter;
import com.example.ruslanmanca.dijoncentervolkov.models.Poi;
import com.example.ruslanmanca.dijoncentervolkov.models.database.Parcours;
import com.example.ruslanmanca.dijoncentervolkov.models.database.Statut;

import java.util.ArrayList;
import java.util.ListIterator;

public class ParcoursActivity extends AppCompatActivity {
    ListView lvParcours;
    private static ParcoursListViewAdapter parcoursLvAdapter;
    ArrayList<Parcours> parcours;
    Integer compteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcours);

        lvParcours = (ListView)findViewById(R.id.lvParcours);
        DatabaseAdapter maCollecDb = new DatabaseAdapter(this);
        SQLiteDatabase db = maCollecDb.getWritableDatabase();
        final ParcoursAdapter pa = new ParcoursAdapter(db);
        parcours = pa.getAll();
        PoiAdapter poiAdapter = new PoiAdapter("https://my-json-server.typicode.com/lpotherat/pois/pois/" );

        compteur = parcours.size() * 2;

        for (final ListIterator<Parcours> p = parcours.listIterator(); p.hasNext();) {
            final Parcours newParcours = p.next();
            /*Poi cinema = */
            poiAdapter.GetById(newParcours.getIdCinema(), new PoiAdapter.PoiAdapterListener() {
                @Override
                public boolean onPoiGetById(Poi poi) {
                    newParcours.setPoiCinema(poi);
                    p.set(newParcours);
                    Decrementer();
                    return true;
                }

                @Override
                public boolean onPoiGetCinemasOuRestaurants(ArrayList<Poi> pois){
                    return true;
                }

                @Override
                public boolean onPoiGetAll(ArrayList<Poi> pois) {
                    return true;
                }
            });

            poiAdapter.GetById(newParcours.getIdRestaurant(), new PoiAdapter.PoiAdapterListener() {
                @Override
                public boolean onPoiGetById(Poi poi) {
                    newParcours.setPoiRestaurant(poi);
                    p.set(newParcours);
                    Decrementer();
                    return true;
                }

                @Override
                public boolean onPoiGetCinemasOuRestaurants(ArrayList<Poi> pois){
                    return true;
                }

                @Override
                public boolean onPoiGetAll(ArrayList<Poi> pois) {
                    return true;
                }
            });
        }
    }

    public void Decrementer(){
        compteur--;
        if (compteur == 0){
            parcoursLvAdapter = new ParcoursListViewAdapter(parcours, getApplicationContext());
            lvParcours.setAdapter(parcoursLvAdapter);
        }
    }
}
