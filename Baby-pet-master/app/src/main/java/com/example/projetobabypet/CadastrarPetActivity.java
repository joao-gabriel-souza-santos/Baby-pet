package com.example.projetobabypet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.projetobabypet.controller.ControllerPet;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityCadastrarPetBinding;
import com.example.projetobabypet.databinding.ActivityCadastroBinding;
import com.example.projetobabypet.model.Pet;

public class CadastrarPetActivity extends AppCompatActivity {


    ControllerPet controllerPet = ControllerPet.getInstancia();

    Pet pet;


    private ActivityCadastrarPetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrarPetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnFinalizarCadastro.setOnClickListener(view -> {
            pet = cadastrarPet();

            startActivity(new Intent(this, Login.class));
        });
    }

    private Pet cadastrarPet() {
        String nome = binding.txtNome.getText().toString();
        String raca = binding.txtRaca.getText().toString();
        int idade = Integer.parseInt(binding.txtIdade.getText().toString());
        String sexo = binding.txtSexo.getText().toString();
        int id = controllerPet.getProxId();
        int idUsuario = getIntent().getIntExtra("idUsuario", -1);
        pet = new Pet(nome, sexo, raca, id, idUsuario, idade);
        controllerPet.cadastrar(pet);
        return pet;
    }
}