package com.example.ruslanmanca.dijoncentervolkov;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruslanmanca.dijoncentervolkov.adapters.PoiAdapter;
import com.example.ruslanmanca.dijoncentervolkov.adapters.contentproviders.PersonProvider;
import com.example.ruslanmanca.dijoncentervolkov.dictionaries.HealthDictionary;
import com.example.ruslanmanca.dijoncentervolkov.dictionaries.PersonDictionary;
import com.example.ruslanmanca.dijoncentervolkov.listadapters.PoiListViewAdapter;
import com.example.ruslanmanca.dijoncentervolkov.models.Poi;
import com.example.ruslanmanca.dijoncentervolkov.models.contentproviders.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView pois;
    TextView nbPois;
    ImageButton imgParcours;
    ImageButton imgAjouterParcours;
    ImageButton imgAfficherCarte;
    EditText txtLogin;
    Button btnLogin;
    Person person;
    private static PoiListViewAdapter poiLvAdapter;

    private final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = (EditText) findViewById(R.id.txtLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.RECEIVE_SMS)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.RECEIVE_SMS},
                        MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }

        nbPois = (TextView)findViewById(R.id.nbPois);
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

        imgAfficherCarte = (ImageButton)findViewById(R.id.imgAfficherCarte);

        imgAfficherCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarteActivity.class);

                intent.putExtra("Person", person);

                startActivity(intent);
            }
        });

        final PoiAdapter poiAdapter = new PoiAdapter("https://my-json-server.typicode.com/lpotherat/pois/db");
        final ArrayList<Poi> lstPois = poiAdapter.GetAll(null);
        nbPois.setText(String.valueOf(lstPois.size()));
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

        //ContentResolver cr = getContentResolver();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = txtLogin.getText().toString();
                PersonProvider personProvider = new PersonProvider();
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(Uri.parse("content://ruslanauthority/ruslanlogin/" + login), null, null, null, null);
                person = personProvider.getPerson(cursor);
                Toast toast = Toast.makeText(getApplicationContext(), "Logged as : " + person.getLoginPerson(), Toast.LENGTH_LONG);
                toast.show();

                ArrayList<Poi> newPois = new ArrayList<Poi>(){};

                person.setCorpulence();
                newPois = poiAdapter.GetAll(person.getCorpulence());
                nbPois.setText(String.valueOf(newPois.size()));
                poiLvAdapter.updateData(newPois);
                poiLvAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECEIVE_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast toast = Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_LONG);
                    toast.show();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
