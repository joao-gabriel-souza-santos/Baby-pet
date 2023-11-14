package com.example.projetobabypet.controller;

import android.content.Context;

import com.example.projetobabypet.dao.Helper;
import com.example.projetobabypet.dao.PetRepositorio;
import com.example.projetobabypet.dao.RacaoAguaRepositorio;
import com.example.projetobabypet.model.Pet;
import com.example.projetobabypet.model.RacaoEAgua;

import java.util.ArrayList;
import java.util.List;

public class ControllerRacaoAgua {
    public static List<RacaoEAgua> racaoEAguas;
    private static ControllerRacaoAgua instancia = null;
    Context context;
    RacaoAguaRepositorio db;

    private ControllerRacaoAgua(Context context){
        this.context = context;
        racaoEAguas = new ArrayList<>();
    }



    public static ControllerRacaoAgua getInstancia(Context context){
        if(instancia==null)
            instancia = new ControllerRacaoAgua(context);
        return instancia;
    }

    public void cadastrar(RacaoEAgua racaoEAgua){
        db = new RacaoAguaRepositorio(context);
        db.cadastrar(racaoEAgua);
    }

    public List<RacaoEAgua> buscar(String email){
        db = new RacaoAguaRepositorio(context);
          racaoEAguas =  db.buscarTodasRacaoAgua(email);
          return racaoEAguas;
    }

}
