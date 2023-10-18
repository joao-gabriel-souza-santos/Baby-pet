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
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        usuario.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(Helper.coluna_foto_usuario, stream.toByteArray());
        values.put(Helper.coluna_qtde_usuario_pets, usuario.getQtde_pet());
        long id = db.insert(Helper.nome_tabela, null, values);


        db.close();
        con.close();
        return id;
    }

    public long atualizarUsuarioPorCpf(Usuario usuario ){
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("cpf", usuario.getCpf());
        values.put("email",usuario.getEmail());
        values.put("senha", usuario.getSenha());
        long id =  db.update(Helper.nome_tabela, values, Helper.coluna_cpf + "= ?", new String[]{usuario.getCpf()});
        db.close();
        con.close();
        return id;
    }
    public long atualizarUsuarioPorEmail(Usuario usuario ){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("cpf", usuario.getCpf());
        values.put("email",usuario.getEmail());
        values.put("senha", usuario.getSenha());
        long id =db.update(Helper.nome_tabela, values, Helper.coluna_email + "= ?", new String[]{usuario.getEmail()});
        db.close();
        con.close();
        return id;
    }


    public List<Usuario> listar() throws IllegalAccessException, NoSuchFieldException {

        db = con.getReadableDatabase();
        List<Usuario> lista = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Helper.nome_tabela, null);
        Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
        field.setAccessible(true);
        field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            String cpf = cursor.getString(2);
            String email = cursor.getString(3);
            String senha = cursor.getString(4);
            byte[] stream = cursor.getBlob(5);
            int qtde_pets = cursor.getInt(6);
            Bitmap foto = BitmapFactory.decodeByteArray(stream, 0, stream.length);
            Usuario usuario = new Usuario(id, nome, cpf, email, senha, foto, qtde_pets);
            lista.add(usuario);
        }
        db.close();
        con.close();
        return lista;
    }
}
