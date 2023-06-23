package com.example.projetobabypet.controller;

import com.example.projetobabypet.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControllerUsuario {

    private int proxId;
    private final List<Usuario> usuarios;
    private static  ControllerUsuario instancia = null;

    private ControllerUsuario(){
        usuarios = new ArrayList<>();
        proxId = 1;
    }

    public int getProxId(){
        return proxId;
    }

    public static ControllerUsuario getInstancia(){
        if(instancia == null)
            instancia = new ControllerUsuario();
        return instancia;
    }

    public List<Usuario> buscarTodos(){
        return new ArrayList<>(usuarios);
    }

    public void cadastrar(Usuario usuario){
        usuario.setId(proxId);
        boolean resultado = usuarios.add(usuario);
        if(resultado){
            proxId+=1;
        }
    }
    
    public boolean login(String email, String senha){
        for (Usuario usuario: usuarios) {
            if((usuario.getEmail().equals(email)) && (usuario.getSenha().equals(senha))) {
                return true;
            }
        }
        return false;
    }

    public Usuario buscarporId(int id){
        for(Usuario usuario: usuarios){
            if(usuario.getId()==id){
                return usuario;
            }
        }
        return null;
    }

}
