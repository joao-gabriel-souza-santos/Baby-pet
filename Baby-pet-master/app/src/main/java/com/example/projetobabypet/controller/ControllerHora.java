package com.example.projetobabypet.controller;

import android.content.Context;

import com.example.projetobabypet.dao.CompromissoRepositorio;
import com.example.projetobabypet.model.Compromisso;

import java.util.ArrayList;
import java.util.List;

public class ControllerHora {

    public  static List<Compromisso> compromissos;
    private static ControllerHora instancia = null;
    Context context;
    CompromissoRepositorio db;

    private ControllerHora(Context context){
        this.context = context;
        compromissos = new ArrayList<>();
    }

    public static ControllerHora getInstance(Context context){
        if(instancia ==null)
            instancia=new ControllerHora(context);
        return instancia;
    }

    public void cadastrar(Compromisso compromisso){
        db = new CompromissoRepositorio(context);
        db.cadastrar(compromisso);
    }

    public List<Compromisso> buscar_compromissos_usuario(int id){
        db = new CompromissoRepositorio(context);
        compromissos = db.buscarTodosCompromissos();
        List<Compromisso> compromissos_usuario = new ArrayList<>();
        for(Compromisso compromisso: compromissos){
            if(compromisso.getId_usuario() == id)
                compromissos_usuario.add(compromisso);

        }
        return compromissos_usuario;
    }
}
