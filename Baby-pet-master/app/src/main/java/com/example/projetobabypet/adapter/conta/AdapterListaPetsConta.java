package com.example.projetobabypet.adapter.conta;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerPet;
import com.example.projetobabypet.interfaces.RecyclerClickPetConta;
import com.example.projetobabypet.model.Pet;

import java.util.List;

public class AdapterListaPetsConta extends RecyclerView.Adapter<HolderListaPetsConta> {

    private final RecyclerClickPetConta recyclerClickPetConta;
    Context context;
    public static List<Pet> pets;
    ControllerPet c;
    int idUsuario;

    public AdapterListaPetsConta(Context context, int idUsuario, RecyclerClickPetConta recyclerClickPetConta){
        this.context = context;
        this.idUsuario = idUsuario;
        this.recyclerClickPetConta = recyclerClickPetConta;
        c = ControllerPet.getInstancia(context);
        try {
            pets = c.teste(idUsuario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    @Override
    public HolderListaPetsConta onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycle_view_conta_pets, viewGroup, false);
        return new HolderListaPetsConta(view, recyclerClickPetConta);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderListaPetsConta holder, int i) {
            Pet pet = pets.get(i);
            holder.nome.setText(pet.getNome());
            holder.sexo.setText(" " + pet.getSexo());
            holder.idade.setText(" "+pet.getIdade());
            holder.raca.setText(" "+ pet.getRaca());
            Bitmap fotoRedimensionada = Bitmap.createScaledBitmap(pet.getFoto(), 150, 150, true);
            holder.imag.setImageBitmap(fotoRedimensionada);

            holder.cardView.setOnClickListener(view -> {
                recyclerClickPetConta.onItemClick(pets.get(i));
            });

            holder.cardView.setOnLongClickListener(view -> {

                c.deletarPet(pets.get(i));
                try {
                    atualizarLista(context, idUsuario);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return true;
            });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static void atualizarLista(Context context, int idUsuario) throws Exception {
        pets.clear();
        ControllerPet cc = ControllerPet.getInstancia(context);
        pets.addAll(cc.buscar_todos_pets_usuario(idUsuario));
    }
}
