package com.example.projetobabypet;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.Login;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityRecuperaEmailBinding;
import com.example.projetobabypet.model.Usuario;

public class RecuperaEmail extends AppCompatActivity {

    ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia();
    ActivityRecuperaEmailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperaEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonInicio.setOnClickListener(view -> {
            startActivity(new Intent(this, Login.class));
        });

        binding.buttonVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, EsqueciLogin.class));
        });

        pesquisarEmail();
    }

    private void pesquisarEmail() {
        binding.buttonPesquisar.setOnClickListener(view -> {
             try {
                 String cpf = binding.editTextCpf.getText().toString();
                Usuario usuario = controllerUsuario.buscarPorCpf(cpf);
                 binding.editTextEmail.setText(usuario.getEmail());
             } catch (Exception e){
                 AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
                 caixademsg.setTitle("Erro");
                 caixademsg.setMessage("Usuario invalido");
                 caixademsg.show();
             }
        });
    }
}