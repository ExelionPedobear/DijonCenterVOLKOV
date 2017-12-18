package com.example.ruslanmanca.dijoncentervolkov;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ruslanmanca.dijoncentervolkov.adapters.PoiAdapter;
import com.example.ruslanmanca.dijoncentervolkov.adapters.database.DatabaseAdapter;
import com.example.ruslanmanca.dijoncentervolkov.adapters.database.ParcoursAdapter;
import com.example.ruslanmanca.dijoncentervolkov.adapters.database.StatutAdapter;
import com.example.ruslanmanca.dijoncentervolkov.listadapters.PoiSpinnerAdapter;
import com.example.ruslanmanca.dijoncentervolkov.listadapters.StatutSpinnerAdapter;
import com.example.ruslanmanca.dijoncentervolkov.models.Poi;
import com.example.ruslanmanca.dijoncentervolkov.models.database.Parcours;
import com.example.ruslanmanca.dijoncentervolkov.models.database.Statut;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AjouterParcoursActivity extends AppCompatActivity {

    ArrayList<Poi> cinemas;
    ArrayList<Poi> restaurants;
    ArrayList<Statut> statuts;

    PoiSpinnerAdapter customCinemaAdapter;
    PoiSpinnerAdapter customRestaurantAdapter;
    StatutSpinnerAdapter customStatutAdapter;

    Spinner spCinemas;
    Spinner spRestaurants;
    Spinner spStatuts;

    Button btnValider;

    Parcours parcours;

    DatePicker tpDateRealisationPrevue;
    EditText editAccompagnant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_parcours);
        spCinemas = (Spinner) findViewById(R.id.spCinemas);
        spRestaurants = (Spinner) findViewById(R.id.spRestaurants);
        spStatuts = (Spinner) findViewById(R.id.spStatuts);

        btnValider = (Button) findViewById(R.id.btnValider);

        tpDateRealisationPrevue = (DatePicker) findViewById(R.id.tpDateRealisationPrevue);
        editAccompagnant = (EditText) findViewById(R.id.editAccompagnant);

        DatabaseAdapter maCollecDb = new DatabaseAdapter(this);
        SQLiteDatabase db = maCollecDb.getWritableDatabase();
        final StatutAdapter sa = new StatutAdapter(db);
        final ParcoursAdapter pa = new ParcoursAdapter(db);
        statuts = sa.getAll();
        customStatutAdapter = new StatutSpinnerAdapter(statuts, this);
        spStatuts.setAdapter(customStatutAdapter);

        PoiAdapter poiAdapter = new PoiAdapter("https://my-json-server.typicode.com/lpotherat/pois/db");

        poiAdapter.GetCinemasOuRestaurants("CINE", new PoiAdapter.PoiAdapterListener() {
            @Override
            public boolean onPoiGetById(Poi poi) {
                return true;
            }

            @Override
            public boolean onPoiGetCinemasOuRestaurants(ArrayList<Poi> pois){
                cinemas = pois;
                customCinemaAdapter = new PoiSpinnerAdapter(cinemas, getApplicationContext());
                spCinemas.setAdapter(customCinemaAdapter);
                return true;
            }

            @Override
            public boolean onPoiGetAll(ArrayList<Poi> pois) {
                return true;
            }
        });

        poiAdapter.GetCinemasOuRestaurants("REST", new PoiAdapter.PoiAdapterListener() {
            @Override
            public boolean onPoiGetById(Poi poi) {
                return true;
            }

            @Override
            public boolean onPoiGetCinemasOuRestaurants(ArrayList<Poi> pois){
                restaurants = pois;
                customRestaurantAdapter = new PoiSpinnerAdapter(restaurants, getApplicationContext());
                spRestaurants.setAdapter(customRestaurantAdapter);
                return true;
            }

            @Override
            public boolean onPoiGetAll(ArrayList<Poi> pois) {
                return true;
            }
        });

        //cinemas = poiAdapter.GetCinemas();
        //restaurants = poiAdapter.GetRestaurants();

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parcours = new Parcours();
                parcours.setAccompagnant(editAccompagnant.getText().toString());
                int day  = tpDateRealisationPrevue.getDayOfMonth();
                int month= tpDateRealisationPrevue.getMonth();
                int year = tpDateRealisationPrevue.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                parcours.setDateRealisation(calendar.getTime().toString());
                parcours.setIdCinema(((Poi)spCinemas.getSelectedItem()).getId());
                parcours.setIdRestaurant(((Poi)spRestaurants.getSelectedItem()).getId());
                parcours.setIdStatut(((Statut)spStatuts.getSelectedItem()).getId());
                pa.insert(parcours);

                Intent intent = new Intent(AjouterParcoursActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
