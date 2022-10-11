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
    TextView txt_prom;
    EditText name, grade, Descripcion;
    Button add, delete, upload;
    ArrayList<Integer> grades = new ArrayList<Integer>();
    ArrayList<String> descripcion = new ArrayList<String>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promedio);

        txt_prom = findViewById(R.id.txt_prom);
        name = findViewById(R.id.name);
        grade = findViewById(R.id.grade);
        add = findViewById(R.id.btn_add);
        delete = findViewById(R.id.btn_delete);
        upload = findViewById(R.id.btn_upload);
        Descripcion = findViewById(R.id.desc);

        add.setOnClickListener(view -> {
            if (grade.getText().toString().equals("") || Descripcion.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese una calificacion", Toast.LENGTH_LONG).show();
                return;
            }
            grades.add(Integer.parseInt(grade.getText().toString()));
            descripcion.add(Descripcion.getText().toString());
            txt_prom.setText(String.valueOf(promediar(Integer.parseInt(grade.getText().toString()), grades.size())));
            grade.setText("");
            Descripcion.setText("");
        });

        delete.setOnClickListener(view -> {
            delete();
        });

        upload.setOnClickListener(view -> {
            if (name.getText().toString().equals("") || txt_prom.getText().toString().equals("")) {
                Toast.makeText(this, "Ingrese su nombre o añada una calificacion al promedio", Toast.LENGTH_LONG).show();
                return;
            } else {
                DbGrades dbGrades = new DbGrades(promedio.this);
                long id = dbGrades.insertarPromedio(name.getText().toString(), String.valueOf(promediar(0,grades.size())));
                for (int i = 0; i < descripcion.size(); i++) {
                    long idD = dbGrades.insertarDescripcion((int) id ,grades.get(i).toString(), descripcion.get(i));
                }
                if (id > 0){
                    Toast.makeText(this, "Registro guardado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Registro no guardado", Toast.LENGTH_LONG).show();
                }
                delete();
            }
            Intent intent = new Intent(promedio.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public int promediar(int nota, int cant) {
        tot = tot + nota;
        return (int)tot/cant;
    }

    public void delete(){
        txt_prom.setText("");
        grades.clear();
        descripcion.clear();
        tot = 0;
        grade.setText("");
        name.setText("");
        Descripcion.setText("");
    }

}