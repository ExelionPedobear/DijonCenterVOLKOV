package com.example.ruslanmanca.dijoncentervolkov.adapters.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruslanmanca.dijoncentervolkov.models.database.Statut;

import org.w3c.dom.Comment;

import java.util.ArrayList;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class StatutAdapter {
    public static final String TABLE = "statut";

    private SQLiteDatabase db = null;

    public StatutAdapter(SQLiteDatabase db) {
        this.db = db;
    }

    public ArrayList<Statut> getAll(){
        Cursor cursor = db.query(TABLE, //table
                new String[]{"id", "libelle"}, //colonnes
                null, //selection (where)
                null, //paramètres de sélection
                null, //order
                null, //group
                null); //limit

        ArrayList<Statut> statuts = new ArrayList<Statut>();
        statuts = this.fillStatutList(cursor);

        return statuts;
    }

    public Statut getById(long id){
        Cursor cursor = db.query(TABLE, //table
                new String[]{"id", "libelle"}, //colonnes
                "id LIKE ?", //selection (where)
                new String[]{String.valueOf(id)}, //paramètres de sélection
                null, //order
                null, //group
                null); //limit

        Statut statut = new Statut();

        if (cursor.moveToFirst()){
            statut.setId(cursor.getLong(0));
            statut.setLibelle(cursor.getString(1));
        }

        cursor.close();

        return statut;
    }

    private ArrayList<Statut> fillStatutList(Cursor cursor){
        ArrayList<Statut> statuts = new ArrayList<Statut>();
        while (cursor.moveToNext()){
            Statut statut = new Statut();
            int idColumnIndex = cursor.getColumnIndex("id");
            int libelleColumnIndex = cursor.getColumnIndex("libelle");

            statut.setId(cursor.getLong(idColumnIndex));
            statut.setLibelle(cursor.getString(libelleColumnIndex));

            statuts.add(statut);
        }

        return statuts;
    }
}
