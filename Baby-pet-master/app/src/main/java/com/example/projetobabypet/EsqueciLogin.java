package com.example.projetobabypet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projetobabypet.activities.Login;
import com.example.projetobabypet.databinding.ActivityEsqueciLoginBinding;

public class EsqueciLogin extends AppCompatActivity {

    ActivityEsqueciLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEsqueciLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonEsqueciEmail.setOnClickListener(view->{
            startActivity(new Intent(this, RecuperaEmail.class)); //leva o usuario pra tela de esqueci email quando clicar no botão
        });

        binding.buttonEsqueciSenha.setOnClickListener(view -> {
            startActivity(new Intent(this, RecuperaSenha.class)); //leva o usuario pra tela de esqueci a senha quando o usuario apertar o botão
        });

        binding.buttonVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, Login.class));// leva pra tela de login quando o usuario apertar no botão de voltar
        });
    }
}