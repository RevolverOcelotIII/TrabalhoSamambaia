package com.example.trabalho_samambaia.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlantaDAO extends SQLiteOpenHelper {
    private static final String DATABASE = "samambaia";
    private static final int VERSAO = 1;

    public PlantaDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE Planta (id INTEGER PRIMARY KEY,"
                + " nome TEXT UNIQUE NOT NULL);";


        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
