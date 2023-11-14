package com.example.projetobabypet.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {

    //TABELA USUARIO
   public static final String nome_banco = "banco_userss";
   private static final int versao = 6;
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
    public static final String coluna_pet_email_usuario = "email_usuario";
    public static final String coluna_nome_pet = "nome";
    public static final String coluna_raca_pet = "raca";
    public static final String coluna_sexo_pet = "sexo";
    public static final String coluna_idade_pet = "idade";

    public static final String coluna_horas_pet_comeu = "horas_pet_comeu";

    public static final String coluna_horas_pet_bebeu = "horas_pet_bebeu";
    public static final String coluna_qtde_pet_racao = "qtde_pet_racao";
    public static final String coluna_qtde_pet_agua = "qtde_pet_agua";
    public static final String coluna_foto_pet = "foto";

    //TABELA COMPROMISSO

    public static final String nome_tabela_compromisso = "Compromisso";
    public static final String coluna_id_compromisso = "id";
    public static final String coluna_email_usuario_compromisso = "email_usuario";
    public static  final String coluna_id_categoria_compromisso = "id_categoria";
    public static final String coluna_nome_compromisso = "nome";
    public static final String coluna_descricao_compromisso = "descricao";
    public static final  String coluna_repeticao_compromisso = "repeticao";
    public static final String coluna_data_compromisso = "data";
    public static final String coluna_hora_compromisso = "hora";


    //TABELA CATEGORIA
    public static  final String nome_tabela_categoria = "Categoria";
    public static  final String coluna_id_categoria = "id";
    public static  final String coluna_nome_categoria = "nome";
    public static  final String coluna_email_usuario_categoria = "email_usuario";

    //TABELA RACAO E AGUA
    public  static  final String nome_tabela_racaoagua = "RacaoAgua";

    public  static  final String coluna_email_usuario_racaoagua = "email_usuario";
    public  static  final String coluna_aguaouracao = "aguaOUracao";
    public  static final String coluna_hora_racaoAgua = "hora";


    public Helper(Context context) {
        super(context, nome_banco, null, versao);

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



        sqLiteDatabase.execSQL("CREATE TABLE " + nome_tabela + "( " +
                                coluna_email + " TEXT PRIMARY KEY, " +
                                coluna_nome + " TEXT NOT NULL, " +
                                coluna_cpf + " TEXT UNIQUE NOT NULL, " +
                                coluna_senha + " TEXT NOT NULL, " +
                                coluna_foto_usuario + " BLOB NOT NULL " +
                                 ");");

        String sql = "CREATE TABLE " + nome_tabela_pet + "( " +
                coluna_id_pet + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                coluna_pet_email_usuario + " TEXT, " +
                coluna_nome_pet + " TEXT NOT NULL, " +
                coluna_raca_pet + " TEXT NOT NULL, " +
                coluna_sexo_pet + " TEXT NOT NULL, " +
                coluna_idade_pet + " INTEGER NOT NULL, " +
                coluna_foto_pet + " BLOB NOT NULL, " +
                coluna_qtde_pet_agua + " TEXT, " +
                coluna_qtde_pet_racao + " TEXT " +
                "); ";

        String sql2 = " CREATE TABLE " + nome_tabela_compromisso + "( "  +
                coluna_id_compromisso + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                coluna_email_usuario_compromisso + " TEXT, " +
                coluna_id_categoria_compromisso + " INTEGER, " +
                coluna_nome_compromisso + " TEXT, " +
                coluna_hora_compromisso + " TEXT NOT NULL," +
                coluna_descricao_compromisso + " TEXT, " +
                coluna_repeticao_compromisso + " TEXT, " +
                coluna_data_compromisso + " TEXT " +
                ");";


        String sql3 = " CREATE TABLE " + nome_tabela_categoria + "(" +
                        coluna_id_categoria + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        coluna_nome_categoria + " TEXT, " +
                coluna_email_usuario_categoria + " TEXT " +
                ");";



        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + nome_tabela + "; " ;
        String sql1 = "DROP TABLE IF EXISTS " + nome_tabela_pet + "; " ;
        String sql2= "DROP TABLE IF EXISTS " + nome_tabela_compromisso + "; " ;
        String sql3 = "DROP TABLE IF EXISTS " + nome_tabela_categoria + ";";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);
        onCreate(sqLiteDatabase);
    }
}
