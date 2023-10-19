package com.example.projetobabypet.adapter.hora;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetobabypet.R;
import com.example.projetobabypet.interfaces.RecyclerClickHora;

public class HoraHolder extends RecyclerView.ViewHolder {

    ImageView pote_agua;
    TextView hora;
    CardView cardView;

    public HoraHolder(@NonNull View itemView, RecyclerClickHora recyclerClickHora) {
        super(itemView);

        pote_agua = itemView.findViewById(R.id.imageView_pote_agua_hora);
        hora = itemView.findViewById(R.id.textView_hora_cadastrar);
        cardView = itemView.findViewById(R.id.cardview_recycler_hora);
    }
}
