package com.example.projetobabypet.adapter.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetobabypet.R;

public class HolderRecycleHome extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView horario;

    public HolderRecycleHome(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageViewPoteCopo);
        horario = itemView.findViewById(R.id.textView_horario);
    }
}
