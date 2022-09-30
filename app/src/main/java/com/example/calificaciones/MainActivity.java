package com.example.calificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.calificaciones.db.DbGrades;
import com.example.calificaciones.db.DbHelper;
import com.example.calificaciones.db.Persona;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView view1, view2;
    ArrayList<Persona> lista = new ArrayList<>();
    ArrayList<String> listaNombre = new ArrayList<>();
    ArrayList<String> listaProm = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button nuevo;
        nuevo = findViewById(R.id.btn_Nuevo);
        nuevo.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, promedio.class);
            startActivity(intent);
        });

        DbGrades dbGrades = new DbGrades(MainActivity.this);
        lista = dbGrades.visualizar();

        for (int i = 0; i<lista.size();i++){
            listaNombre.add(lista.get(i).getNombre());
            listaProm.add(lista.get(i).getPromedio());
        }

        view1 = findViewById(R.id.items_nombre);
        view2 = findViewById(R.id.items_prom);


        ArrayAdapter adaptador1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaNombre);
        view1.setAdapter(adaptador1);
        ArrayAdapter adaptador2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaProm);
        view2.setAdapter(adaptador2);
    }


    }

