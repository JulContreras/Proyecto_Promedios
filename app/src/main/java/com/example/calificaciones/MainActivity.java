package com.example.calificaciones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.calificaciones.db.DbGrades;

public class MainActivity extends AppCompatActivity {
    RecyclerView listaPromedio;
    Button nuevo, limpiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nuevo = findViewById(R.id.btn_Nuevo);
        limpiar = findViewById(R.id.btn_Limpiar);
        listaPromedio = findViewById(R.id.listaProm);

        visualizarPromedio();

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿Desea eliminar TODOS los promedios?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DbGrades dbGrades = new DbGrades(MainActivity.this);
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

        nuevo.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, promedio.class);
            startActivity(intent);
        });
    }

    public void visualizarPromedio(){
        DbGrades dbGrades = new DbGrades(MainActivity.this);
        listaPromedio.setLayoutManager(new LinearLayoutManager(this));
        AdaptadorPersona adapter = new AdaptadorPersona(dbGrades.visualizarProm());
        listaPromedio.setAdapter(adapter);
    }

    }

