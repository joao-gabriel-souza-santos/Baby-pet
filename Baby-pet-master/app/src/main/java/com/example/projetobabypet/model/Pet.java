package com.example.projetobabypet.model;

import java.io.Serializable;

public class Pet implements Serializable {
    private String nome, sexo, raca;
    private int id, idUsuario, idade;


    //Construtores
    public Pet(){
        //construtor default
    }




    public Pet(String nome, String sexo, String raca, int id, int idUsuario, int idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.id = id;
        this.idUsuario = idUsuario;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }


}
