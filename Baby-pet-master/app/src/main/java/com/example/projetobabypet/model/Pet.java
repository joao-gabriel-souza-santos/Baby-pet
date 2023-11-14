package com.example.projetobabypet.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Pet implements Serializable {
    private String nome, sexo, raca;
    private  String email_usuario;
    private int id, idade, qtde_racao, qtde_agua;
    private Bitmap foto;



    //Construtores
    public Pet(){
        //construtor default
    }
    public Pet(String nome, String sexo, String raca, int id, String email_usuario, int idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.id = id;
        this.email_usuario = email_usuario;
        this.idade = idade;
    }



    public Pet(String email_usuario, String nome, String sexo, String raca, int idade, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.idade = idade;
        this.foto = foto;
        this.email_usuario = email_usuario;
    }

    public Pet(String nome, String sexo, String raca, int id, String email_usuario, int idade, int qtde_racao, int qtde_agua, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.id = id;
        this.email_usuario = email_usuario;
        this.idade = idade;
        this.qtde_racao = qtde_racao;
        this.qtde_agua = qtde_agua;
        this.foto = foto;
    }

    public Pet(String nome, String sexo, String raca, int id, String email_usuario, int idade, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.id = id;
        this.email_usuario = email_usuario;
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

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }


}
