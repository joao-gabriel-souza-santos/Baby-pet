package com.example.projetobabypet.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerPet;
import com.example.projetobabypet.model.Pet;

import java.util.List;

public class AdapterRecycleListaPets extends RecyclerView.Adapter<HolderRecycleListaPets> {

    Context context;
    int id_usuario;
    ControllerPet cp;
    public static List<Pet> pets;


    public AdapterRecycleListaPets(Context context, int id_usuario){
        this.context = context;
        this.id_usuario = id_usuario;
        cp = ControllerPet.getInstancia(context);
        try {
            pets = cp.buscar_todos_pets_usuario(id_usuario);
        } catch (Exception e) {
            AlertDialog.Builder caixademsg = new AlertDialog.Builder(context); //cria a caixa de dialogo
            caixademsg.setTitle("Erro"); //coloca o titulo da caixa de dialogo
            caixademsg.setMessage(e.getMessage()); //setta a mensagem da caixa de dialogo
            caixademsg.show(); //exibe a caixa de alerta;
        }
    }

    @NonNull
    @Override
    public HolderRecycleListaPets onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_view_layout_header_lista_pets, viewGroup, false);
        return new HolderRecycleListaPets(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecycleListaPets holderRecycleListaPets, int i) {

        Pet pet = pets.get(i);
        holderRecycleListaPets.textView_nome_pet.setText(pet.getNome());
        holderRecycleListaPets.textView_raca_pet.setText(pet.getNome());
        Bitmap fotoRedimensionada = Bitmap.createScaledBitmap(pet.getFoto(), 80, 74, true);
        holderRecycleListaPets.foto_pet.setImageBitmap(fotoRedimensionada);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }
}
