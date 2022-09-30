package com.example.calificaciones.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbGrades extends DbHelper{

    Context context;

    public DbGrades(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertar(String name, String grade){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", name);
            values.put("promedio",grade);

            id = db.insert(TABLE_GRADES, null, values);
        } catch (Exception e){
        }
        return id;
    }

    public ArrayList<Persona> visualizar(){
        ArrayList<Persona> listaPersona = new ArrayList<>();
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Persona persona = null;
            Cursor cursor = null;

            cursor = db.rawQuery("SELECT * FROM "+ TABLE_GRADES, null);
            if (cursor.moveToFirst()){
                do{
                    persona = new Persona();
                    persona.setNombre(cursor.getString(1));
                    persona.setPromedio(cursor.getString(2));
                    listaPersona.add(persona);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e){
        }
        return listaPersona;
    }

}
