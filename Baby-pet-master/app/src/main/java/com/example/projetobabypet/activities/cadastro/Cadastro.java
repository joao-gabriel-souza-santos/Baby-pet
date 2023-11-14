package com.example.projetobabypet.activities.cadastro;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.Login;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.dao.firebase.FirebaseDB;
import com.example.projetobabypet.databinding.ActivityCadastroBinding;
import com.example.projetobabypet.model.Usuario;
import com.google.firebase.FirebaseApp;

import java.io.ByteArrayOutputStream;

public class Cadastro extends AppCompatActivity {

    private Usuario usuario;

    private ActivityCadastroBinding binding;
    private Bitmap fotoCarregada = null;
    private Bitmap fotoPuxada;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fotoCarregada = null;


        binding.buttonVoltarCadastro.setOnClickListener(view -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            this.finish();

        });

        binding.buttonEsqueciEmail.setOnClickListener(view -> {

            try {
                cadastrarUsuario();
            } catch (Exception e) {
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
                caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                caixademsg.setMessage("" + e.getMessage()); //coloca a mensagem da caixa
                caixademsg.show(); //exibe a caixa pro usuario
            }
        });

        binding.imageRedonda.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                String[] permissao = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissao, 1001);
            } else {
                escolherImagem();
            }

        });
    }

    private void escolherImagem() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1001: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    escolherImagem();
                } else {
                    Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
                    binding.imageRedonda.setImageResource(R.drawable.baseline_person_24);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    fotoPuxada.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    fotoCarregada = fotoPuxada;
                }
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1000) {
            fotoPuxada = null;
            try {
                Uri image = data.getData();
                fotoPuxada = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
                Bitmap fotoRedimensionda = Bitmap.createScaledBitmap(fotoPuxada, 150, 150, true);
                binding.imageRedonda.setImageBitmap(fotoRedimensionda);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                fotoPuxada.compress(Bitmap.CompressFormat.PNG, 100, stream);
                fotoCarregada = fotoPuxada;

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                binding.imageRedonda.setImageResource(R.drawable.baseline_person_24);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                fotoPuxada.compress(Bitmap.CompressFormat.PNG, 100, stream);
                fotoCarregada = fotoPuxada;
            }
        }
    }

    private Usuario cadastrarUsuario() {


        String nome = binding.txtNome.getText().toString();

        String email = binding.txtEmail.getText().toString();
        String cpf = binding.txtCpf.getText().toString();
        String senha = binding.txtSenha.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!cpf.isEmpty()) {
                    if (!senha.isEmpty()) {
                        if (senha.length() >= 6) {
                            if (fotoCarregada == null) {
                                binding.imageRedonda.setImageResource(R.drawable.imagem_redonda);
                                Drawable drawable = binding.imageRedonda.getDrawable();
                                fotoCarregada = ((BitmapDrawable) drawable).getBitmap();
                            }
                            Dialog dialog = showProgressBar(this);
                            dialog.create();
                            dialog.show();
                            String fotoString = FirebaseDB.converte_bitmap_pra_string(fotoCarregada);
                            FirebaseDB firebaseDB = new FirebaseDB(this);
                            firebaseDB.cadastrarUsuario(dialog, email, senha, nome, cpf, fotoString, new FirebaseDB.CadastroCallback() {
                                @Override
                                public void onCadastroSucesso() {

                                }

                                @Override
                                public void onCadastroFalha() {
                                    AlertDialog.Builder caixademsg = new AlertDialog.Builder(Cadastro.this); //cria uma caixa de alerta
                                    caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                                    caixademsg.setMessage("Usuário inválido"); //coloca a mensagem da caixa
                                    caixademsg.show(); //exibe a caixa pro usuario
                                }
                            });
                        }else {
                            AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
                            caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                            caixademsg.setMessage("A senha precisa ter no mínimo 6 caracteres"); //coloca a mensagem da caixa
                            caixademsg.show(); //exibe a caixa pro usuario
                        }
                    } else {
                        AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
                        caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                        caixademsg.setMessage("Campo senha vazio. Por favor digite uma senha com no mínimo 6 caracteres"); //coloca a mensagem da caixa
                        caixademsg.show(); //exibe a caixa pro usuario
                    }
                } else {
                    AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
                    caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                    caixademsg.setMessage("usuario nulo"); //coloca a mensagem da caixa
                    caixademsg.show(); //exibe a caixa pro usuario
                }
            } else{
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
                caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                caixademsg.setMessage("Campo email vazio. Por favor insira um email"); //coloca a mensagem da caixa
                caixademsg.show(); //exibe a caixa pro usuario
            }
        } else {
            AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
            caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
            caixademsg.setMessage("Campo nome vazio. Por favor insira um nome"); //coloca a mensagem da caixa
            caixademsg.show(); //exibe a caixa pro usuario
        }
        return usuario;
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