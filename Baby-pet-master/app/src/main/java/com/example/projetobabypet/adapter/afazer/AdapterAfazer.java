package com.example.projetobabypet.adapter.afazer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerCompromisso;
import com.example.projetobabypet.model.Compromisso;

import java.util.List;

public class AdapterAfazer extends RecyclerView.Adapter<HolderAfazer> {

    private Context context;
    private List<Compromisso> compromissos;
    private ControllerCompromisso c;
    int id_categoria;
    public AdapterAfazer(Context context, int id_categoria){
        this.context = context;
        this.id_categoria = id_categoria;
         c = ControllerCompromisso.getInstance(context);
        compromissos = c.buscar_compromissos_categoria(id_categoria);
    }
    @Override
    public HolderAfazer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_afazer, parent, false);
        return new HolderAfazer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAfazer holder, int position) {
        Compromisso compromisso = compromissos.get(position);
        holder.nome.setText(compromisso.getNome());
        boolean check = holder.checkBox.isChecked();


        holder.checkBox.setOnClickListener(view -> {
            boolean checks = ((CheckBox)view).isChecked();

            if(checks){
                    c.deletar(compromisso);
                    atualizarLista();
                }
        });

    }

    @Override
    public int getItemCount() {
        return compromissos.size();
    }

    private void atualizarLista(){
        compromissos.clear();
        compromissos.addAll(c.buscar_compromissos_categoria(id_categoria));
        notifyDataSetChanged();
    }
}
