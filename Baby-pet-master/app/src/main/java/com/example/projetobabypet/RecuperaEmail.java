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

    ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia(this); //instancia o controler de usuario
    ActivityRecuperaEmailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperaEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonInicio.setOnClickListener(view -> {
            startActivity(new Intent(this, Login.class));
            this.finish();
        });

        binding.buttonVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, EsqueciLogin.class));
            this.finish();
        });

        pesquisarEmail();
    }

    private void pesquisarEmail() {
        binding.buttonPesquisar.setOnClickListener(view -> {
             try {
                 String cpf = binding.editTextCpf.getText().toString(); //pega o texto que tá no edit text de cpf
                Usuario usuario = controllerUsuario.buscarPorCpf(cpf); //joga pro controller e o controller vai retornar um usuario
                 binding.editTextEmail.setText(usuario.getEmail()); //setta o edit text de email com o email do usuario retornado pelo controller
             } catch (Exception e){ //se der erro de NullPointerException quer dizer que não tem nenhum usuario, fiquei com preguiça de usar if ent coloquei o try e catch dessa vez
                 AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria a caixa de alerta
                 caixademsg.setTitle("Erro"); //coloca o titulo da caixa de alerta
                 caixademsg.setMessage("Usuario invalido"); //setta a menssagem da caixa de alerta
                 caixademsg.show(); //exibe a caixa
             }
        });
    }
}