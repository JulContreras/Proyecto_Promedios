package com.example.calificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calificaciones.db.DbGrades;

import java.util.ArrayList;

public class promedio extends AppCompatActivity {

    double tot=0;
    ArrayList<Double> grades = new ArrayList<Double>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promedio);
        TextView txt_prom;
        EditText name, grade;
        Button add, delete, upload;
        txt_prom = findViewById(R.id.txt_prom);
        name = findViewById(R.id.name);
        grade = findViewById(R.id.grade);
        add = findViewById(R.id.btn_add);
        delete = findViewById(R.id.btn_delete);
        upload = findViewById(R.id.btn_upload);

        add.setOnClickListener(view -> {
            if (grade.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese una calificacion", Toast.LENGTH_LONG).show();
                return;
            }
            grades.add(Double.parseDouble(grade.getText().toString()));
            txt_prom.setText(String.valueOf(promediar(Double.parseDouble(grade.getText().toString()), grades.size())));
            grade.setText("");
        });

        delete.setOnClickListener(view -> {
            txt_prom.setText("");
            grades.clear();
            tot = 0;
            grade.setText("");
        });

        upload.setOnClickListener(view -> {
            if (name.getText().toString().equals("") && txt_prom.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese una calificacion y nombre", Toast.LENGTH_LONG).show();
                return;
            } else {
                DbGrades dbGrades = new DbGrades(promedio.this);
                long id = dbGrades.insertar(name.getText().toString(), String.valueOf(promediar(0,grades.size())));

                if (id>0){
                    Toast.makeText(this, "Registro guardado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Registro no guardado", Toast.LENGTH_LONG).show();
                }
                txt_prom.setText("");
                grades.clear();
                tot = 0;
                grade.setText("");
                name.setText("");
            }
            Intent intent = new Intent(promedio.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public double promediar(double nota, int cant) {
        tot = tot + nota;
        double promedio = tot/cant;
        return promedio;
    }





}