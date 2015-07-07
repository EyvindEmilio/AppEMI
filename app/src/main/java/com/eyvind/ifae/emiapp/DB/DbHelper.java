
package com.eyvind.ifae.emiapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eyvind.ifae.emiapp.DB.Contract.EstudiantesEntry;
import com.eyvind.ifae.emiapp.DB.Contract.CuentaEntry;
import com.eyvind.ifae.emiapp.GLOBALS;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, GLOBALS.DATABASE_NAME, null, GLOBALS.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_ESTUDIANTES = "CREATE TABLE " + EstudiantesEntry.TABLE_NAME + " (" +
                EstudiantesEntry.COLUMN_ID_CARNET + " INTEGER PRIMARY KEY," +
                EstudiantesEntry.COLUMN_FULL_NAME + " TEXT UNIQUE NOT NULL, " +
                EstudiantesEntry.COLUMN_SEMESTRE + " TEXT NOT NULL, " +
                EstudiantesEntry.COLUMN_CARRERA + " TEXT NOT NULL, " +
                EstudiantesEntry.COLUMN_DIRECCION + " TEXT NOT NULL " +
                EstudiantesEntry.COLUMN_CORREO + " TEXT NOT NULL " +
                " );";
        final String CREATE_CUENTA = "CREATE TABLE " + CuentaEntry.TABLE_NAME + " (" +
                CuentaEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                CuentaEntry.COLUMN_FULL_NAME + " TEXT UNIQUE NOT NULL, " +
                CuentaEntry.COLUMN_SEMESTRE + " TEXT NOT NULL, " +
                CuentaEntry.COLUMN_CARRERA + " TEXT NOT NULL, " +
                CuentaEntry.COLUMN_DIRECCION + " TEXT NOT NULL " +
                CuentaEntry.COLUMN_CORREO + " TEXT NOT NULL " +
                " );";
        sqLiteDatabase.execSQL(CREATE_ESTUDIANTES);
        sqLiteDatabase.execSQL(CREATE_CUENTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EstudiantesEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CuentaEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
