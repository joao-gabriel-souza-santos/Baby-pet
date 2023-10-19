package com.example.projetobabypet.adapter.hora;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerHora;
import com.example.projetobabypet.interfaces.RecyclerClickHora;
import com.example.projetobabypet.model.Compromisso;

import java.util.List;

public class AdapterListaHora extends RecyclerView.Adapter<HoraHolder> {

    private final RecyclerClickHora recyclerClickHora;
    Context context;
    public  static List<Compromisso> compromissos;
    ControllerHora c;
    int idUsuario;

    public AdapterListaHora(Context context, int idUsuario, RecyclerClickHora recyclerClickHora){
        this.context =context;
        this.idUsuario= idUsuario;
        this.recyclerClickHora = recyclerClickHora;
        c= ControllerHora.getInstance(context);
        compromissos= c.buscar_compromissos(idUsuario);
    }

    @NonNull
    @Override
    public HoraHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_hora, parent, false);
        return new HoraHolder(view, recyclerClickHora);
    }

    @Override
    public void onBindViewHolder(@NonNull HoraHolder holder, int position) {
        Compromisso compromisso = compromissos.get(position);
        holder.hora.setText(compromisso.getHora());

        holder.cardView.setOnClickListener(view -> {
            recyclerClickHora.onClick(compromissos.get(position));
        });

        holder.itemView.setOnLongClickListener(view -> {
            ControllerHora c = ControllerHora.getInstance(context);
            c.deletar(compromissos.get(position));
            atualizarLista();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return compromissos.size();
    }

    public void atualizarLista(){
        compromissos.clear();
        c= ControllerHora.getInstance(context);
        compromissos.addAll(c.buscar_compromissos(idUsuario));
        notifyDataSetChanged();
    }}
