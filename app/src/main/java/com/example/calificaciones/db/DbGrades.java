package com.example.calificaciones.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.calificaciones.entidades.Descripcion;
import com.example.calificaciones.entidades.Persona;

import java.util.ArrayList;

public class DbGrades extends DbHelper{

    Context context;

    public DbGrades(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPromedio(String name, String grade){
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

    public long insertarDescripcion(int ID,String grade, String descripcion){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("idStudent", ID);
            values.put("grade", grade);
            values.put("description", descripcion);

            id = db.insert(TABLE_DESC, null, values);
        } catch (Exception e){
        }
        return id;
    }

    public ArrayList<Persona> visualizarProm(){
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
                    persona.setID(cursor.getInt(0));
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

    public ArrayList<Descripcion> visualizarDesc(int id){
        ArrayList<Descripcion> listaDescripcion = new ArrayList<>();
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Descripcion descripcion = null;
            Cursor cursor = null;

            cursor = db.rawQuery("SELECT * FROM " + TABLE_DESC + " WHERE idStudent = '" + id + "'", null);
            if (cursor.moveToFirst()){
                do{
                    descripcion = new Descripcion();
                    descripcion.setGrade(cursor.getString(2));
                    descripcion.setDesc(cursor.getString(3));
                    listaDescripcion.add(descripcion);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e){
        }
        return listaDescripcion;
    }

    public boolean eliminar(int id){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{
            db.execSQL("DELETE FROM " + TABLE_GRADES + " WHERE id = '" + id + "' LIMIT 1" );
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    public boolean borrarTabla(){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{
            db.execSQL("DELETE FROM " + TABLE_GRADES );
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

}
