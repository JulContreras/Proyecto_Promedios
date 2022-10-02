package com.example.calificaciones.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "promedio.db";
    public static final String TABLE_GRADES = "t_grades";
    public static final String TABLE_DESC = "t_desc";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_GRADES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL,"+
                "promedio TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DESC + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "idStudent INTEGER NOT NULL,"+
                "grade TEXT NOT NULL,"+
                "description TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_GRADES);
        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_DESC);
        onCreate(sqLiteDatabase);
    }
}
