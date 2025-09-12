package com.example.taskmaster;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class verTareas extends AppCompatActivity {

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
        setContentView(R.layout.activity_ver_tareas);

        getSupportActionBar().setTitle("Tareas");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaTarea tareas = listaTarea.getInstance();

        RecyclerView recyclerView = findViewById(R.id.ReciclerViewTareas);
        adapter Adapter = new adapter();
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}