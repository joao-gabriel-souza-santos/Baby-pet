package com.example.projetobabypet.activities.cadastro;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerPet;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityCadastrarNovoPetBinding;
import com.example.projetobabypet.model.Pet;
import com.example.projetobabypet.model.Usuario;

import java.io.ByteArrayOutputStream;

public class CadastrarNovoPet extends AppCompatActivity {

    private ActivityCadastrarNovoPetBinding binding;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String email;
    private Usuario usuario;
    private  Bitmap fotoCarregada = null;
    private Bitmap fotoPuxada;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCadastrarNovoPetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences("Log", MODE_PRIVATE);
        editor = sp.edit();
        email = sp.getString("email", "");
        binding.buttonPetCadastrarNovo.setOnClickListener(view ->{
           try{
               usuario = usuario_logado(email);
               String nome = binding.editTextContaNome.getText().toString();
               int idUsuario = usuario.getId();
               String raca = binding.editTextContaRaca.getText().toString();
               String sexo = binding.editTextContaSexo.getText().toString();
               int idade = Integer.parseInt(binding.editTextContaIdade.getText().toString());
               String horas_comida_manha = binding.editTextContaHorasCafe.getText().toString();
               String horas_comida_tarde = binding.editTextHorasAlmoco.getText().toString();
               String horas_comida_noite = binding.editTextHorasJanta.getText().toString();
               String horas_agua_manha = binding.editTextContaAguaManha.getText().toString();
               String horas_agua_tarde = binding.editTextContaAguaTarde.getText().toString();
               String horas_agua_noite = binding.editTextContaAguaNoite.getText().toString();

               Pet pet = new Pet(nome, sexo, raca, horas_comida_manha, horas_comida_tarde, horas_comida_noite, horas_agua_manha, horas_agua_tarde, horas_agua_noite, idUsuario, idade, fotoCarregada);
               ControllerPet controllerPet = ControllerPet.getInstancia(this);
               controllerPet.cadastrarNovoPet(pet);
               this.finish();
           }catch (Exception e){
               AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
               caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
               caixademsg.setMessage("" + e.getMessage()); //coloca a mensagem da caixa
               caixademsg.show(); //exibe a caixa pro usuario
           }
        });

        binding.imagemRedonda.setOnClickListener(view->{
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                String[] permissao = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissao, 1001);
            } else {
                escolherImagem();
            }

        });

        binding.buttonVoltarCadastroPet.setOnClickListener(view -> {
            this.finish();
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
                    binding.imagemRedonda.setImageResource(R.drawable.baseline_person_24);
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
                binding.imagemRedonda.setImageBitmap(fotoRedimensionda);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                fotoPuxada.compress(Bitmap.CompressFormat.PNG, 100, stream);
                fotoCarregada = fotoPuxada;

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                binding.imagemRedonda.setImageResource(R.drawable.baseline_person_24);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                fotoPuxada.compress(Bitmap.CompressFormat.PNG, 100, stream);
                fotoCarregada = fotoPuxada;
            }
        }
    }
    private Usuario usuario_logado(String email) {
        try {
            ControllerUsuario c = ControllerUsuario.getInstancia(CadastrarNovoPet.this);
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

}