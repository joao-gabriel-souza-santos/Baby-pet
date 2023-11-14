package com.example.projetobabypet.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.projetobabypet.model.Usuario;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {
    private Helper con;
    private SQLiteDatabase db;

    public UsuarioRepositorio(Context contexto){
        con = new Helper(contexto);
    }

    public long inserirUsuario (Usuario usuario){
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_nome, usuario.getNome());
        values.put(Helper.coluna_cpf, usuario.getCpf());
        values.put(Helper.coluna_email,usuario.getEmail());
        values.put(Helper.coluna_senha, usuario.getSenha());
        values.put(Helper.coluna_qtde_agua_racaoagua, 1);
        values.put(Helper.coluna_maxracao, 1);
        values.put(Helper.coluna_soma_agua, 1);
        values.put(Helper.coluna_maxagua,1);
        values.put(Helper.coluna_soma_racao,1);
        values.put(Helper.coluna_qtde_racao_racaoagua,1);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        usuario.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(Helper.coluna_foto_usuario, stream.toByteArray());
        long id = db.insert(Helper.nome_tabela, null, values);
        db.close();
        con.close();
        return id;
    }


    public long atualizarUsuario(Usuario usuario ){
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_senha, usuario.getSenha());
        long id =db.update(Helper.nome_tabela, values, Helper.coluna_email + "= ?", new String[]{String.valueOf(usuario.getEmail())});
        db.close();
        con.close();
        return id;
    }
    public long atualizar(Usuario usuario, String emailAntigo ){
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_nome, usuario.getNome());
        values.put(Helper.coluna_cpf, usuario.getCpf());
        values.put(Helper.coluna_email,usuario.getEmail());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        usuario.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(Helper.coluna_foto_usuario, stream.toByteArray());
        long id =db.update(Helper.nome_tabela, values, Helper.coluna_email + "= ?", new String[]{emailAntigo});
        db.close();
        con.close();
        return id;
    }


    public void atualizaqtdeRacao(String email, int valor){
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_qtde_racao_racaoagua, valor);
        db.update(Helper.nome_tabela,values,Helper.coluna_email + "=?", new String[]{email});
    }
    public void atualizaqtdeAgua(String email, int valor){
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_qtde_agua_racaoagua, valor);
        db.update(Helper.nome_tabela,values,Helper.coluna_email + "=?", new String[]{email});
    }
    public void atualizaSomaRacao(String email, int valor){
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_soma_racao, valor);
        db.update(Helper.nome_tabela,values,Helper.coluna_email + "=?", new String[]{email});
    }
    public void atualizaSomaAgua(String email, int valor){
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_soma_agua, valor);
        db.update(Helper.nome_tabela,values,Helper.coluna_email + "=?", new String[]{email});
    }
    public void atualizamaxAgua(String email, int valor){
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_maxagua, valor);
        db.update(Helper.nome_tabela,values,Helper.coluna_email + "=?", new String[]{email});
    }
    public void atualizaMaxRacao(String email, int valor){
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_maxracao, valor);
        db.update(Helper.nome_tabela,values,Helper.coluna_email + "=?", new String[]{email});
    }

    public List<Usuario> listar() throws IllegalAccessException, NoSuchFieldException {

        db = con.getReadableDatabase();
        List<Usuario> lista = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Helper.nome_tabela, null);
        Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
        field.setAccessible(true);
        field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        while (cursor.moveToNext()){
            String email = cursor.getString(0);
            String nome = cursor.getString(1);
            String cpf = cursor.getString(2);
            String senha = cursor.getString(3);
            byte[] stream = cursor.getBlob(4);
            int qtde_racao = cursor.getInt(5);
            int qtde_agua = cursor.getInt(6);
            int somaagua = cursor.getInt(7);
            int somaracao = cursor.getInt(8);
            int maxagua = cursor.getInt(9);
            int maxracao = cursor.getInt(10);
            Bitmap foto = BitmapFactory.decodeByteArray(stream, 0, stream.length);
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setNome(nome);
            usuario.setSenha(senha);
            usuario.setCpf(cpf);
            usuario.setFoto(foto);
            usuario.setQtde_agua(qtde_agua);
            usuario.setQtde_racao(qtde_racao);
            usuario.setSomaagua(somaagua);
            usuario.setSomaracao(somaracao);
            usuario.setMaxAgua(maxagua);
            usuario.setMaxRacao(maxracao);
            lista.add(usuario);
        }
        db.close();
        con.close();
        return lista;
    }
}
