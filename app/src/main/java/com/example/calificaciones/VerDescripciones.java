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
import com.example.calificaciones.entidades.Descripcion;
import java.util.ArrayList;

public class VerDescripciones extends AppCompatActivity {

    RecyclerView listaDescripcion;
    int id = 0;
    Button eliminar;
    ArrayList<Descripcion> listaDesc = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_descripciones);

        listaDescripcion = findViewById(R.id.listaDescripcion);
        eliminar = findViewById(R.id.btn_eliminarProm);

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

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerDescripciones.this);
                builder.setMessage("¿Desea eliminar TODOS los promedios?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DbGrades dbGrades = new DbGrades(VerDescripciones.this);
                                dbGrades.eliminar(id);
                                Intent intent = new Intent(VerDescripciones.this, MainActivity.class);
                                startActivity(intent);
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

        visualizarDescripcion();

    }

    public void visualizarDescripcion(){
        DbGrades dbGrades = new DbGrades(VerDescripciones.this);
        listaDescripcion.setLayoutManager(new LinearLayoutManager(this));
        AdaptadorDescripcion adapter = new AdaptadorDescripcion(dbGrades.visualizarDesc(id));
        listaDescripcion.setAdapter(adapter);
    }

}
