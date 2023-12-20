package com.example.proyecto2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbContext extends SQLiteOpenHelper {
    private static final String DB_NAME = "DataBase_Proyecto2";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Usuario";
    private static final String ID_COL = "id";
    private static final String NOMBRE_COL = "Nombre";
    private static final String CORREO_COL = "Correo";
    private static final String PASS_COL = "Contrasena";

    public dbContext(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOMBRE_COL + " TEXT NOT NULL,"
                + CORREO_COL + " TEXT NOT NULL,"
                + PASS_COL + " TEXT NOT NULL)";
        db.execSQL(query);
    }
    public void agregarUsuario(String nombre,String correo, String Pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE_COL, nombre);
        values.put(CORREO_COL, correo);
        values.put(PASS_COL, Pass);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

