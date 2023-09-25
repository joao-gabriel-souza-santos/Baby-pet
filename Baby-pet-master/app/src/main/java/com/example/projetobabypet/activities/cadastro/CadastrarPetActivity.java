package com.example.projetobabypet.activities.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projetobabypet.activities.Login;
import com.example.projetobabypet.controller.ControllerPet;
import com.example.projetobabypet.databinding.ActivityCadastrarPetBinding;
import com.example.projetobabypet.model.Pet;

public class CadastrarPetActivity extends AppCompatActivity {


    ControllerPet controllerPet = ControllerPet.getInstancia(this);

    Pet pet;


    private ActivityCadastrarPetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrarPetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnFinalizarCadastro.setOnClickListener(view -> {
            try {
                pet = cadastrarPet();
                Toast.makeText(CadastrarPetActivity.this, "Cadastro efetuado com sucesso", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, Login.class));
                this.finish();
            } catch (Exception e) {
                Toast.makeText(CadastrarPetActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    private Pet cadastrarPet() {
        String nome = binding.txtNome.getText().toString();
        String raca = binding.txtRaca.getText().toString();
        int idade = Integer.parseInt(binding.txtIdade.getText().toString());
        String sexo = binding.txtSexo.getText().toString();

        pet = new Pet(nome, sexo, raca, idade);
        controllerPet.cadastrar(pet);
        return pet;


    }
}