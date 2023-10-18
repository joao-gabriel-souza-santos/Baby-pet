package com.example.projetobabypet.adapter.hora;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetobabypet.R;

public class HoraHolder extends RecyclerView.ViewHolder {

    ImageView pote_agua;
    TextView hora;

    public HoraHolder(@NonNull View itemView) {
        super(itemView);

        pote_agua = itemView.findViewById(R.id.imageView_pote_agua_hora);
        hora = itemView.findViewById(R.id.textView_hora_cadastrar);
    }
}
