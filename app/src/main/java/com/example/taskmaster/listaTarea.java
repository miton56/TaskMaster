package com.example.taskmaster;

import java.util.ArrayList;

public class listaTarea {
    private static listaTarea instancia;
    private ArrayList<Tarea> lista;

    private listaTarea() {
        lista = new ArrayList<>();
}

public static synchronized listaTarea getInstance() {
        if (instancia == null) {
            instancia = new listaTarea();
        }
        return instancia;
    }

    public void agregar(Tarea tarea) {
        lista.add(tarea);
    }

    public void eliminar(Tarea tarea) {
        lista.remove(tarea);
    }

    public ArrayList<Tarea> getLista() {
        return lista;
    }
}