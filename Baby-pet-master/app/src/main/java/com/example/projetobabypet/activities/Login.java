package com.example.projetobabypet.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projetobabypet.activities.cadastro.Cadastro;
import com.example.projetobabypet.EsqueciLogin;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia(this); //instancia do controller de usuario
    private ActivityLoginBinding binding; //binding serve pra acessar as views que estão no xml (botão, texto, editText)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater()); //infla o layout da classe
        setContentView(binding.getRoot()); //setta o layout na view


        binding.buttonEsqueci.setOnClickListener(view -> {
            startActivity(new Intent(this, EsqueciLogin.class)); //chamar a classe esqueci login quando clicar no botao esqueci login
            this.finish();
        });

        binding.btnCadastrar.setOnClickListener(view -> {
            startActivity(new Intent(this, Cadastro.class)); //chama a classe cadastrar quando clicar no botao cadastrar
            this.finish();
        });

        validarLogin(); //chama o metodo validar login
    }

    private void validarLogin() {
        binding.btnLogin.setOnClickListener(view -> {
            try {
                String usuarioEmail = binding.email.getText().toString(); //recebe o texto que ta no edit text de email
                String senhaUsuario = binding.senha.getText().toString(); //receb o texto que ta no edit text de senha
                if(controllerUsuario.login(usuarioEmail, senhaUsuario)) { //manda pro controler verificar o login,
                    this.finish();
                    startActivity(new Intent(this, HomeActivity.class)); //se o controller retornaar positivo então
                    // o login é efetuado com sucesso e leva pra tela inicial
                }else {
                    AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
                    caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                    caixademsg.setMessage("Usuario invalido"); //coloca a mensagem da caixa
                    caixademsg.show(); //exibe a caixa pro usuario
                }


            }catch (Exception e){
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
                caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                caixademsg.setMessage("" + e.getMessage()); //coloca a mensagem da caixa
                caixademsg.show(); //exibe a caixa pro usuario
            }
        });
    }
}