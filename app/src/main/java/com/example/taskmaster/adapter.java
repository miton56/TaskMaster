package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MiViewHolder>{

    private List<Tarea> tareas;

    private listaTarea tareasBase = listaTarea.getInstance();

    public adapter(){
        this.tareas = tareasBase.getLista();
    }

    public static class MiViewHolder extends RecyclerView.ViewHolder{

        ImageView ivTarea;

        TextView tvNombre, tvCategoria, tvPrioridad, tvFecha, tvHoraInicio, tvHoraTermino;

        public MiViewHolder(View itemView){
            super(itemView);

            ivTarea = itemView.findViewById(R.id.ivTarea);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCategoria = itemView.findViewById(R.id.tvCategoria);
            tvPrioridad = itemView.findViewById(R.id.tvPrioridad);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvHoraInicio = itemView.findViewById(R.id.tvHoraInicio);
            tvHoraTermino = itemView.findViewById(R.id.tvHoraTermino);

        }

    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tarea_layout, parent, false);
        return new MiViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        Tarea tarea = tareas.get(position);


        holder.tvNombre.setText(tarea.getNombre());
        holder.tvCategoria.setText(tarea.getCategoria());
        holder.tvPrioridad.setText("Prioridad: " + tarea.getPrioridad());
        holder.tvFecha.setText(tarea.getFecha());
        holder.tvHoraInicio.setText("Inicio: " + tarea.getHoraInicio());
        holder.tvHoraTermino.setText("Termino: " + tarea.getHoraTermino());


        holder.ivTarea.setImageResource(R.drawable.lofigirl);
    }

    @Override
    public int getItemCount() {
        return this.tareas.size();
    }
}
