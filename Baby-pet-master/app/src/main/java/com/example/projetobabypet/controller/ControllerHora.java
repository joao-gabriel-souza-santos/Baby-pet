package com.example.projetobabypet.controller;

import com.example.projetobabypet.model.Horas;

import java.util.ArrayList;
import java.util.List;

public class ControllerHora {
    private int proxId;
    private final List<Horas> horas; //crio uma lista final pra ser imutavel
    private static  ControllerHora instancia = null; //crio um atributo que vai instanciar meu controler

    private ControllerHora(){ //quando ele criar um controller ele vai criar uma lista e um id pro usuario
        horas = new ArrayList<>();
        proxId = 1;
    }

    public int getProxId(){
        return proxId;
    }

    public static ControllerHora getInstancia(){
        if(instancia == null) //verifica se o controller já foi instanciado
            instancia = new ControllerHora(); //se não for instanciado, ele cria uma instancia gerando uma lista
        return instancia;
    }

    public List<Horas> buscarTodos(){
        return new ArrayList<>(horas);
    } //retorna todos os usuarios da lista

    public void cadastrar(Horas hora){ //recebo o usuario a ser cadastrado
        hora.setId(proxId); //setto o id do usuario
        boolean resultado = horas.add(hora); //realizo o cadastro e verifico se teve algum erro
        if(resultado){ //se o resultado for um sucesso
            proxId+=1; //ele cria o prox id
        }
    }
}
