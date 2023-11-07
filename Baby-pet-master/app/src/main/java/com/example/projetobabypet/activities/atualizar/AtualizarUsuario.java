package com.example.projetobabypet.activities.atualizar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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
import android.os.Bundle;
import android.widget.Toast;

import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.HomeActivity;
import com.example.projetobabypet.activities.Login;
import com.example.projetobabypet.activities.cadastro.CadastrarPetActivity;
import com.example.projetobabypet.controller.ControllerUsuario;
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


                cadastrarUsuario(id);
                this.finish();

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

    private void cadastrarUsuario(int id) {
        String nome = binding.txtNome.getText().toString();
        String email = binding.txtEmail.getText().toString();
        String cpf = binding.txtCpf.getText().toString();

        usuario.setId(id);
        usuario.setCpf(cpf);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setFoto(fotoCarregada);
        ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia(this);


        controllerUsuario.atualizar(usuario);

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
}