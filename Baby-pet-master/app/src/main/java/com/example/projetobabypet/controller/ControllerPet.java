package com.example.projetobabypet.controller;

import android.content.Context;

import com.example.projetobabypet.dao.PetRepositorio;
import com.example.projetobabypet.model.Pet;

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
    public List<Pet> buscarTodos() throws Exception{
        db = new PetRepositorio(context);
        pets = db.buscarTodosPets();
        return pets;
    }

    public List<Pet> buscar_todos_pets_usuario(String email) throws Exception{
        db = new PetRepositorio(context);
        pets = buscarTodos();
        List<Pet> pets_usuario  = new ArrayList<>();
        for(Pet pet  : pets){ //pra cada usuario na lista
            if(pet.getEmail_usuario().equals(email)){ //verifica se tem algum usuario com o email igual ao email recebido
                pets_usuario.add(pet);// se tiver então retorna verdadeiro
            }
        }
        return pets_usuario;
    }

    public List<Pet> teste(String email) throws Exception {
        db = new PetRepositorio(context);
        pets = buscar_todos_pets_usuario(email);
        return pets;
    }
    public void cadastrarNovoPet(Pet pet){
        db = new PetRepositorio(context);
        db.cadastrar_novo_Pet(pet);
    }
    public void atualizarPet(Pet pet){
        db = new PetRepositorio(context);
        db.atualizarPet(pet);
    }
    public void deletarPet(Pet pet){
        db = new PetRepositorio(context);
        db.deletar(pet);
    }
}
