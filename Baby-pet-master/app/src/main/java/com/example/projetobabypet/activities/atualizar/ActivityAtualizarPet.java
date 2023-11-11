package com.example.projetobabypet.activities.atualizar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.projetobabypet.activities.HomeActivity;
import com.example.projetobabypet.adapter.conta.AdapterListaPetsConta;
import com.example.projetobabypet.controller.ControllerPet;
import com.example.projetobabypet.databinding.ActivityAtualizarPetBinding;
import com.example.projetobabypet.databinding.ActivityAtualizarUsuarioBinding;
import com.example.projetobabypet.model.Pet;

import java.io.ByteArrayOutputStream;

public class ActivityAtualizarPet extends AppCompatActivity {


    ActivityAtualizarPetBinding binding;
    Intent intent;
    private  Bitmap fotoCarregada = null;
    private Bitmap fotoPuxada;
    int idade;
    int id ;
    String nome ;
    int idUsuario ;
    String raca ;
    String sexo ;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAtualizarPetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        generateComponents();

        binding.buttonPetCadastrarNovo.setOnClickListener(view -> {
            atualizarPet();
        });

        binding.imagemRedonda.setOnClickListener(view->{
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                String[] permissao = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissao, 1001);
            } else {
                escolherImagem();
            }

        });

        binding.imageButtonVoltarAtualizarPet.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, HomeActivity.class);
            startActivity(intent1);

            this.finish();
        });
    }

    private void generateComponents() {
        intent = getIntent();
        idade = intent.getIntExtra("idade", 0);
        id = intent.getIntExtra("id", 0);
        nome = intent.getStringExtra("nome");
        idUsuario = intent.getIntExtra("idUsuario", 0);
        raca = intent.getStringExtra("raca");
        sexo = intent.getStringExtra("sexo");
        byte[] stream = intent.getByteArrayExtra("foto");
        Bitmap foto = BitmapFactory.decodeByteArray(stream,0,stream.length);
        binding.imagemRedonda.setImageBitmap(foto);
        binding.editTextContaNome.setText(nome);
        binding.editTextContaRaca.setText(raca);
        binding.editTextContaSexo.setText(sexo);
        binding.editTextContaIdade.setText("" + idade);

    }

    private void atualizarPet() {
             nome = binding.editTextContaNome.getText().toString();
             raca = binding.editTextContaRaca.getText().toString();
             sexo = binding.editTextContaSexo.getText().toString();
             idade = Integer.parseInt(binding.editTextContaIdade.getText().toString());


             Pet pet = new Pet(nome, sexo, raca, id,idUsuario, idade, fotoCarregada);
             ControllerPet controllerPet = ControllerPet.getInstancia(this);
             controllerPet.atualizarPet(pet);
        try {
            AdapterListaPetsConta.atualizarLista(this, idUsuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

        this.finish();
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
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1000) {
            try {
                Uri image = data.getData();
                Bitmap fotoPuxada = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
                Bitmap fotoRedimensionda = Bitmap.createScaledBitmap(fotoPuxada, 150, 150, true);
                binding.imagemRedonda.setImageBitmap(fotoRedimensionda);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                fotoPuxada.compress(Bitmap.CompressFormat.PNG, 100, stream);
                fotoCarregada = fotoPuxada;

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}