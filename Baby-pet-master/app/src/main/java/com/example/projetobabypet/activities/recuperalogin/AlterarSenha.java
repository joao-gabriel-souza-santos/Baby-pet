package com.example.projetobabypet.activities.recuperalogin;

import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projetobabypet.activities.Login;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityAlterarSenhaBinding;
import com.example.projetobabypet.model.Usuario;

public class AlterarSenha extends AppCompatActivity {


    ActivityAlterarSenhaBinding binding;
    ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia(this); //instancia o controller do usuario
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlterarSenhaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonAlterarSenha.setOnClickListener(view -> {
            alterarSenha();
        });

        binding.buttonAlterarSenha.setOnClickListener(view -> {
            startActivity(new Intent(this, RecuperaSenha.class));
        });


    }

    private void alterarSenha() {

        try {
            String novaSenha = binding.editTextSenha.getText().toString(); //pega o texto do editText de senha
            String confirmarSenha = binding.editTextConfirmarsenha.getText().toString(); //pega o texto do edit text de confirmar senha
            Intent it = getIntent(); //recebo a intenção que eu passei na tela de Esqueci senha
            if (it != null) { //verifico se tem algo na intenção
                Usuario usuario = (Usuario) it.getSerializableExtra("us"); // se tiver algo na intenção, então é o usuario que
                // eu passei na tela de esqueci senha, então eu recebo essa intenção
                // e converto esse objeto em usuario
                if (usuario != null) { //verifico se o usuario é nullo
                    if (novaSenha.equals(confirmarSenha)) { //verifico se as duas senhas são iguais
                        boolean resultado = controllerUsuario.alterarSenha(usuario, novaSenha); //jogo pro controller o usuario e a senha pra ele
                        // alterar a senha, e o controller vai me dar uma resposta
                        // verdadeira caso alterado com sucesso
                        if (resultado) { //se foi alterado com sucesso
                            Toast.makeText(this, "Alteração efetuada com sucesso", Toast.LENGTH_LONG).show(); //exibo um toast dizendo que
                            //a alteração foi um sucesso
                            startActivity(new Intent(this, Login.class)); //levo o usuario pra activity de login
                            this.finish();
                        }
                    } else { //caso o resultado da troca de senha tenha sido falso
                        // aviso o usuario pela caixa de alerta que o resultado foi falso
                        AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
                        caixademsg.setTitle("Erro");
                        caixademsg.setMessage("As senhas não estão iguais");
                        caixademsg.show();
                    }
                }
            } else { //isso aq eu fiz pra verificar se tava vindo a intenção da activity, se viesse nullo quer dizer que a activity não tava me passando o usuario
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
                caixademsg.setTitle("Erro");
                caixademsg.setMessage("Algo de errado aconteceu");
                caixademsg.show();
            }
        }catch (Exception e){
            AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
            caixademsg.setTitle("Erro");
            caixademsg.setMessage(""+e.getMessage());
            caixademsg.show();
        }
    }
}