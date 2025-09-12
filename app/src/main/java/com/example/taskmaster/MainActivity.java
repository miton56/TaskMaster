package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    listaTarea tareas = listaTarea.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("configuracion", MODE_PRIVATE);
        String tema = prefs.getString("tema", "claro");

        if (tema.equals("claro")) {
            setTheme(R.style.Theme_TaskMaster_Light);
        } else {
            setTheme(R.style.Theme_TaskMaster_Dark);
        }

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Tarea tareaInit = new Tarea("Trabajo", 5, "Terminar informe", "09:00", "11:00", "11/09/2025");

        tareas.agregar(tareaInit);

        Button botonCrearTareas = findViewById(R.id.btnCrearTarea);
        Button botonAlarma = findViewById(R.id.btnCambiarAlarma);
        Button botonVerTareas = findViewById(R.id.btnVerTarea);

        botonCrearTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, crearTareas.class);
                startActivity(intent);
            }
        });

        botonAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, configuracion.class);
                startActivity(intent);
            }
        });

        botonVerTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, verTareas.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}