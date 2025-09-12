package com.example.taskmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class editarTarea extends AppCompatActivity {

    private EditText editTextNombre, editTextHoraInicio, editTextHoraTermino, editTextFecha;
    private Spinner spinnerCategorias;
    private RatingBar ratingBarPrioridad;
    private CheckBox checkBoxTerminada;
    private RadioGroup radioGroupEstado;
    private RadioButton radioPorHacer, radioEnProgreso, radioCompletada;
    private ProgressBar progressBarTarea;
    private Button btnGuardarCambios;

    // Tarea que vamos a editar
    private Tarea tarea;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarea); // tu XML de edición

        // Referencias de los elementos
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextHoraInicio = findViewById(R.id.editTextHoraInicio);
        editTextHoraTermino = findViewById(R.id.editTextHoraTermino);
        editTextFecha = findViewById(R.id.editTextFecha);
        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        ratingBarPrioridad = findViewById(R.id.ratingBarPrioridad);
        checkBoxTerminada = findViewById(R.id.checkBoxTerminada);
        radioGroupEstado = findViewById(R.id.radioGroupEstado);
        radioPorHacer = findViewById(R.id.radioPorHacer);
        radioEnProgreso = findViewById(R.id.radioEnProgreso);
        radioCompletada = findViewById(R.id.radioCompletada);
        progressBarTarea = findViewById(R.id.progressBarTarea);
        btnGuardarCambios = findViewById(R.id.btnGuardarCambios);

        // Obtener la tarea desde un intent (debe enviarse como Serializable)
        tarea = (Tarea) getIntent().getSerializableExtra("tarea");

        if (tarea != null) {
            cargarDatosTarea();
        }

        // Botón guardar cambios
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios();
            }
        });
    }

    private void cargarDatosTarea() {
        editTextNombre.setText(tarea.getNombre());
        editTextHoraInicio.setText(tarea.getHoraInicio());
        editTextHoraTermino.setText(tarea.getHoraTermino());
        editTextFecha.setText(tarea.getFecha());
        // Aquí deberías setear el spinner según la categoría actual
        ratingBarPrioridad.setRating(tarea.getPrioridad());
        checkBoxTerminada.setChecked(tarea.isTerminada());

        // RadioButtons según el estado
        if (tarea.isTerminada()) {
            radioCompletada.setChecked(true);
            progressBarTarea.setProgress(100);
        } else {
            radioPorHacer.setChecked(true);
            progressBarTarea.setProgress(0);
        }
    }

    private void guardarCambios() {
        if (tarea == null) {
            Toast.makeText(this, "Error: no se pudo cargar la tarea.", Toast.LENGTH_SHORT).show();
            return; // evita que siga y lance excepción
        }
        // Actualizar la tarea con los datos de la vista
        tarea.setNombre(editTextNombre.getText().toString());
        tarea.setHoraInicio(editTextHoraInicio.getText().toString());
        tarea.setHoraTermino(editTextHoraTermino.getText().toString());
        tarea.setFecha(editTextFecha.getText().toString());
        tarea.setPrioridad(ratingBarPrioridad.getRating());
        tarea.setTerminada(checkBoxTerminada.isChecked());

        // Actualizar el progreso según el estado
        if (radioCompletada.isChecked()) {
            progressBarTarea.setProgress(100);
        } else if (radioEnProgreso.isChecked()) {
            progressBarTarea.setProgress(50);
        } else {
            progressBarTarea.setProgress(0);
        }

        // Actualizar la lista global
        ArrayList<Tarea> lista = listaTarea.getInstance().getLista();
        for (int i = 0; i < lista.size(); i++) {
            Tarea t = lista.get(i);
            if (t.equals(tarea)) {
                listaTarea.getInstance().eliminar(tarea);
                listaTarea.getInstance().agregar(tarea);

                break;
            }
        }

        Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show();
        finish(); // cerramos la actividad y volvemos a la lista
    }
}
