package com.example.projetobabypet.adapter.afazer;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetobabypet.R;

public class HolderAfazer extends RecyclerView.ViewHolder {
    TextView nome;
    CheckBox checkBox;
    public HolderAfazer(@NonNull View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.textView_nome_afazer_recycle);
        checkBox = itemView.findViewById(R.id.checkBox_afazer_recycle);
    }
}
