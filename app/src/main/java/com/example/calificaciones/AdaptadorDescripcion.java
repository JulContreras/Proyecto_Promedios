package com.example.calificaciones;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calificaciones.entidades.Descripcion;
import com.example.calificaciones.entidades.Persona;

import java.util.ArrayList;

public class AdaptadorDescripcion extends RecyclerView.Adapter<AdaptadorDescripcion.PromedioViewHolder>{

    ArrayList<Descripcion> listaDesc;

    public AdaptadorDescripcion(ArrayList<Descripcion> listaDesc){
        this.listaDesc = listaDesc;
    }

    @NonNull
    @Override
    public AdaptadorDescripcion.PromedioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_descripcion, null, false);
        return new AdaptadorDescripcion.PromedioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDescripcion.PromedioViewHolder holder, int position) {
        holder.viewDesc.setText(listaDesc.get(position).getDesc());
        holder.viewGrade.setText(listaDesc.get(position).getGrade());
    }

    @Override
    public int getItemCount() {
        return listaDesc.size();
    }

    public class PromedioViewHolder extends RecyclerView.ViewHolder {
        TextView viewDesc, viewGrade;
        public PromedioViewHolder(@NonNull View itemView) {
            super(itemView);
            viewDesc = itemView.findViewById(R.id.viewDesc);
            viewGrade = itemView.findViewById(R.id.viewGrade);
        }
    }
}
