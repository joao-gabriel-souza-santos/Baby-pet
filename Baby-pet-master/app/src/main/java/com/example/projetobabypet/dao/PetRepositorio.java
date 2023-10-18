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
        values.put(Helper.coluna_pet_id_usuario, pet.getIdUsuario());
        values.put(Helper.coluna_nome_pet, pet.getNome());
        values.put(Helper.coluna_raca_pet, pet.getRaca());
        values.put(Helper.coluna_idade_pet, pet.getNome());
        values.put(Helper.coluna_sexo_pet, pet.getSexo());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        pet.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(Helper.coluna_foto_pet, stream.toByteArray());
         long id = db.insert(Helper.nome_tabela_pet, Helper.coluna_horas_pet_bebe_manha + ", " +
                 Helper.coluna_horas_pet_bebe_tarde + ", " +
                 Helper.coluna_horas_pet_bebe_noite + ", " +
                 Helper.coluna_horas_pet_bebeu + ", " +
                Helper.coluna_horas_pet_come_manha + ", " +
                 Helper.coluna_horas_pet_come_tarde + ", " +
                 Helper.coluna_horas_pet_come_noite + ", " +
                Helper.coluna_horas_pet_comeu + "," +
                Helper.coluna_qtde_pet_agua + ", " +
                Helper.coluna_qtde_pet_racao,values);
        db.close();
        helper.close();
         return id;


    }
    public long cadastrar_novo_Pet(Pet pet){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_pet_id_usuario, pet.getIdUsuario());
        values.put(Helper.coluna_nome_pet, pet.getNome());
        values.put(Helper.coluna_raca_pet, pet.getRaca());
        values.put(Helper.coluna_idade_pet, pet.getNome());
        values.put(Helper.coluna_sexo_pet, pet.getSexo());
        values.put(Helper.coluna_horas_pet_bebe_manha, pet.getHoras_agua_manha());
        values.put(Helper.coluna_horas_pet_bebe_tarde, pet.getHoras_agua_tarde());
        values.put(Helper.coluna_horas_pet_bebe_noite, pet.getHoras_agua_noite());
        values.put(Helper.coluna_horas_pet_come_manha, pet.getHoras_comida_manha());
        values.put(Helper.coluna_horas_pet_come_tarde, pet.getHoras_comida_tarde());
        values.put(Helper.coluna_horas_pet_come_noite, pet.getHoras_comida_noite());

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        pet.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(Helper.coluna_foto_pet, stream.toByteArray());
        long id = db.insert(Helper.nome_tabela_pet,
                Helper.coluna_horas_pet_bebeu + ", " +
                Helper.coluna_horas_pet_comeu + "," +
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
            int id_usuario = cursor.getInt(1);
            String nome = cursor.getString(2);
            String raca = cursor.getString(3);
            String sexo = cursor.getString(4);
            int idade = cursor.getInt(5);
            byte[] stream = cursor.getBlob(6);
            String horasAguaManha = cursor.getString(7);
            String horasAguaTarde = cursor.getString(8);
            String horasAguaNoite = cursor.getString(9);
            String horasPetBebeu = cursor.getString(10);
            String horasCafe = cursor.getString(11);
            String horasAlmoco = cursor.getString(12);
            String horasNoite = cursor.getString(13);
            String horasPetComeu = cursor.getString(14);
            int qtde_agua = cursor.getInt(15);
            int qtde_racao = cursor.getInt(16);
            Bitmap foto = BitmapFactory.decodeByteArray(stream, 0, stream.length);
            Pet pet = new Pet(nome, sexo, raca, horasCafe, horasAlmoco, horasNoite, horasAguaManha, horasAguaTarde, horasAguaNoite, idPet,id_usuario, idade, foto);
            pets.add(pet);
        }
        db.close();
        helper.close();
        return pets;
    }

    public List<Pet> buscar_todos_pets_do_usuario(int id) throws Exception{
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + Helper.nome_tabela_pet + " WHERE " + Helper.coluna_pet_id_usuario + "= ?";

        Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(id) });
        Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
        field.setAccessible(true);
        field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        List<Pet> pets = new ArrayList<>();
        while (cursor.moveToNext()){
            int idPet = cursor.getInt(0);
            int id_usuario = cursor.getInt(1);
            String nome = cursor.getString(2);
            String raca = cursor.getString(3);
            String sexo = cursor.getString(4);
            int idade = cursor.getInt(5);
            byte[] stream = cursor.getBlob(6);
            String horasPetBebe = cursor.getString(7);
            String horasPetBebeu = cursor.getString(8);
            String horasPetCome = cursor.getString(9);
            String horasPetComeu = cursor.getString(10);
            int qtde_agua = cursor.getInt(11);
            int qtde_racao = cursor.getInt(12);
            Bitmap foto = BitmapFactory.decodeByteArray(stream, 0, stream.length);
            Pet pet = new Pet(nome, sexo, raca, horasPetCome, horasPetComeu, horasPetBebe, horasPetBebeu, idPet, id_usuario, idade, qtde_racao, qtde_agua, foto);
            pets.add(pet);
        }
        db.close();
        helper.close();
        return pets;
    }

    public void atualizarPet(Pet pet){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Helper.coluna_pet_id_usuario, pet.getIdUsuario());
        values.put(Helper.coluna_nome_pet, pet.getNome());
        values.put(Helper.coluna_raca_pet, pet.getRaca());
        values.put(Helper.coluna_idade_pet, pet.getNome());
        values.put(Helper.coluna_sexo_pet, pet.getSexo());
        values.put(Helper.coluna_horas_pet_bebe_manha, pet.getHoras_agua_manha());
        values.put(Helper.coluna_horas_pet_bebe_tarde, pet.getHoras_agua_tarde());
        values.put(Helper.coluna_horas_pet_bebe_noite, pet.getHoras_agua_noite());
        values.put(Helper.coluna_horas_pet_come_manha, pet.getHoras_comida_manha());
        values.put(Helper.coluna_horas_pet_come_tarde, pet.getHoras_comida_tarde());
        values.put(Helper.coluna_horas_pet_come_noite, pet.getHoras_comida_noite());
        db.update(Helper.nome_tabela_pet, values, Helper.coluna_id_pet + " =?", new String[]{String.valueOf(pet.getId())});
        db.close();
        helper.close();
    }
}
