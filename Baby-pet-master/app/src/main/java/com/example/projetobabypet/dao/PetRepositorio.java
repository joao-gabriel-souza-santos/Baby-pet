package com.example.projetobabypet.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetobabypet.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetRepositorio {

    Helper helper;

    public PetRepositorio(Context context){
        helper = new Helper(context);
    }

    public long cadastrarPet(Pet pet){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_nome_pet, pet.getNome());
        values.put(Helper.coluna_raca_pet, pet.getRaca());
        values.put(Helper.coluna_idade_pet, pet.getNome());
         long id = db.insert(Helper.nome_tabela_pet, Helper.coluna_horas_pet_bebe + ", " +
                Helper.coluna_horas_pet_bebeu + ", " +
                Helper.coluna_horas_pet_come + ", " +
                Helper.coluna_horas_pet_comeu + "," +
                Helper.coluna_qtde_pet_agua + ", " +
                Helper.coluna_qtde_pet_racao,values);
         return id;
    }


    public List<Pet> buscarTodosPets (){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + Helper.nome_tabela_pet + ";";

        Cursor cursor = db.rawQuery(sql, null);

        List<Pet> pets = new ArrayList<>();
        while (cursor.moveToNext()){
            int idPet = cursor.getInt(0);
            int id_usuario = cursor.getInt(1);
            String nome = cursor.getString(2);
            String raca = cursor.getString(3);
            String sexo = cursor.getString(4);
            int idade = cursor.getInt(5);
            String horasPetBebe = cursor.getString(6);
            String horasPetBebeu = cursor.getString(7);
            String horasPetCome = cursor.getString(8);
            String horasPetComeu = cursor.getString(9);
            int qtde_agua = cursor.getInt(10);
            int qtde_racao = cursor.getInt(11);
            Pet pet = new Pet(nome, sexo, raca, horasPetCome, horasPetComeu, horasPetBebe, horasPetBebeu, idPet, id_usuario, idade, qtde_racao, qtde_agua);
            pets.add(pet);
        }
        return pets;
    }
}
