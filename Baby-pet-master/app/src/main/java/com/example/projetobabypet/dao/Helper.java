package com.example.projetobabypet.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {
   public static final String nome_banco = "banco_userss";
   private static final int versao = 3;
    public static final String nome_tabela = "Usuario";
    public static final String coluna_id= "id";
    public static final String coluna_nome = "nome";
    public static final String coluna_cpf = "cpf";
    public static final String coluna_email = "email";
    public static final String coluna_senha = "senha";

    public static final String nome_tabela_pet = "Pet";
    public static final String coluna_id_pet = "id";
    public static final String coluna_pet_id_usuario = "id_usuario";
    public static final String coluna_nome_pet = "nome";
    public static final String coluna_raca_pet = "raca";
    public static final String coluna_sexo_pet = "sexo";

    public static final String coluna_idade_pet = "idade";
    public static final String coluna_horas_pet_come = "horas_pet_come";
    public static final String coluna_horas_pet_comeu = "horas_pet_comeu";
    public static final String coluna_horas_pet_bebe = "horas_pet_bebe";
    public static final String coluna_horas_pet_bebeu = "horas_pet_bebeu";
    public static final String coluna_qtde_pet_racao = "qtde_pet_racao";
    public static final String coluna_qtde_pet_agua = "qtde_pet_agua";
    public Helper(Context context) {
        super(context, nome_banco, null, versao);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + nome_tabela + "( " +
                                coluna_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                coluna_nome + " TEXT NOT NULL, " +
                                coluna_cpf + " TEXT UNIQUE NOT NULL, " +
                                coluna_email + " TEXT UNIQUE NOT NULL, " +
                                coluna_senha + " TEXT NOT NULL " + ");");

        String sql = "CREATE TABLE " + nome_tabela_pet + "( " +
                     coluna_id_pet + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                coluna_pet_id_usuario + " INTEGER NOT NULL, " +
                coluna_nome_pet + " TEXT NOT NULL, " +
                coluna_raca_pet + " TEXT NOT NULL, " +
                coluna_sexo_pet + " TEXT NOT NULL, " +
                coluna_idade_pet + " INTEGER NOT NULL, " +
                coluna_horas_pet_bebe + " TEXT, " +
                coluna_horas_pet_bebeu + " TEXT, " +
                coluna_horas_pet_come + " TEXT, " +
                coluna_horas_pet_comeu + " TEXT, " +
                coluna_qtde_pet_agua + " TEXT, " +
                coluna_qtde_pet_racao + " TEXT, " +
                " FOREIGN KEY(" + coluna_pet_id_usuario + ") REFERENCES " + nome_tabela + "(" + coluna_id + ") " +
                ");";
        sqLiteDatabase.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + nome_tabela + "; " ;
        String sql1 = "DROP TABLE IF EXISTS " + nome_tabela_pet + "; " ;
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
        onCreate(sqLiteDatabase);
    }
}
