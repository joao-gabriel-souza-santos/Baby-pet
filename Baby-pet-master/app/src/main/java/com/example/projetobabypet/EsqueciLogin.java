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
            startActivity(new Intent(this, RecuperaEmail.class));
        });

        binding.buttonEsqueciSenha.setOnClickListener(view -> {
            startActivity(new Intent(this, RecuperaSenha.class));
        });

        binding.buttonVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, Login.class));
        });
    }
}