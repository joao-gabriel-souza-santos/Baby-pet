package com.example.projetobabypet;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.Login;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityAlterarSenhaBinding;
import com.example.projetobabypet.model.Usuario;

public class AlterarSenha extends AppCompatActivity {


    ActivityAlterarSenhaBinding binding;
    ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlterarSenhaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonAlterarSenha.setOnClickListener(view -> {
            String novaSenha = binding.editTextSenha.getText().toString();
            String confirmarSenha = binding.editTextConfirmarsenha.getText().toString();
            Intent it = getIntent();
            if(it!=null){
                Usuario usuario = (Usuario) it.getSerializableExtra("us");
                if(usuario !=null){
                    if(novaSenha.equals(confirmarSenha)){
                       boolean resultado = controllerUsuario.alterarSenha(usuario,novaSenha);
                       if(resultado){
                           Toast.makeText(this, "Alteração efetuada com sucesso", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(this, Login.class));
                       }
                       }else{
                        AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
                        caixademsg.setTitle("Erro");
                        caixademsg.setMessage("As senhas não estão iguais");
                        caixademsg.show();
                    }
                }
            }else {
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
                caixademsg.setTitle("Erro");
                caixademsg.setMessage("Algo de errado aconteceu");
                caixademsg.show();
            }
        });

        binding.buttonVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, RecuperaSenha.class));
        });


    }
}