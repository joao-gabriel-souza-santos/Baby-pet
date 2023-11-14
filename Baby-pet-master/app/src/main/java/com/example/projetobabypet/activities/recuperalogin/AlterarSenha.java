package com.example.projetobabypet.activities.recuperalogin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.Login;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.dao.firebase.FirebaseDB;
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
    }

    private void alterarSenha() {

        try {
            String novaSenha = binding.editTextSenha.getText().toString(); //pega o texto do editText de senha
            String confirmarSenha = binding.editTextConfirmarsenha.getText().toString(); //pega o texto do edit text de confirmar senha
            Intent it = getIntent(); //recebo a intenção que eu passei na tela de Esqueci senha
            if (it != null) { //verifico se tem algo na intenção
                String email = it.getStringExtra("email");
                Usuario usuario = controllerUsuario.buscarPorEmail(email);// se tiver algo na intenção, então é o usuario que
                if (usuario != null) { //verifico se o usuario é nullo
                    if (novaSenha.length() > 6) {
                        if (novaSenha.equals(confirmarSenha)) { //verifico se as duas senhas são iguais
                            Dialog dialog = showProgressBar(this);
                            dialog.create();
                            dialog.show();
                            ;
                            FirebaseDB firebaseDB = new FirebaseDB(this);
                            firebaseDB.alterarSenha(dialog, usuario, email, novaSenha, new FirebaseDB.CadastroCallback() {
                                @Override
                                public void onCadastroSucesso() {

                                }

                                @Override
                                public void onCadastroFalha() {
                                    dialog.cancel();
                                }
                            });
                        } else { //caso o resultado da troca de senha tenha sido falso
                            // aviso o usuario pela caixa de alerta que o resultado foi falso
                            AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
                            caixademsg.setTitle("Erro");
                            caixademsg.setMessage("As senhas não estão iguais");
                            caixademsg.show();
                        }
                    } else {
                        AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
                        caixademsg.setTitle("Erro");
                        caixademsg.setMessage("A senha precisa ter no mínimo 6 dígitos");
                        caixademsg.show();
                    }
                }
            } else { //isso aq eu fiz pra verificar se tava vindo a intenção da activity, se viesse nullo quer dizer que a activity não tava me passando o usuario
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
                caixademsg.setTitle("Erro");
                caixademsg.setMessage("Algo de errado aconteceu");
                caixademsg.show();
            }
        } catch (Exception e) {
            AlertDialog.Builder caixademsg = new AlertDialog.Builder(this);
            caixademsg.setTitle("Erro");
            caixademsg.setMessage("" + e.getMessage());
            caixademsg.show();
        }
    }

    public Dialog showProgressBar(Context context) {
        Dialog progressDialog = new Dialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.pop_up_progressbar);
        progressDialog.setCancelable(false);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(progressDialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        progressDialog.show();
        progressDialog.getWindow().setAttributes(layoutParams);
        return progressDialog;
    }
}