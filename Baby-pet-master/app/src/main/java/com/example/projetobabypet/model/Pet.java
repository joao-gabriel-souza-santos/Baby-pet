package com.example.projetobabypet.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Pet implements Serializable {
    private String nome, sexo, raca;
    private int id, idUsuario, idade, qtde_racao, qtde_agua;
    private Bitmap foto;



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

    public Pet(String nome, String sexo, String raca,  int idUsuario, int idade, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;

        this.idUsuario = idUsuario;
        this.idade = idade;
        this.foto = foto;
    }

    public Pet(int id_usuario, String nome, String sexo, String raca, int idade, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.idade = idade;
        this.foto = foto;
        this.idUsuario = id_usuario;
    }

    public Pet(String nome, String sexo, String raca, int id, int idUsuario, int idade, int qtde_racao, int qtde_agua, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.id = id;
        this.idUsuario = idUsuario;
        this.idade = idade;
        this.qtde_racao = qtde_racao;
        this.qtde_agua = qtde_agua;
        this.foto = foto;
    }

    public Pet(String nome, String sexo, String raca, int id, int idUsuario, int idade, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.id = id;
        this.idUsuario = idUsuario;
        this.idade = idade;
        this.foto = foto;
    }

    public Pet(String nome, String sexo, String raca, int idade, Bitmap fotoCarregada) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.idade = idade;
        this.foto = fotoCarregada;
    }






    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }


    public int getQtde_racao() {
        return qtde_racao;
    }

    public void setQtde_racao(int qtde_racao) {
        this.qtde_racao = qtde_racao;
    }

    public int getQtde_agua() {
        return qtde_agua;
    }

    public void setQtde_agua(int qtde_agua) {
        this.qtde_agua = qtde_agua;
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
