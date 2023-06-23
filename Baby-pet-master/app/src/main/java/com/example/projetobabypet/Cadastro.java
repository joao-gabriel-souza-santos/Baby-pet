package com.example.projetobabypet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityCadastroBinding;
import com.example.projetobabypet.model.Usuario;

import java.util.List;

public class Cadastro extends AppCompatActivity {

    private Usuario usuario;

    private ActivityCadastroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCadastrarPet.setOnClickListener(view -> {
            usuario = cadastrarUsuario();
            Intent it = new Intent(this, CadastrarPetActivity.class);
            it.putExtra("idUsuario", usuario.getId());
            startActivity(it);
        });
    }

    private Usuario cadastrarUsuario() {
        String nome = binding.txtNome.getText().toString();
        String email = binding.txtEmail.getText().toString();
        String cpf = binding.txtCpf.getText().toString();
        String senha = binding.txtSenha.getText().toString();
        ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia();
        int id = controllerUsuario.getProxId();
        usuario = new Usuario(nome, cpf, email, senha, id );
        controllerUsuario.cadastrar(usuario);
        return usuario;
    }
}