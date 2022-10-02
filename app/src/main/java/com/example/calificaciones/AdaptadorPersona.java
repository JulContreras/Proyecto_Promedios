package com.example.calificaciones;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calificaciones.entidades.Persona;

import java.util.ArrayList;

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.PromedioViewHolder> {

    ArrayList<Persona> listaPersona;

    public AdaptadorPersona(ArrayList<Persona> listaPersona){
        this.listaPersona = listaPersona;
    }

    @NonNull
    @Override
    public PromedioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_prom, null, false);
        return new PromedioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromedioViewHolder holder, int position) {
        holder.viewNombre.setText(listaPersona.get(position).getNombre());
        holder.viewProm.setText(listaPersona.get(position).getPromedio());
    }

    @Override
    public int getItemCount() {
        return listaPersona.size();
    }

    public class PromedioViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewProm;

        public PromedioViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewProm = itemView.findViewById(R.id.viewProm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerDescripciones.class);
                    intent.putExtra("ID", listaPersona.get(getAdapterPosition()).getID());
                    context.startActivity(intent);
                }
            });
        }
    }
}
