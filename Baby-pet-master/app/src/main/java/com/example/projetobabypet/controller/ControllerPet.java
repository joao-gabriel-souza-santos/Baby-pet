package com.example.projetobabypet.controller;

import com.example.projetobabypet.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class ControllerPet {

    private int proxId;
    private final List<Pet> pets;
    private static ControllerPet instancia = null;

    private ControllerPet(){
        pets = new ArrayList<>();
    }



    public static ControllerPet getInstancia(){
        if(instancia==null)
            instancia = new ControllerPet();
        return instancia;
    }

    public void cadastrar(Pet pet){
        pet.setId(proxId);
        boolean resultado = pets.add(pet);
        if(resultado)
            proxId += 1;
    }
    public int getProxId(){
        return proxId;
    }
}
