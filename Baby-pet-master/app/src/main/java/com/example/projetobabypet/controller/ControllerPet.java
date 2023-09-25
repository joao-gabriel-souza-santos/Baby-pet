package com.example.projetobabypet.controller;

import android.content.Context;

import com.example.projetobabypet.dao.Helper;
import com.example.projetobabypet.dao.PetRepositorio;
import com.example.projetobabypet.model.Pet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerPet {

    private int proxId;
    public static List<Pet> pets;
    private static ControllerPet instancia = null;
    Context context;
    PetRepositorio db;

    private ControllerPet(Context context){
        this.context = context;
        pets = new ArrayList<>();
    }



    public static ControllerPet getInstancia(Context context){
        if(instancia==null)
            instancia = new ControllerPet(context);
        return instancia;
    }

    public void cadastrar(Pet pet){
        db = new PetRepositorio(context);
        db.cadastrarPet(pet);
    }
    public List<Pet> buscarTodos(){
        db = new PetRepositorio(context);
        pets = db.buscarTodosPets();
        return pets;
    }
}
