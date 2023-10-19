package com.example.projetobabypet.adapter.home;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerHora;
import com.example.projetobabypet.fragments.HomeFragment;

import java.util.List;

public class AdapterRecycleHome extends RecyclerView.Adapter<HolderRecycleHome> {

    Context context;
    public static  List<String> horas;

    static int racao_agua;

    public AdapterRecycleHome(Context context) {
        this.context = context;

        this.horas = HomeFragment.horas;

    }

    @NonNull
    @Override
    public HolderRecycleHome onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_recycle_view_home, viewGroup, false);
        return new HolderRecycleHome(view) ;
    }

    @Override
    public void onBindViewHolder(HolderRecycleHome holderRecycleHome, int position) {

        holderRecycleHome.horario.setText(horas.get(position).toString());

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

    public static void atualizarLista(int valor){
        horas = HomeFragment.horas;
        racao_agua = valor;
    }
}
