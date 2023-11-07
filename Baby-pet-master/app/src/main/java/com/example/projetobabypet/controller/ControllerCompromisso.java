package com.example.projetobabypet.controller;

import android.content.Context;

import com.example.projetobabypet.dao.CompromissoRepositorio;
import com.example.projetobabypet.model.Compromisso;

import java.util.ArrayList;
import java.util.List;

public class ControllerCompromisso {

    public  static List<Compromisso> compromissos;
    private static ControllerCompromisso instancia = null;
    Context context;
    CompromissoRepositorio db;

    private ControllerCompromisso(Context context){
        this.context = context;
        compromissos = new ArrayList<>();
    }

    public static ControllerCompromisso getInstance(Context context){
        if(instancia ==null)
            instancia=new ControllerCompromisso(context);
        return instancia;
    }

    public void cadastrar(Compromisso compromisso){
        db = new CompromissoRepositorio(context);
        db.cadastrar(compromisso);
    }

    public void cadastrarNotificacao(Compromisso compromisso){
        db = new CompromissoRepositorio(context);
        db.cadastrarNotificacao(compromisso);
    }


    public List<Compromisso> buscar_compromissos(int id){
        db = new CompromissoRepositorio(context);
        compromissos = db.buscarTodosCompromissos_usuario(id);
        return compromissos;
    }
    public List<Compromisso> buscar_todos_compromissos(){
        db = new CompromissoRepositorio(context);
        compromissos = db.buscarTodosCompromissos();
        return compromissos;
    }

    public void atualizarCompromisso(Compromisso compromisso){
        db = new CompromissoRepositorio(context);
        db.atualizarCompromisso(compromisso);
    }

    public void deletar(Compromisso compromisso){
        db = new CompromissoRepositorio(context);
        db.deletar(compromisso);
    }
    public void cadastrarAfazer(Compromisso compromisso){
        db = new CompromissoRepositorio(context);
        db.cadastrar(compromisso);
    }
    public List<Compromisso> buscar_compromissos_categoria(int id_categoria){
        db = new CompromissoRepositorio(context);
        compromissos = db.buscarTodosCompromissos_categoria(id_categoria);
        return compromissos;
    }
    public void cadastrarCompromissoCategoria(Compromisso compromisso){
        db = new CompromissoRepositorio(context);
        db.cadastrarCompromissoCategoria(compromisso);
    }

    public Compromisso buscarCompromissoId(int id, int id_categoria){
        db = new CompromissoRepositorio(context);
        compromissos = db.buscarTodosCompromissos_categoria(id_categoria);
        for(Compromisso compromisso : compromissos){
            if(compromisso.getId() == id){
                return compromisso;
            }
        }
        return null;
    }

}
