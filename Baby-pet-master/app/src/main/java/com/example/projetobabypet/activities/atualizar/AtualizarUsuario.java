package com.example.projetobabypet.activities.atualizar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import com.example.projetobabypet.activities.HomeActivity;
import com.example.projetobabypet.activities.Login;
import com.example.projetobabypet.activities.cadastro.CadastrarPetActivity;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.dao.firebase.FirebaseDB;
import com.example.projetobabypet.databinding.ActivityCadastroBinding;
import com.example.projetobabypet.model.Usuario;

import java.io.ByteArrayOutputStream;

import com.example.projetobabypet.R;
import com.example.projetobabypet.databinding.ActivityAtualizarUsuarioBinding;


public class AtualizarUsuario extends AppCompatActivity {
    private Usuario usuario;

    private  Bitmap fotoCarregada = null;
    private Bitmap fotoPuxada;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    ActivityAtualizarUsuarioBinding binding;
    String email;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAtualizarUsuarioBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        fotoCarregada = null;

        sp = getSharedPreferences("Log", MODE_PRIVATE);
        editor = sp.edit();
        email = sp.getString("email", "");

        usuario = usuario_logado(email);


        binding.txtNome.setText(usuario.getNome());
        binding.txtCpf.setText(usuario.getCpf());
        binding.txtEmail.setText(usuario.getEmail());
        binding.imageRedonda.setImageBitmap(usuario.getFoto());

        binding.buttonVoltarCadastro.setOnClickListener(view -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            this.finish();


        });

        int id = usuario.getId();
        binding.buttonSalvarUsuario.setOnClickListener(view -> {
                cadastrarUsuario();

        });

        binding.imageRedonda.setOnClickListener(view->{

                if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
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
        switch (requestCode){
            case 1001: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
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

    private String cadastrarUsuario() {
        String nome = binding.txtNome.getText().toString();
        String email = binding.txtEmail.getText().toString();
        String cpf = binding.txtCpf.getText().toString();

        usuario.setCpf(cpf);
        usuario.setNome(nome);
        if(fotoCarregada == null){
            BitmapDrawable drawable = (BitmapDrawable) binding.imageRedonda.getDrawable();

            fotoCarregada = drawable.getBitmap();
            usuario.setFoto(fotoCarregada);
        } else {
            usuario.setFoto(fotoCarregada);
        }
        Dialog dialog = showProgressBar(this);
        dialog.create();
        FirebaseDB firebaseDB = new FirebaseDB(this);
        firebaseDB.alterarDadosUsuario(sp,dialog, nome, cpf, email, usuario.getEmail(),usuario.getSenha(), fotoCarregada, new FirebaseDB.CadastroCallback() {
            @Override
            public void onCadastroSucesso() {

            }

            @Override
            public void onCadastroFalha() {

            }
        });

        return email;

    }
    private Usuario usuario_logado(String email) {
        try {
            ControllerUsuario c = ControllerUsuario.getInstancia(this);
            Usuario usuario = c.buscarPorEmail(email);
            return usuario;
        } catch (Exception e) {
            AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
            caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
            caixademsg.setMessage("" + e.getMessage()); //coloca a mensagem da caixa
            caixademsg.show(); //exibe a caixa pro usuario
        }
        return null;
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