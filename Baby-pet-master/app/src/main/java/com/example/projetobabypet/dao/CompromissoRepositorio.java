package com.example.projetobabypet.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetobabypet.model.Compromisso;
import com.example.projetobabypet.model.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class CompromissoRepositorio {

    Helper helper;

    public CompromissoRepositorio(Context context){
        helper = new Helper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

    }

    public void cadastrar(Compromisso compromisso){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Helper.coluna_nome_compromisso, compromisso.getNome());
        values.put(Helper.coluna_id_usuario_compromisso, compromisso.getId_usuario());
        values.put(Helper.coluna_descricao_compromisso, compromisso.getDescricao());
        values.put(Helper.coluna_hora_compromisso, compromisso.getHora());
        values.put(Helper.coluna_data_compromisso, compromisso.getData());
        db.insert(Helper.nome_tabela_compromisso, Helper.coluna_repeticao_compromisso, values);

        db.close();
        helper.close();

    }

    public void cadastrarCompromissoCategoria(Compromisso compromisso){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Helper.coluna_nome_compromisso, compromisso.getNome());
        values.put(Helper.coluna_id_usuario_compromisso, compromisso.getId_usuario());
        values.put(Helper.coluna_descricao_compromisso, compromisso.getDescricao());
        values.put(Helper.coluna_hora_compromisso, compromisso.getHora());
        values.put(Helper.coluna_data_compromisso, compromisso.getData());
        values.put(Helper.coluna_id_categoria_compromisso, compromisso.getId_categoria());
        db.insert(Helper.nome_tabela_compromisso, Helper.coluna_repeticao_compromisso, values);

        db.close();
        helper.close();

    }
    public void cadastrarNotificacao(Compromisso compromisso){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Helper.coluna_descricao_compromisso, compromisso.getDescricao());
        values.put(Helper.coluna_hora_compromisso, compromisso.getHora());
        values.put(Helper.coluna_id_usuario_compromisso, compromisso.getId_usuario());
        db.insert(Helper.nome_tabela_compromisso, " "+Helper.coluna_nome_compromisso + "," +Helper.coluna_repeticao_compromisso + ", " +
                Helper.coluna_data_compromisso , values);

        db.close();
        helper.close();

    }

    public List<Compromisso> buscarTodosCompromissos(){
       SQLiteDatabase db = helper.getWritableDatabase();
        List<Compromisso> compromissos = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +  Helper.nome_tabela_compromisso,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int id_usuario = cursor.getInt(1);
            String nome = cursor.getString(2);
            String hora = cursor.getString(3);
            String descricao = cursor.getString(4);
            String repreticao = cursor.getString(5);
            String data = cursor.getString(6);
        Compromisso compromisso = new Compromisso(id, id_usuario, nome, descricao, repreticao, hora, data);
        compromissos.add(compromisso);

        }
    db.close();
        helper.close();
        return compromissos;
    }
    public List<Compromisso> buscarTodosCompromissos_usuario(int idUsuario){
        SQLiteDatabase db = helper.getWritableDatabase();
        List<Compromisso> compromissos = new ArrayList<>();
        String sql ="SELECT * FROM " + Helper.nome_tabela_compromisso + " WHERE " + Helper.coluna_id_usuario_compromisso + "= ?";
        Cursor cursor = db.rawQuery(sql,new String[] { String.valueOf(idUsuario) });
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int id_usuario = cursor.getInt(1);
            int id_categoria = cursor.getInt(2);
            String nome = cursor.getString(3);
            String hora = cursor.getString(4);
            String descricao = cursor.getString(5);
            String repreticao = cursor.getString(6);
            String data =  cursor.getString(7);
            Compromisso compromisso = new Compromisso(id, id_usuario,id_categoria, nome, descricao, repreticao, hora, data);
            compromissos.add(compromisso);

        }
        db.close();
        helper.close();
        return compromissos;
    }

    public List<Compromisso> buscarTodosCompromissos_categoria(int idCategoria){
        SQLiteDatabase db = helper.getWritableDatabase();
        List<Compromisso> compromissos = new ArrayList<>();
        String sql ="SELECT * FROM " + Helper.nome_tabela_compromisso + " WHERE " + Helper.coluna_id_categoria_compromisso + "= ?";
        Cursor cursor = db.rawQuery(sql,new String[] { String.valueOf(idCategoria) });
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int id_usuario = cursor.getInt(1);
            int id_usuario_categoria = cursor.getInt(2);
            String nome = cursor.getString(3);
            String hora = cursor.getString(4);
            String descricao = cursor.getString(5);
            String repreticao = cursor.getString(6);
            String data =  cursor.getString(7);
            Compromisso compromisso = new Compromisso(id, id_usuario, id_usuario_categoria, nome, descricao, repreticao, hora, data);
            compromissos.add(compromisso);

        }
        db.close();
        helper.close();
        return compromissos;
    }

    public void atualizarCompromisso(Compromisso compromisso){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Helper.coluna_nome_compromisso, compromisso.getNome());
        values.put(Helper.coluna_id_usuario_compromisso, compromisso.getId_usuario());
        values.put(Helper.coluna_descricao_compromisso, compromisso.getDescricao());
        values.put(Helper.coluna_hora_compromisso, compromisso.getHora());
        values.put(Helper.coluna_data_compromisso, compromisso.getData());
        values.put(Helper.coluna_id_categoria_compromisso, compromisso.getId_categoria());
        db.update(Helper.nome_tabela_compromisso, values, Helper.coluna_id_compromisso + "=?",new String[]{String.valueOf(compromisso.getId())} );
        db.close();
        helper.close();
    }

    public void deletar(Compromisso compromisso){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(Helper.nome_tabela_compromisso,Helper.coluna_id_compromisso + "= ?", new String[]{String.valueOf(compromisso.getId())});
    }


}
