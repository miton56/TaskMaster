package com.example.taskmaster;

public class Tarea {

    private String categoria;

    private float prioridad;

    private String nombre;

    private String horaInicio;

    private String horaTermino;

    private String fecha;

    public Tarea(String Categoria, float Prioridad, String Nombre, String HoraInicio, String HoraTermino, String Fecha){

        this.categoria = Categoria;

        this.prioridad = Prioridad;

        this.nombre = Nombre;

        this.horaInicio = HoraInicio;

        this.horaTermino = HoraTermino;

        this.fecha = Fecha;

    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(float prioridad) {
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
