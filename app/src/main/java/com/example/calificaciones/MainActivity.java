package com.example.calificaciones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.calificaciones.db.DbGrades;
import com.example.calificaciones.entidades.Persona;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaPromedio;
    ArrayList<Persona> lista = new ArrayList<>();
    ArrayList<String> listaNombre = new ArrayList<>();
    ArrayList<String> listaProm = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button nuevo, limpiar;
        nuevo = findViewById(R.id.btn_Nuevo);
        limpiar = findViewById(R.id.btn_Limpiar);
        listaPromedio = findViewById(R.id.listaProm);
        DbGrades dbGrades = new DbGrades(MainActivity.this);

        nuevo.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, promedio.class);
            startActivity(intent);
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿Desea eliminar TODOS los promedios?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbGrades.borrarTabla();
                                visualizarPromedio();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                return;
                            }
                        }).show();
            }
        });

        visualizarPromedio();

    }

    public void vaciarArray(){
        listaNombre.clear();
        listaProm.clear();
    }

    //listaNombre.add(lista.get(i).getNombre());
    //listaProm.add(lista.get(i).getPromedio());

    public void visualizarPromedio(){
        DbGrades dbGrades = new DbGrades(MainActivity.this);
        listaPromedio.setLayoutManager(new LinearLayoutManager(this));
        AdapatadorPersona adapter = new AdapatadorPersona(dbGrades.visualizarProm());
        listaPromedio.setAdapter(adapter);
    }


    }

