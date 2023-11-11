package com.example.projetobabypet.dao.firebase;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class FirebaseDB {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ControllerUsuario c;
    Context context;



    public FirebaseDB(Context context) {

        c = ControllerUsuario.getInstancia(context);
        mAuth = FirebaseAuth.getInstance();
        this.context = context;
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");
    }


    public void cadastrarUsuario(String email, String senha,  String nome,  String cpf,  Bitmap foto, CadastroCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String stringFoto = converte_bitmap_pra_string(foto);
                        String userId = mAuth.getCurrentUser().getUid();
                        c.cadastrar(salvarDadosUsuario(userId, nome, cpf, email, stringFoto), context);
                        callback.onCadastroSucesso();
                        Toast.makeText(context, "Usuário cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder caixademsg = new AlertDialog.Builder(context); //cria uma caixa de alerta
                        caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                        caixademsg.setMessage(task.getException().getMessage()); //coloca a mensagem da caixa
                        caixademsg.show(); //exibe a caixa pro usuario

                        callback.onCadastroFalha();
                    }
                });

    }

    private Usuario salvarDadosUsuario(String userId, String nome, String cpf, String email, String foto) {
        Usuario usuario = new Usuario(nome, cpf, email, foto);
        mDatabase.child(userId).setValue(usuario);
        return usuario;
    }

    public String converte_bitmap_pra_string(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public interface CadastroCallback {
        void onCadastroSucesso();
        void onCadastroFalha();
    }
}
