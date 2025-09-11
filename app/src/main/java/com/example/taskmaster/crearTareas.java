package com.example.taskmaster;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Locale;

public class crearTareas extends AppCompatActivity {

    private listaTarea tareas;

    private Spinner listaCat;

    private RatingBar prioridad;

    private EditText horaInicio;

    private EditText horaTermino;

    private EditText fecha;

    private EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_tareas);

        String[] categorias = {"escuela", "trabajo", "casa"};

        this.listaCat = findViewById(R.id.listaCategorias);

        this.prioridad = findViewById(R.id.prioridadBar);

        this.horaInicio = findViewById(R.id.horaInitEntry);

        this.horaTermino = findViewById(R.id.horaEndEntry);

        this.fecha = findViewById(R.id.fechaEntry);

        this.nombre = findViewById(R.id.nombreEntry);

        this.tareas = listaTarea.getInstance();

        this.horaInicio.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hora = calendar.get(Calendar.HOUR_OF_DAY);
            int minuto = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePicker = new TimePickerDialog(this,
                    (view, h, m) -> {
                        this.horaInicio.setText(String.format(Locale.getDefault(), "%02d:%02d", h, m));
                    }, hora, minuto, true);
            timePicker.show();
        });

        this.horaTermino.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hora = calendar.get(Calendar.HOUR_OF_DAY);
            int minuto = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePicker = new TimePickerDialog(this,
                    (view, h, m) -> {
                        this.horaTermino.setText(String.format(Locale.getDefault(), "%02d:%02d", h, m));
                    }, hora, minuto, true);
            timePicker.show();
        });

        this.fecha.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            int mes = calendar.get(Calendar.MONTH);
            int anio = calendar.get(Calendar.YEAR);

            DatePickerDialog datePicker = new DatePickerDialog(this,
                    (view, year, month, dayOfMonth) -> {

                        this.fecha.setText(String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year));
                    }, anio, mes, dia);
            datePicker.show();
        });

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categorias
        );

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listaCat.setAdapter(adaptador);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void insertarTarea(View view){

        Tarea nuevaTarea = new Tarea(
            this.listaCat.getSelectedItem().toString(),
            this.prioridad.getRating(),
            this.nombre.getText().toString(),
            this.horaInicio.getText().toString(),
            this.horaTermino.getText().toString(),
            this.fecha.getText().toString()
        );

        this.tareas.agregar(nuevaTarea);

        Toast.makeText(this, "tarea agregada", Toast.LENGTH_SHORT).show();
    }

}