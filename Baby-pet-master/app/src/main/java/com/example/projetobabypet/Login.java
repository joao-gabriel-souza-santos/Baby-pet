package com.example.projetobabypet;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia();
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCadastrar.setOnClickListener(view -> {
            startActivity(new Intent(this, Cadastro.class));
        });

        binding.btnLogin.setOnClickListener(view -> {
            String usuarioEmail = binding.email.getText().toString();
            String senhaUsuario = binding.senha.getText().toString();
            if(controllerUsuario.login(usuarioEmail, senhaUsuario)) {
                startActivity(new Intent(this, HomeActivity.class));
            } else {
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
                caixademsg.setTitle("Erro");
                caixademsg.setMessage("Email ou senha incorretos, tente novamente");
                caixademsg.show();
            }
        });
    }
}