package com.example.projetobabypet.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {

    //TABELA USUARIO
   public static final String nome_banco = "banco_userss";
   private static final int versao = 1;
    public static final String nome_tabela = "Usuario";
    public static final String coluna_id_usuario = "id";
    public static final String coluna_nome = "nome";
    public static final String coluna_cpf = "cpf";
    public static final String coluna_email = "email";
    public static final String coluna_senha = "senha";
    public static final String coluna_foto_usuario = "foto";

    public  static final String coluna_qtde_usuario_pets = "qtde_pets";

    //TABELA PET
    public static final String nome_tabela_pet = "Pet";
    public static final String coluna_id_pet = "id";
    public static final String coluna_pet_id_usuario = "id_usuario";
    public static final String coluna_nome_pet = "nome";
    public static final String coluna_raca_pet = "raca";
    public static final String coluna_sexo_pet = "sexo";
    public static final String coluna_idade_pet = "idade";
    public static final String coluna_horas_pet_come_manha = "horas_pet_come_manha";
    public static final String coluna_horas_pet_come_tarde = "horas_pet_come_tarde";
    public static final String coluna_horas_pet_come_noite = "horas_pet_come_noite";
    public static final String coluna_horas_pet_comeu = "horas_pet_comeu";
    public static final String coluna_horas_pet_bebe_manha = "horas_pet_bebe_manha";
    public static final String coluna_horas_pet_bebe_tarde = "horas_pet_bebe_tarde";
    public static final String coluna_horas_pet_bebe_noite = "horas_pet_bebe_noite";
    public static final String coluna_horas_pet_bebeu = "horas_pet_bebeu";
    public static final String coluna_qtde_pet_racao = "qtde_pet_racao";
    public static final String coluna_qtde_pet_agua = "qtde_pet_agua";
    public static final String coluna_foto_pet = "foto";

    //TABELA COMPROMISSO

    public static final String nome_tabela_compromisso = "Compromisso";
    public static final String coluna_id_compromisso = "id";
    public static final String coluna_id_usuario_compromisso = "id_usuario";
    public static final String coluna_nome_compromisso = "nome";
    public static final String coluna_descricao_compromisso = "descricao";
    public static final  String coluna_repeticao_compromisso = "repeticao";
    public static final String coluna_data_compromisso = "data";
    public static final String coluna_hora_compromisso = "hora";

    public Helper(Context context) {
        super(context, nome_banco, null, versao);

    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



        sqLiteDatabase.execSQL("CREATE TABLE " + nome_tabela + "( " +
                coluna_id_usuario + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                coluna_nome + " TEXT NOT NULL, " +
                                coluna_cpf + " TEXT UNIQUE NOT NULL, " +
                                coluna_email + " TEXT UNIQUE NOT NULL, " +
                                coluna_senha + " TEXT NOT NULL, " +
                                coluna_foto_usuario + " BLOB NOT NULL, " +
                                coluna_qtde_usuario_pets + " INTEGER NOT NULL" + ");");

        String sql = "CREATE TABLE " + nome_tabela_pet + "( " +
                     coluna_id_pet + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                coluna_pet_id_usuario + " INTEGER, " +
                coluna_nome_pet + " TEXT NOT NULL, " +
                coluna_raca_pet + " TEXT NOT NULL, " +
                coluna_sexo_pet + " TEXT NOT NULL, " +
                coluna_idade_pet + " INTEGER NOT NULL, " +
                coluna_foto_pet + " BLOB NOT NULL, " +
                coluna_horas_pet_bebe_manha + " TEXT, " +
                coluna_horas_pet_bebe_tarde + " TEXT, " +
                coluna_horas_pet_bebe_noite + " TEXT, " +
                coluna_horas_pet_bebeu + " TEXT, " +
                coluna_horas_pet_come_manha + " TEXT, " +
                coluna_horas_pet_come_tarde + " TEXT, " +
                coluna_horas_pet_come_noite + " TEXT, " +
                coluna_horas_pet_comeu + " TEXT, " +
                coluna_qtde_pet_agua + " TEXT, " +
                coluna_qtde_pet_racao + " TEXT " +
                "); ";

        String sql2 = " CREATE TABLE " + nome_tabela_compromisso + "( "  +
                coluna_id_compromisso + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                coluna_id_usuario_compromisso + " INTEGER, " +
                coluna_nome_compromisso + " TEXT, " +
                coluna_hora_compromisso + " TEXT NOT NULL," +
                coluna_descricao_compromisso + " TEXT, " +
                coluna_repeticao_compromisso + " TEXT, " +
                coluna_data_compromisso + " NUMERIC " +
                ");";



        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + nome_tabela + "; " ;
        String sql1 = "DROP TABLE IF EXISTS " + nome_tabela_pet + "; " ;
        String sql2= "DROP TABLE IF EXISTS " + nome_tabela_compromisso + "; " ;
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);


        onCreate(sqLiteDatabase);
    }
}
