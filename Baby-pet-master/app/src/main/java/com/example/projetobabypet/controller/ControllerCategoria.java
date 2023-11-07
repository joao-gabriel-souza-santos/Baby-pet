package com.example.projetobabypet.controller;

import android.content.Context;

import com.example.projetobabypet.dao.CategoriaRepositorio;
import com.example.projetobabypet.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class ControllerCategoria {

    private  Context context;
    public  static List<Categoria> categorias;
    private static ControllerCategoria instancia = null;
    private CategoriaRepositorio db;

    private ControllerCategoria(Context context){
        this.context = context;
        categorias = new ArrayList<>();
    }

    public static ControllerCategoria getInstance(Context context){
        if(instancia == null)
            instancia = new ControllerCategoria(context);
        return instancia;
    }

    public void cadastrar(Categoria categoria){
        db = new CategoriaRepositorio(context);
        db.cadastrar(categoria);
    }
    public List<Categoria> buscarCategorias(int id){
        db = new CategoriaRepositorio(context);
        categorias = db.buscarTodasCategorias(id);
        return categorias;
    }

    public void deletar(int id){
        db = new CategoriaRepositorio(context);
        db.deletar(id);
    }
}
