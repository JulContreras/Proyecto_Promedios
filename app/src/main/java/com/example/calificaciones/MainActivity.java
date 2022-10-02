package com.example.calificaciones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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
        Button nuevo, limpiar;
        nuevo = findViewById(R.id.btn_Nuevo);
        limpiar = findViewById(R.id.btn_Limpiar);
        view1 = findViewById(R.id.items_nombre);
        view2 = findViewById(R.id.items_prom);

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

    public void visualizarPromedio(){
        DbGrades dbGrades = new DbGrades(MainActivity.this);
        lista = dbGrades.visualizarProm();

        vaciarArray();

        for (int i = 0; i<lista.size();i++){
            listaNombre.add(lista.get(i).getNombre());
            listaProm.add(lista.get(i).getPromedio());
        }

        ArrayAdapter adaptador1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaNombre);
        view1.setAdapter(adaptador1);
        ArrayAdapter adaptador2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaProm);
        view2.setAdapter(adaptador2);
    }


    }

