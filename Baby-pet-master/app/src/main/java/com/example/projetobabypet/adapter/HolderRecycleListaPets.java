package com.example.projetobabypet.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetobabypet.R;

public class HolderRecycleListaPets extends RecyclerView.ViewHolder {

    TextView textView_nome_pet;
    TextView textView_raca_pet;
    ImageView foto_pet;

    public HolderRecycleListaPets(@NonNull View itemView) {
        super(itemView);
        textView_nome_pet = itemView.findViewById(R.id.textView_nome_pet);
        textView_raca_pet = itemView.findViewById(R.id.textView_raca_pet);
        foto_pet = itemView.findViewById(R.id.imageView_foto_pet);


    }
}
