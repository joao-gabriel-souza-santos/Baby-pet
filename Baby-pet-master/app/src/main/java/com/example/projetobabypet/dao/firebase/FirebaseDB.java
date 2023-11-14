package com.example.projetobabypet.dao.firebase;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.projetobabypet.activities.HomeActivity;
import com.example.projetobabypet.activities.Login;
import com.example.projetobabypet.activities.cadastro.CadastrarPetActivity;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.model.Compromisso;
import com.example.projetobabypet.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FirebaseDB {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ControllerUsuario c;
    Context context;
    String token;


    public FirebaseDB(Context context) {

        c = ControllerUsuario.getInstancia(context);
        mAuth = FirebaseAuth.getInstance();
        this.context = context;
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");
    }
    public void cadastrarUsuario(Dialog dialog, String email, String senha, String nome, String cpf, String foto, CadastroCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        Bitmap fotoBitpmap = string_para_bitmap(foto);
                        Usuario usuario = salvarDadosUsuario(userId, nome, cpf, email, senha, foto);
                        usuario.setFoto(fotoBitpmap);
                        c.cadastrar(usuario, context);
                        callback.onCadastroSucesso();
                        Toast.makeText(context, "Usuário cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(context, CadastrarPetActivity.class);
                        it.putExtra("email", email);
                        ((Activity) context).startActivity(it);
                        ((Activity) context).finish();
                        dialog.cancel();
                    } else {
                        dialog.cancel();
                        AlertDialog.Builder caixademsg = new AlertDialog.Builder(context); //cria uma caixa de alerta
                        caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                        caixademsg.setMessage("Email inválido"); //coloca a mensagem da caixa
                        caixademsg.show(); //exibe a caixa pro usuario

                        callback.onCadastroFalha();
                    }
                });

    }

    public void verificarExistenciaUsuario(SharedPreferences sp,Dialog dialog, String email, String senha, CadastroCallback callback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            recuperarDadosUsuario(sp,userId, context, dialog);
                            callback.onCadastroSucesso();

                        } else {
                            exibirAlertDialog("Erro", "Falha na verificação de e-mail e senha: " + task.getException().getMessage());
                        }
                    }
                });
    }


    private void recuperarDadosUsuario(SharedPreferences sp,String userId, Context context, Dialog dialog) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuarios").child(userId);


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // O usuário existe, recuperar dados
                    String cpf = dataSnapshot.child("cpf").getValue(String.class);
                    String nome = dataSnapshot.child("nome").getValue(String.class);
                    String senha = dataSnapshot.child("senha").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String stringFoto = dataSnapshot.child("stringFoto").getValue(String.class);


                    Bitmap foto = string_para_bitmap(stringFoto);
                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    usuario.setCpf(cpf);
                    usuario.setFoto(foto);
                    c.cadastrar(usuario, context);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("email", email);
                    editor.putString("senha",senha);
                    editor.putBoolean("user_logado", true);
                    editor.apply();

                    Intent intent = new Intent(context.getApplicationContext(), HomeActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                    dialog.cancel();
                } else {
                    exibirAlertDialog("Erro", "Falha na verificação de e-mail e senha: ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Lidar com erro de leitura do banco de dados
                // Faça algo, se necessário
            }
        });

    }

    private Usuario salvarDadosUsuario(String userId, String nome, String cpf, String email, String senha, String foto) {
        Usuario usuario = new Usuario(nome, cpf, email, senha, foto);
        mDatabase.child(userId).setValue(usuario);


        return usuario;
    }

    public void alterarDadosUsuario(SharedPreferences sp, Dialog dialog, String novoNome, String novoCpf, String novoEmail, String antigoEmail, String senha, Bitmap novaFoto, CadastroCallback callback) {

        mAuth.signInWithEmailAndPassword(antigoEmail, senha)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        DatabaseReference usuarioRef = mDatabase.child(userId);

                        if (!antigoEmail.equals(novoEmail)) {
                                            mAuth.getCurrentUser().verifyBeforeUpdateEmail(novoEmail)
                                                    .addOnCompleteListener(emailUpdateTask -> {
                                                        if (emailUpdateTask.isComplete()) {
                                                            // Email alterado com sucesso
                                                            usuarioRef.child("email").setValue(novoEmail);
                                                            usuarioRef.child("nome").setValue(novoNome);
                                                            usuarioRef.child("cpf").setValue(novoCpf);
                                                            usuarioRef.child("senha").setValue(senha);
                                                            String novaFotoString = converte_bitmap_pra_string(novaFoto);
                                                            usuarioRef.child("stringFoto").setValue(novaFotoString);
                                                            Usuario usuario = new Usuario();
                                                            usuario.setNome(novoNome);
                                                            usuario.setEmail(novoEmail);
                                                            usuario.setCpf(novoCpf);
                                                            usuario.setFoto(novaFoto);
                                                            c.atualizar(usuario, antigoEmail);

                                                            callback.onCadastroSucesso();
                                                            exibirAlertDialog("Sucesso", "Usuário atualizado com sucesso.");

                                                            SharedPreferences.Editor editor = sp.edit();
                                                            editor.putString("email", novoEmail);
                                                            editor.commit();
                                                            editor.apply();

                                                            Intent intent = new Intent(context.getApplicationContext(), HomeActivity.class);
                                                            context.startActivity(intent);
                                                            ((Activity) context).finish();
                                                            dialog.cancel();
                                                        } else {
                                                            exibirAlertDialog("Erro", emailUpdateTask.getException().getMessage());
                                                        }
                                                    });

                        } else {
                            usuarioRef.child("email").setValue(novoEmail);
                            usuarioRef.child("nome").setValue(novoNome);
                            usuarioRef.child("cpf").setValue(novoCpf);

                            String novaFotoString = converte_bitmap_pra_string(novaFoto);
                            usuarioRef.child("stringFoto").setValue(novaFotoString);

                            Usuario usuario = new Usuario();
                            usuario.setNome(novoNome);
                            usuario.setEmail(novoEmail);
                            usuario.setCpf(novoCpf);
                            usuario.setFoto(novaFoto);
                            c.atualizar(usuario, antigoEmail);

                            callback.onCadastroSucesso();

                            Intent intent = new Intent(context.getApplicationContext(), HomeActivity.class);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                            dialog.cancel();
                        }
                    } else {
                        // Lidar com falha na verificação do e-mail e senha
                        exibirAlertDialog("Erro", "Falha na verificação de e-mail e senha: " + task.getException().getMessage());
                    }
                });
    }


    public void alterarSenha(Dialog dialog, Usuario usuario, String email,String senha, CadastroCallback callback){
        mAuth.signInWithEmailAndPassword(email, usuario.getSenha())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        mAuth.getCurrentUser().updatePassword(senha).addOnCompleteListener(task1 -> {
                            if(task1.isSuccessful()){
                                usuario.setSenha(senha);
                                c.atualizarUsuario(usuario);
                                callback.onCadastroSucesso();
                                dialog.cancel();
                                Intent intent = new Intent(context, Login.class);
                                ((Activity) context).startActivity(intent);
                                ((Activity)context).finish();
                            } else {
                                callback.onCadastroFalha();
                                AlertDialog.Builder caixademsg = new AlertDialog.Builder(context);
                                caixademsg.setTitle("Erro");
                                caixademsg.setMessage(task1.getException().getMessage());
                                caixademsg.show();
                            }
                        });
                    }
                });
    }



    private void exibirAlertDialog(String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            // Ação quando o botão "OK" é pressionado
        });
        builder.show();
    }


    public void token(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                this.token = task.getResult();
            }
        });
    }

  public  void enviaNotificacaoFirebase(Compromisso compromisso){

        try {
            token();
            JSONObject jsonObject = new JSONObject();

            JSONObject notificacao = new JSONObject();
            notificacao.put("title", compromisso.getNome());
            notificacao.put("body", compromisso.getDescricao());

             String id = mAuth.getCurrentUser().getUid();

            JSONObject dataobj = new JSONObject();
            dataobj.put("userId", id);

            jsonObject.put("notification", notificacao);
            jsonObject.put("data", dataobj);
            jsonObject.put("to", token);
            chamaApi(jsonObject);
        }catch (Exception e){

        }

    }

    public void chamaApi(JSONObject jsonObject){
        MediaType JSON = MediaType.get("application/json");
        OkHttpClient client = new OkHttpClient();

        String url = "https://fcm.googleapis.com/fcm/semd";
        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);
        Request request = new Request.Builder().
                url(url)
                .post(body)
                .header("Authorization", "Bearer AAAAO_fPyl4:APA91bFMp7vYkc-H8an-0moRjKKiV66Pxw5zacMfXQsLshL05b8FC8-uNVUtsGeU38HyjhdYoKWII66cGrOMVBidC-JJgGtv_NaWtAqPjyIWVIEIzhhmnmrzFRrmIyWu6YjjuiQBU1sR")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });
    }

    public static String converte_bitmap_pra_string(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public interface CadastroCallback {
        void onCadastroSucesso();

        void onCadastroFalha();
    }

    public static Bitmap string_para_bitmap(String base64String) {
        byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }


}
