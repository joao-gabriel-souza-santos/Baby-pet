package com.example.projetobabypet.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {
   public static final String nome_banco = "banco_usuarios";
   private static final int versao = 1;
    public static final String nome_tabela = "Usuario";
    public static final String coluna_id= "id";
    public static final String coluna_nome = "nome";
    public static final String coluna_cpf = "cpf";
    public static final String coluna_email = "email";
    public static final String coluna_senha = "senha";


    public Helper(Context context) {
        super(context, nome_banco, null, versao);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + nome_tabela + "( " +
                                coluna_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                coluna_nome + " TEXT NOT NULL, " +
                                coluna_cpf + " TEXT UNIQUE NOT NULL, " +
                                coluna_email + " TEXT UNIQUE NOT NULL, " +
                                coluna_senha + " TEXT NOT NULL " + ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + nome_tabela + "; " ;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
