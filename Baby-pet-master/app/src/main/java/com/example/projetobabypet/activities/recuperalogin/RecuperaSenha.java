package com.example.projetobabypet.activities.recuperalogin;

import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityRecuperaSenhaBinding;
import com.example.projetobabypet.model.Usuario;

public class RecuperaSenha extends AppCompatActivity {
        ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia(this); //instancia o controller do usuario
        ActivityRecuperaSenhaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperaSenhaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, EsqueciLogin.class));
            this.finish();
        });
        binding.buttonContinuar.setOnClickListener(view -> {
            verificaEmail();
        });
    }

    private void verificaEmail() {
           try {
               String email = binding.editTextEmail.getText().toString(); //recebe o texto que tá no edit text de email
               Usuario usuario = controllerUsuario.buscarPorEmail(email); //joga o email pro controller e o controller vai retornar um usuario com esse email
               if (usuario != null) { //se o usuario retornado pelo controller não for nulo
                   Intent it = new Intent(this, AlterarSenha.class); //inicio o processo de troca de activity criando uma intenção(Intent)
                   it.putExtra("email", email);  //passo o usuario encontrado pelo email para a activity de Alterar Senha
                   startActivity(it); //inicia a Activity Alterar Senha
               } else { //se o usuario for nullo quer dizer que não tem nenhum usuario com aquele email
                   AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria a caixa de dialogo
                   caixademsg.setTitle("Erro"); //coloca o titulo da caixa de dialogo
                   caixademsg.setMessage("Email não encontrado"); //setta a mensagem da caixa de dialogo
                   caixademsg.show(); //exibe a caixa de alerta
               }
           }catch (Exception e){
               AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria a caixa de dialogo
               caixademsg.setTitle("Erro"); //coloca o titulo da caixa de dialogo
               caixademsg.setMessage(e.getMessage()); //setta a mensagem da caixa de dialogo
               caixademsg.show(); //exibe a caixa de alerta
           }
    }
}