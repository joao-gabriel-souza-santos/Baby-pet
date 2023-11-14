package com.example.projetobabypet.adapter.hora;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerCompromisso;
import com.example.projetobabypet.interfaces.RecyclerClickHora;
import com.example.projetobabypet.model.Compromisso;

import java.util.List;

public class AdapterListaHora extends RecyclerView.Adapter<HoraHolder> {

    private final RecyclerClickHora recyclerClickHora;
    Context context;
    public  static List<Compromisso> compromissos;
    ControllerCompromisso c;
    String email_usuario;

    public AdapterListaHora(Context context, String email_usuario, RecyclerClickHora recyclerClickHora){
        this.context =context;
        this.email_usuario = email_usuario;
        this.recyclerClickHora = recyclerClickHora;
        c= ControllerCompromisso.getInstance(context);
        compromissos= c.buscar_compromissos(email_usuario);
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
        if(compromisso.getDescricao().equals("Ração") || compromisso.getDescricao().equals("Agua")){
            holder.hora.setText(compromisso.getHora());

            if(compromisso.getDescricao().equals("Ração")){
                holder.pote_agua.setImageResource(R.drawable.tigelabranca);
            } else {
                holder.pote_agua.setImageResource(R.drawable.copoaguabranco);
            }

            holder.cardView.setOnClickListener(view -> {
                recyclerClickHora.onClick(compromissos.get(position));
            });

            holder.itemView.setOnLongClickListener(view -> {
                ControllerCompromisso c = ControllerCompromisso.getInstance(context);
                c.deletar(compromissos.get(position));
                atualizarLista();
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return compromissos.size();
    }

    public void atualizarLista(){
        compromissos.clear();
        c= ControllerCompromisso.getInstance(context);
        compromissos.addAll(c.buscar_compromissos(email_usuario));
        notifyDataSetChanged();
    }}
