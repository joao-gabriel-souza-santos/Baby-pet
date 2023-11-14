package com.example.projetobabypet.adapter.home;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerRacaoAgua;
import com.example.projetobabypet.fragments.HomeFragment;
import com.example.projetobabypet.model.RacaoEAgua;

import java.util.List;

public class AdapterRecycleHome extends RecyclerView.Adapter<HolderRecycleHome> {

    Context context;


    public List<RacaoEAgua> racaoEAguas;
    String email;

    public AdapterRecycleHome(Context context, String email) {
        this.context = context;
        this.email = email;
        ControllerRacaoAgua c = ControllerRacaoAgua.getInstancia(context);
        racaoEAguas = c.buscar(email);
    }

    @NonNull
    @Override
    public HolderRecycleHome onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_recycle_view_home, viewGroup, false);
        return new HolderRecycleHome(view) ;
    }

    @Override
    public void onBindViewHolder(HolderRecycleHome holderRecycleHome, int position) {

        RacaoEAgua racaoEAgua = racaoEAguas.get(position);

        holderRecycleHome.horario.setText(racaoEAgua.getHora());
        if(racaoEAgua.getAguaouracao().equals("Racao")) {
            holderRecycleHome.imageView.setImageResource(R.drawable.imagem_mini_pote);
        }else{
            holderRecycleHome.imageView.setImageResource(R.drawable.imagem_mini_copo);
        }
        }

    @Override
    public int getItemCount() {
        return racaoEAguas.size();
    }

    public static void atualizarLista(int valor){

    }
}
