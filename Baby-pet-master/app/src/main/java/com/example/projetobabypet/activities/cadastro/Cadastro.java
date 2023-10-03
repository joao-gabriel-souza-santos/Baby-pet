package com.example.projetobabypet.activities.cadastro;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.cadastro.CadastrarPetActivity;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityCadastroBinding;
import com.example.projetobabypet.model.Usuario;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.io.ByteArrayOutputStream;

public class Cadastro extends AppCompatActivity {

    private Usuario usuario;

    private ActivityCadastroBinding binding;
    private  Bitmap fotoCarregada = null;
    private Bitmap fotoPuxada;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fotoCarregada = null;



        binding.buttonEsqueciEmail.setOnClickListener(view -> {

           try {
               usuario = cadastrarUsuario();
               Intent it = new Intent(this, CadastrarPetActivity.class);
               it.putExtra("email", usuario.getEmail());
               startActivity(it);

               this.finish();
           }catch (Exception e){
               AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
               caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
               caixademsg.setMessage("" + e.getMessage()); //coloca a mensagem da caixa
               caixademsg.show(); //exibe a caixa pro usuario
           }
        });

        binding.imageRedonda.setOnClickListener(view->{
           if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
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
       if(resultCode == RESULT_OK && requestCode == 1000) {
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
        int qtde_pets = Integer.parseInt(binding.editTextQtdePet.getText().toString());
        ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia(this);

        usuario = new Usuario(nome, cpf, email, senha, fotoCarregada, qtde_pets);
        controllerUsuario.cadastrar(usuario, this);
        return usuario;
    }
}