package com.example.projetobabypet.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.cadastro.Cadastro;
import com.example.projetobabypet.activities.recuperalogin.EsqueciLogin;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.dao.firebase.FirebaseDB;
import com.example.projetobabypet.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
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



        sp = getSharedPreferences("Log", MODE_PRIVATE);
        editor = sp.edit();
        boolean logado = sp.getBoolean("user_logado", false);
        if(logado == true){
            startActivity(new Intent(this, HomeActivity.class));
            this.finish();
        }

        validarLogin();
    }

    private void validarLogin() {
        binding.btnLogin.setOnClickListener(view -> {
            try {
                Dialog dialog = showProgressBar(this);
                dialog.create();
                FirebaseDB db = new FirebaseDB(this);
                String usuarioEmail = binding.email.getText().toString(); //recebe o texto que ta no edit text de email
                String senhaUsuario = binding.senha.getText().toString(); //receb o texto que ta no edit text de senha
                if(controllerUsuario.login(usuarioEmail, senhaUsuario)) { //manda pro controler verificar o login,
                    editor.putString("email", binding.email.getText().toString());
                    editor.putString("senha", binding.senha.getText().toString());
                    editor.putBoolean("user_logado", true);
                    editor.apply();

                    startActivity(new Intent(this, HomeActivity.class)); //se o controller retornaar positivo então
                    this.finish();

                } else  {
                    db.verificarExistenciaUsuario(sp,dialog,usuarioEmail, senhaUsuario, new FirebaseDB.CadastroCallback() {
                        @Override
                        public void onCadastroSucesso() {

                        }
                        @Override
                        public void onCadastroFalha() {
                            AlertDialog.Builder caixademsg = new AlertDialog.Builder(Login.this); //cria uma caixa de alerta
                            caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                            caixademsg.setMessage("Usuário ou senha inválidos"); //coloca a mensagem da caixa
                            caixademsg.show(); //exibe a caixa pro usuario
                        }
                    });
                }
            }catch (Exception e){
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
                caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                caixademsg.setMessage("" + e.getMessage()); //coloca a mensagem da caixa
                caixademsg.show(); //exibe a caixa pro usuario
            }
        });
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