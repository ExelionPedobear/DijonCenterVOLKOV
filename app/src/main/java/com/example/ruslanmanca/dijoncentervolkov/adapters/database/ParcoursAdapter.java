package com.example.ruslanmanca.dijoncentervolkov.adapters.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruslanmanca.dijoncentervolkov.models.database.Parcours;
import com.example.ruslanmanca.dijoncentervolkov.models.database.Statut;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class ParcoursAdapter {
    public static final String TABLE = "parcours";

    private SQLiteDatabase db = null;

    public ParcoursAdapter(SQLiteDatabase db) {
        this.db = db;
    }

    public ArrayList<Parcours> getAll(){
        Cursor cursor = db.query(TABLE, //table
                new String[]{"id", "idCinema", "idRestaurant", "dateCreation", "dateRealisationPrevue", "accompagnant", "idStatut"}, //colonnes
                null, //selection (where)
                null, //paramètres de sélection
                null, //order
                null, //group
                null); //limit

        ArrayList<Parcours> parcours = new ArrayList<Parcours>();
        parcours = this.fillParcoursList(cursor);

        return parcours;
    }

    public long insert(Parcours parcours){
        long id = -2;
        try {

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            String formattedDate = df.format(c.getTime());

            ContentValues contentValue = new ContentValues();
            contentValue.put("idCinema", parcours.getIdCinema());
            contentValue.put("idRestaurant", parcours.getIdRestaurant());
            contentValue.put("dateCreation", formattedDate);
            contentValue.put("dateRealisationPrevue", parcours.getDateRealisation());
            contentValue.put("accompagnant", parcours.getAccompagnant());
            contentValue.put("idStatut", parcours.getIdStatut());

            id = db.insert(TABLE, null, contentValue);

            return id;
        }
        catch(Exception ex){
            return id;
        }
    }

    private ArrayList<Parcours> fillParcoursList(Cursor cursor){
        ArrayList<Parcours> result = new ArrayList<Parcours>();
        StatutAdapter sa = new StatutAdapter(db);
        while (cursor.moveToNext()){
            Parcours parcours = new Parcours();
            int idColumnIndex = cursor.getColumnIndex("id");
            int idCinemaColumnIndex = cursor.getColumnIndex("idCinema");
            int idRestaurantColumnIndex = cursor.getColumnIndex("idRestaurant");
            int dateCreationColumnIndex = cursor.getColumnIndex("dateCreation");
            int dateRealisationPrevueColumnIndex = cursor.getColumnIndex("dateRealisationPrevue");
            int accompagantColumnIndex = cursor.getColumnIndex("accompagnant");
            int idStatutColumnIndex = cursor.getColumnIndex("idStatut");

            parcours.setId(cursor.getLong(idColumnIndex));
            parcours.setIdCinema(cursor.getString(idCinemaColumnIndex));
            parcours.setIdRestaurant(cursor.getString(idRestaurantColumnIndex));
            parcours.setDateCreation(cursor.getString(dateCreationColumnIndex));
            parcours.setDateRealisation(cursor.getString(dateRealisationPrevueColumnIndex));
            parcours.setAccompagnant(cursor.getString(accompagantColumnIndex));
            parcours.setIdStatut(cursor.getLong(idStatutColumnIndex));

            parcours.setStatut(sa.getById(parcours.getIdStatut()));

            result.add(parcours);
        }

        return result;
    }
}
