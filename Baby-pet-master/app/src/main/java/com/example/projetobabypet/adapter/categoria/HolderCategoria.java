package com.example.projetobabypet.adapter.categoria;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetobabypet.R;

public class HolderCategoria extends RecyclerView.ViewHolder {

    ImageButton botao;
    TextView nome;
    RecyclerView recycler;
    public HolderCategoria(@NonNull View itemView) {
        super(itemView);

        botao = itemView.findViewById(R.id.imageButton_cadastrar_afazer_categoria_saude);
        nome = itemView.findViewById(R.id.textView_categoria_saude);
        recycler = itemView.findViewById(R.id.recycler__view_afazeres_categoria);
    }
}
