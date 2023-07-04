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
        ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia(); //instancia o controller do usuario
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
            String email = binding.editTextEmail.getText().toString(); //recebe o texto que tá no edit text de email
            Usuario usuario = controllerUsuario.buscarPorEmail(email); //joga o email pro controller e o controller vai retornar um usuario com esse email
            if(usuario != null){ //se o usuario retornado pelo controller não for nulo
                Intent it = new Intent(this, AlterarSenha.class); //inicio o processo de troca de activity criando uma intenção(Intent)
                it.putExtra("us", usuario);  //passo o usuario encontrado pelo email para a activity de Alterar Senha
                startActivity(it); //inicia a Activity Alterar Senha
            }else{ //se o usuario for nullo quer dizer que não tem nenhum usuario com aquele email
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria a caixa de dialogo
                caixademsg.setTitle("Erro"); //coloca o titulo da caixa de dialogo
                caixademsg.setMessage("Email não encontrado"); //setta a mensagem da caixa de dialogo
                caixademsg.show(); //exibe a caixa de alerta
            }
    }
}