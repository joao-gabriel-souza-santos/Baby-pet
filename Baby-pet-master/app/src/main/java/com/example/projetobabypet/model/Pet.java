package com.example.projetobabypet.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Pet implements Serializable {
    private String nome, sexo, raca, horas_comida, horas_comeu, horas_agua, horas_bebeu;
    private int id, idUsuario, idade, qtde_racao, qtde_agua;
    private Bitmap foto;

    public Pet(String nome, String sexo, String raca, String horas_comida, String horas_comeu, String horas_agua, String horas_bebeu, int idade, int qtde_racao, int qtde_agua, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.horas_comida = horas_comida;
        this.horas_comeu = horas_comeu;
        this.horas_agua = horas_agua;
        this.horas_bebeu = horas_bebeu;
        this.idade = idade;
        this.qtde_racao = qtde_racao;
        this.qtde_agua = qtde_agua;
        this.foto = foto;
    }

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

    public Pet(int id_usuario, String nome, String sexo, String raca, int idade, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.idade = idade;
        this.foto = foto;
        this.idUsuario = id_usuario;
    }

    public Pet(String nome, String sexo, String raca, String horas_comida, String horas_comeu, String horas_agua, String horas_bebeu, int id, int idUsuario, int idade, int qtde_racao, int qtde_agua, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.horas_comida = horas_comida;
        this.horas_comeu = horas_comeu;
        this.horas_agua = horas_agua;
        this.horas_bebeu = horas_bebeu;
        this.id = id;
        this.idUsuario = idUsuario;
        this.idade = idade;
        this.qtde_racao = qtde_racao;
        this.qtde_agua = qtde_agua;
        this.foto = foto;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getHoras_comida() {
        return horas_comida;
    }

    public void setHoras_comida(String horas_comida) {
        this.horas_comida = horas_comida;
    }

    public String getHoras_comeu() {
        return horas_comeu;
    }

    public void setHoras_comeu(String horas_comeu) {
        this.horas_comeu = horas_comeu;
    }

    public String getHoras_agua() {
        return horas_agua;
    }

    public void setHoras_agua(String horas_agua) {
        this.horas_agua = horas_agua;
    }

    public String getHoras_bebeu() {
        return horas_bebeu;
    }

    public void setHoras_bebeu(String horas_bebeu) {
        this.horas_bebeu = horas_bebeu;
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
