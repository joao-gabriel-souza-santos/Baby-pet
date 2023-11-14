package com.example.projetobabypet.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.projetobabypet.model.Pet;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
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
        values.put(Helper.coluna_pet_email_usuario, pet.getEmail_usuario());
        values.put(Helper.coluna_nome_pet, pet.getNome());
        values.put(Helper.coluna_raca_pet, pet.getRaca());
        values.put(Helper.coluna_idade_pet, pet.getIdade());
        values.put(Helper.coluna_sexo_pet, pet.getSexo());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        pet.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(Helper.coluna_foto_pet, stream.toByteArray());
         long id = db.insert(Helper.nome_tabela_pet,
                Helper.coluna_qtde_pet_agua + ", " +
                Helper.coluna_qtde_pet_racao,values);
        db.close();
        helper.close();
         return id;


    }
    public long cadastrar_novo_Pet(Pet pet){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_pet_email_usuario, pet.getEmail_usuario());
        values.put(Helper.coluna_nome_pet, pet.getNome());
        values.put(Helper.coluna_raca_pet, pet.getRaca());
        values.put(Helper.coluna_idade_pet, pet.getIdade());
        values.put(Helper.coluna_sexo_pet, pet.getSexo());

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        pet.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(Helper.coluna_foto_pet, stream.toByteArray());
        long id = db.insert(Helper.nome_tabela_pet,

                Helper.coluna_qtde_pet_agua + ", " +
                Helper.coluna_qtde_pet_racao,values);
        db.close();
        helper.close();
        return id;
    }


    public List<Pet> buscarTodosPets ()throws Exception{
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + Helper.nome_tabela_pet + ";";

        Cursor cursor = db.rawQuery(sql, null);
        Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
        field.setAccessible(true);
        field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        List<Pet> pets = new ArrayList<>();
        while (cursor.moveToNext()){
            int idPet = cursor.getInt(0);
            String email_usuario = cursor.getString(1);
            String nome = cursor.getString(2);
            String raca = cursor.getString(3);
            String sexo = cursor.getString(4);
            int idade = cursor.getInt(5);
            byte[] stream = cursor.getBlob(6);
            int qtde_agua = cursor.getInt(7);
            int qtde_racao = cursor.getInt(8);
            Bitmap foto = BitmapFactory.decodeByteArray(stream, 0, stream.length);
            Pet pet = new Pet();
            pet.setEmail_usuario(email_usuario);
            pet.setId(idPet);
            pet.setNome(nome);
            pet.setRaca(raca);
            pet.setSexo(sexo);
            pet.setIdade(idade);
            pet.setFoto(foto);
            pets.add(pet);
        }
        db.close();
        helper.close();
        return pets;
    }

    public List<Pet> buscar_todos_pets_do_usuario(String email) throws Exception{
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + Helper.nome_tabela_pet + " WHERE " + Helper.coluna_pet_email_usuario + "= ?";

        Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(email) });
        Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
        field.setAccessible(true);
        field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        List<Pet> pets = new ArrayList<>();
        while (cursor.moveToNext()){
            int idPet = cursor.getInt(0);
            String email_usuario = cursor.getString(1);
            String nome = cursor.getString(2);
            String raca = cursor.getString(3);
            String sexo = cursor.getString(4);
            int idade = cursor.getInt(5);
            byte[] stream = cursor.getBlob(6);
            String qtde_agua = cursor.getString(7);
            String qtde_racao = cursor.getString(8);
            Bitmap foto = BitmapFactory.decodeByteArray(stream, 0, stream.length);
            Pet pet = new Pet(nome, sexo, raca, idPet,email_usuario, idade, foto);
            pets.add(pet);
        }
        db.close();
        helper.close();
        return pets;
    }

    public void atualizarPet(Pet pet){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_pet_email_usuario, pet.getEmail_usuario());
        values.put(Helper.coluna_nome_pet, pet.getNome());
        values.put(Helper.coluna_raca_pet, pet.getRaca());
        values.put(Helper.coluna_idade_pet, pet.getIdade());
        values.put(Helper.coluna_sexo_pet, pet.getSexo());
        db.update(Helper.nome_tabela_pet, values, Helper.coluna_id_pet + " =?", new String[]{String.valueOf(pet.getId())});
        db.close();
        helper.close();
    }

    public void deletar(Pet pet){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(Helper.nome_tabela_pet, Helper.coluna_id_pet + "=?", new String[]{
                String.valueOf(pet.getId())
        });
    }
}
