package com.example.projetobabypet;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityRecuperaSenhaBinding;
import com.example.projetobabypet.model.Usuario;

public class RecuperaSenha extends AppCompatActivity {
        ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia();
        ActivityRecuperaSenhaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperaSenhaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, EsqueciLogin.class));
        });
        binding.buttonContinuar.setOnClickListener(view -> {
            verificaEmail();
        });
    }

    private void verificaEmail() {
            String email = binding.editTextEmail.getText().toString();
            Usuario usuario = controllerUsuario.buscarPorEmail(email);
            if(usuario != null){
                Intent it = new Intent(this, AlterarSenha.class);
                it.putExtra("us", usuario);
                startActivity(it);
            }else{
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
                caixademsg.setTitle("Erro");
                caixademsg.setMessage("Email não encontrado");
                caixademsg.show();
            }
    }
}