package com.example.projetobabypet.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetobabypet.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorio {

    private  Helper helper;
    SQLiteDatabase sqLiteDatabase;

    public  CategoriaRepositorio(Context context){
        helper = new Helper(context);

    }

    public void cadastrar(Categoria categoria){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Helper.coluna_nome_categoria, categoria.getNome());
        values.put(Helper.coluna_id_usuario_categoria, categoria.getId_usuario());
        db.insert(Helper.nome_tabela_categoria, null, values);

        db.close();
        helper.close();
    }

    public List<Categoria> buscarTodasCategorias(int id_usuario){
        SQLiteDatabase db = helper.getReadableDatabase();
        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT * FROM " + Helper.nome_tabela_categoria + " WHERE " + Helper.coluna_id_usuario_categoria + "=? ";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id_usuario)});
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            int id_us = cursor.getInt(2);

            Categoria categoria = new Categoria(id, nome, id_us);
            categorias.add(categoria);
        }
         db.close();
        helper.close();
        cursor.close();
        return categorias;

    }

    public void deletar(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(Helper.nome_tabela_categoria, Helper.coluna_id_compromisso + "=?", new String[]{String.valueOf(id)});
    }

}
