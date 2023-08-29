package com.example.projetobabypet.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {
   private static final String nome = "banco.db";
   private static final int versao = 1;


    public Conexao(@Nullable Context context) {
        super(context, nome, null, versao);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE usuario(id integer primary key autoincrement, nome varchar(300), cpf varchar(14) unique," +
                " email varchar(200) unique, senha varchar(50))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
