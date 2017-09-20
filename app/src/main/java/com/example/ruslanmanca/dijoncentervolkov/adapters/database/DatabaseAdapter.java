package com.example.ruslanmanca.dijoncentervolkov.adapters.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class DatabaseAdapter extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dijoncentervolkov.db";

    Context newContext;

    public DatabaseAdapter(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.newContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE parcours(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idCinema VARCHAR NOT NULL, " +
                "idRestaurant VARCHAR NOT NULL, " +
                "dateCreation VARCHAR NOT NULL, " +
                "dateRealisationPrevue VARCHAR NOT NULL, " +
                "accompagnant VARCHAR," +
                "idStatut FLOAT NOT NULL )");

        db.execSQL("CREATE TABLE statut(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "libelle VARCHAR NOT NULL )");

        db.execSQL("INSERT INTO \"statut\" VALUES(0,'A venir'); ");
        db.execSQL("INSERT INTO \"statut\" VALUES(1,'En cours'); ");
        db.execSQL("INSERT INTO \"statut\" VALUES(2,'Terminé'); ");
        db.execSQL("INSERT INTO \"statut\" VALUES(3,'Annulé'); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (newVersion) {
            case 2:

        }
    }
}