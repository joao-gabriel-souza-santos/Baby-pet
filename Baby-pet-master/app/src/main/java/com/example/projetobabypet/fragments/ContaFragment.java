package com.example.projetobabypet.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.projetobabypet.activities.atualizar.ActivityAtualizarPet;
import com.example.projetobabypet.activities.cadastro.CadastrarNovoPet;
import com.example.projetobabypet.controller.ControllerPet;
import com.example.projetobabypet.interfaces.RecyclerClickPetConta;
import com.example.projetobabypet.model.Pet;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetobabypet.adapter.conta.AdapterListaPetsConta;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.FragmentContaBinding;
import com.example.projetobabypet.model.Usuario;

import java.io.ByteArrayOutputStream;


public class ContaFragment extends Fragment implements RecyclerClickPetConta {
    FragmentContaBinding binding;
    private String email;
    private Usuario usuario;

    BottomSheetDialog dialog;
    ControllerPet c;
    AdapterListaPetsConta adapterListaPetsConta;
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1;


    SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        sp = getActivity().getSharedPreferences("Log", Context.MODE_PRIVATE);
        editor = sp.edit();
        email = sp.getString("email", "");

        pets_do_usuario();




        binding.buttonAdicionarPet.setOnClickListener(view1 -> {
            try {
                Intent intent = new Intent(getContext(), CadastrarNovoPet.class);
                startActivity(intent);
            }catch (Exception e){

            }

        });


        return view;
    }



    private void pets_do_usuario() {
        try {
            usuario = usuario_logado(email);
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            binding.recyclerViewContaListaPets.setLayoutManager(manager);
            adapterListaPetsConta = new AdapterListaPetsConta(getContext(), email, position -> onItemClick(position));
            binding.recyclerViewContaListaPets.setAdapter(adapterListaPetsConta);
        } catch (Exception e) {
            AlertDialog.Builder caixademsg = new AlertDialog.Builder(getContext()); //cria uma caixa de alerta
            caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
            caixademsg.setMessage("usuario nulo"); //coloca a mensagem da caixa
            caixademsg.show(); //exibe a caixa pro usuario
        }
    }


    private Usuario usuario_logado(String email) {
        try {
            ControllerUsuario c = ControllerUsuario.getInstancia(getContext());
            Usuario usuario = c.buscarPorEmail(email);
            return usuario;
        } catch (Exception e) {
            AlertDialog.Builder caixademsg = new AlertDialog.Builder(getContext()); //cria uma caixa de alerta
            caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
            caixademsg.setMessage("" + e.getMessage()); //coloca a mensagem da caixa
            caixademsg.show(); //exibe a caixa pro usuario
        }
        return null;
    }

    @Override
    public void onItemClick(Pet pet) {

            try {
                Intent intent = new Intent(getContext(), ActivityAtualizarPet.class);

                intent.putExtra("id", pet.getId());
                intent.putExtra("idade", pet.getIdade());
                intent.putExtra("email", pet.getEmail_usuario());
                intent.putExtra("nome", pet.getNome());
                intent.putExtra("raca", pet.getRaca());
                intent.putExtra("sexo", pet.getSexo());

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                pet.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);
                intent.putExtra("foto", stream.toByteArray());
                startActivity(intent);
            }catch (Exception e){
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(getContext()); //cria uma caixa de alerta
                caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                caixademsg.setMessage("" + e.getMessage()); //coloca a mensagem da caixa
                caixademsg.show(); //exibe a caixa pro usuario
            }


    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            adapterListaPetsConta.atualizarLista(getContext(), usuario.getEmail());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
