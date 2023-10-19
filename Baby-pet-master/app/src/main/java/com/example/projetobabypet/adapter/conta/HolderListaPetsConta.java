package com.example.projetobabypet.adapter.conta;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetobabypet.R;
import com.example.projetobabypet.interfaces.RecyclerClickPetConta;

public class HolderListaPetsConta extends RecyclerView.ViewHolder {

    TextView nome,sexo,  raca, idade;
    ImageView imag;
    CardView cardView;

    public HolderListaPetsConta(@NonNull View itemView, RecyclerClickPetConta recyclerClickPetConta) {
        super(itemView);
        nome = itemView.findViewById(R.id.textView_conta_nome);
        sexo = itemView.findViewById(R.id.textView_conta_sexo);

        raca = itemView.findViewById(R.id.textView_conta_raca);
        idade = itemView.findViewById(R.id.textView_conta_idade);
        imag = itemView.findViewById(R.id.imagemPet);

        cardView = itemView.findViewById(R.id.cardview_recycleview_conta_pet);


    }
}
