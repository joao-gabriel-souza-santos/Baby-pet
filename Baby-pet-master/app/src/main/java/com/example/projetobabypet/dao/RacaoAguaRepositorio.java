package com.example.projetobabypet.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetobabypet.model.RacaoEAgua;

import java.util.ArrayList;
import java.util.List;

public class RacaoAguaRepositorio {

   private Context context;
    private List<RacaoEAgua> racaoaguas;
    SQLiteDatabase db;
    Helper helper;

    public  RacaoAguaRepositorio(Context context){
        this.context = context;
        helper = new Helper(context);
        racaoaguas = new ArrayList<>();
    }

    public void cadastrar(RacaoEAgua racaoEAgua){
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_email_usuario_racaoagua, racaoEAgua.getEmail_usuario());
        values.put(Helper.coluna_hora_racaoAgua, racaoEAgua.getHora());
        values.put(Helper.coluna_aguaouracao, racaoEAgua.getAguaouracao());
        db.insert(Helper.nome_tabela_racaoagua, null,values);
    }

    public List<RacaoEAgua> buscarTodasRacaoAgua(String email){

        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + Helper.nome_tabela_racaoagua;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String email_usuario = cursor.getString(1);
            String racaoOuagua = cursor.getString(2);
            String hora = cursor.getString(3);

            RacaoEAgua racaoEAgua = new RacaoEAgua(id, email_usuario, racaoOuagua, hora);
            racaoaguas.add(racaoEAgua);
        }
        db.close();
        helper.close();
        return racaoaguas;
    }

}
