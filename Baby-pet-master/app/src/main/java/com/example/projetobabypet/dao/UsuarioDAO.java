package com.example.projetobabypet.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetobabypet.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Conexao con;
    private SQLiteDatabase db;

    public UsuarioDAO(Context contexto){
        con = new Conexao(contexto);
        db = con.getWritableDatabase();

    }

    public long inserirUsuario (Usuario usuario){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", usuario.getNome());
        contentValues.put("cpf", usuario.getCpf());
        contentValues.put("email",usuario.getEmail());
        contentValues.put("senha", usuario.getSenha());

        return db.insert("usuario", null, contentValues);
    }

    public List<Usuario> listar(){
        List<Usuario> lista = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM usuario", null);

        while (cursor.moveToNext()){
            lista.add(new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4)));
        }
        return lista;
    }
}
