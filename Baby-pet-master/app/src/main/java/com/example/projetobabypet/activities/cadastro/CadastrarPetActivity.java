package com.example.projetobabypet.activities.cadastro;

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
import android.os.Bundle;
import android.widget.Toast;

import com.example.projetobabypet.activities.Login;
import com.example.projetobabypet.controller.ControllerPet;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityCadastrarPetBinding;
import com.example.projetobabypet.model.Pet;
import com.example.projetobabypet.model.Usuario;

import java.io.ByteArrayOutputStream;

public class CadastrarPetActivity extends AppCompatActivity {


    private ControllerPet controllerPet = ControllerPet.getInstancia(this);

    private Pet pet;
    private Bitmap fotoCarregada = null;

    private ActivityCadastrarPetBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrarPetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnFinalizarCadastro.setOnClickListener(view -> {
            try {
                    pet = cadastrarPet();
                    Toast.makeText(CadastrarPetActivity.this, "Cadastro efetuado com sucesso", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(this, Login.class);
                    startActivity(it);
                    this.finish();
            } catch (Exception e) {
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
                caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                caixademsg.setMessage("" + e.getMessage()); //coloca a mensagem da caixa
                caixademsg.show(); //exibe a caixa pro usuario
            }

        });


        binding.imageRedonda.setOnClickListener(view -> {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                String[] permissao = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissao, 1001);
            } else {
                escolherImagem();
            }
        });
    }



    private Pet cadastrarPet() throws Exception {
        String email = getIntent().getStringExtra("email");

        ControllerUsuario c = ControllerUsuario.getInstancia(CadastrarPetActivity.this);
        Usuario us = c.buscarPorEmail(email);

        String nome = binding.txtNome.getText().toString();
        String raca = binding.txtRaca.getText().toString();
        int idade = Integer.parseInt(binding.txtIdade.getText().toString());
        String sexo = binding.txtSexo.getText().toString();

        pet = new Pet(us.getId(), nome, sexo, raca, idade, fotoCarregada);
      //  pet = new Pet(nome, sexo, raca, idade, fotoCarregada);
        controllerPet.cadastrar(pet);
        return pet;


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
                }
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == 1000){
            try{
                Uri image = data.getData();
                Bitmap fotoPuxada = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
                Bitmap fotoRedimensionda = Bitmap.createScaledBitmap(fotoPuxada, 150,150, true);
                binding.imageRedonda.setImageBitmap(fotoRedimensionda);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                fotoPuxada.compress(Bitmap.CompressFormat.PNG, 100, stream);
                fotoCarregada = fotoPuxada;

            }catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}