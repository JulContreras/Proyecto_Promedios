package com.example.calificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.calificaciones.db.DbGrades;
import com.example.calificaciones.entidades.Descripcion;

import java.util.ArrayList;

public class VerDescripciones extends AppCompatActivity {
    RecyclerView listaDescripcion;
    int id = 0;
    ArrayList<Descripcion> listaDesc = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_descripciones);
        listaDescripcion = findViewById(R.id.listaDescripcion);
        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbGrades dbGrades = new DbGrades(VerDescripciones.this);
        listaDescripcion.setLayoutManager(new LinearLayoutManager(this));
        AdaptadorDescripcion adapter = new AdaptadorDescripcion(dbGrades.visualizarDesc(id));
        listaDescripcion.setAdapter(adapter);

    }

    }
