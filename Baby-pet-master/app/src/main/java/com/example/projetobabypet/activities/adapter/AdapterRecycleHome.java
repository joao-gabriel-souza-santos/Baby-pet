package com.example.projetobabypet.activities.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerHora;
import com.example.projetobabypet.model.Horas;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycleHome extends RecyclerView.Adapter<HolderRecycleHome> {

    Context context;
    public static  List<Horas> horas;
    ControllerHora controllerHora = ControllerHora.getInstancia();
    int racao_agua;

    public AdapterRecycleHome(Context context, List<Horas> horas, int racao_agua) {
        this.context = context;

        this.horas = controllerHora.buscarTodos();
        this.racao_agua = racao_agua;
    }

    @NonNull
    @Override
    public HolderRecycleHome onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HolderRecycleHome(LayoutInflater.from(context).inflate(R.layout.item_layout_recycle_view_home, viewGroup, false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecycleHome holderRecycleHome, int i) {
        horas = controllerHora.buscarTodos();
        Horas hora = horas.get(i);
        holderRecycleHome.horario.setText(hora.getHoras());

        if(racao_agua ==1) {
            holderRecycleHome.imageView.setImageResource(R.drawable.imagem_mini_pote);
        }else{
            holderRecycleHome.imageView.setImageResource(R.drawable.imagem_mini_copo);
        }
        }

    @Override
    public int getItemCount() {
        return horas.size();
    }
}
