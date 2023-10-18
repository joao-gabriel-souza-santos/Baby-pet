package com.example.projetobabypet.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Pet implements Serializable {
    private String nome, sexo, raca, horas_comida_manha, horas_comida_tarde, horas_comida_noite, horas_comeu, horas_agua_manha,horas_agua_tarde, horas_agua_noite, horas_bebeu;
    private int id, idUsuario, idade, qtde_racao, qtde_agua;
    private Bitmap foto;

    public Pet(String nome, String sexo, String raca, String horas_comida_manha, String horas_comeu, String horas_agua_manha, String horas_bebeu, int idade, int qtde_racao, int qtde_agua, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.horas_comida_manha = horas_comida_manha;
        this.horas_comeu = horas_comeu;
        this.horas_agua_manha = horas_agua_manha;
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

    public Pet(String nome, String sexo, String raca, String horas_comida_manha, String horas_comida_tarde, String horas_comida_noite, String horas_agua_manha, String horas_agua_tarde, String horas_agua_noite, int idUsuario, int idade, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.horas_comida_manha = horas_comida_manha;
        this.horas_comida_tarde = horas_comida_tarde;
        this.horas_comida_noite = horas_comida_noite;
        this.horas_agua_manha = horas_agua_manha;
        this.horas_agua_tarde = horas_agua_tarde;
        this.horas_agua_noite = horas_agua_noite;
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

    public Pet(String nome, String sexo, String raca, String horas_comida_manha, String horas_comeu, String horas_agua_manha, String horas_bebeu, int id, int idUsuario, int idade, int qtde_racao, int qtde_agua, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.horas_comida_manha = horas_comida_manha;
        this.horas_comeu = horas_comeu;
        this.horas_agua_manha = horas_agua_manha;
        this.horas_bebeu = horas_bebeu;
        this.id = id;
        this.idUsuario = idUsuario;
        this.idade = idade;
        this.qtde_racao = qtde_racao;
        this.qtde_agua = qtde_agua;
        this.foto = foto;
    }

    public Pet(String nome, String sexo, String raca, String horas_comida_manha, String horas_comida_tarde, String horas_comida_noite, String horas_agua_manha, String horas_agua_tarde, String horas_agua_noite, int id, int idUsuario, int idade, Bitmap foto) {
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.horas_comida_manha = horas_comida_manha;
        this.horas_comida_tarde = horas_comida_tarde;
        this.horas_comida_noite = horas_comida_noite;
        this.horas_agua_manha = horas_agua_manha;
        this.horas_agua_tarde = horas_agua_tarde;
        this.horas_agua_noite = horas_agua_noite;
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

    public String getHoras_comida_tarde() {
        return horas_comida_tarde;
    }

    public void setHoras_comida_tarde(String horas_comida_tarde) {
        this.horas_comida_tarde = horas_comida_tarde;
    }

    public String getHoras_comida_noite() {
        return horas_comida_noite;
    }

    public void setHoras_comida_noite(String horas_comida_noite) {
        this.horas_comida_noite = horas_comida_noite;
    }

    public String getHoras_agua_tarde() {
        return horas_agua_tarde;
    }

    public void setHoras_agua_tarde(String horas_agua_tarde) {
        this.horas_agua_tarde = horas_agua_tarde;
    }

    public String getHoras_agua_noite() {
        return horas_agua_noite;
    }

    public void setHoras_agua_noite(String horas_agua_noite) {
        this.horas_agua_noite = horas_agua_noite;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getHoras_comida_manha() {
        return horas_comida_manha;
    }

    public void setHoras_comida_manha(String horas_comida_manha) {
        this.horas_comida_manha = horas_comida_manha;
    }

    public String getHoras_comeu() {
        return horas_comeu;
    }

    public void setHoras_comeu(String horas_comeu) {
        this.horas_comeu = horas_comeu;
    }

    public String getHoras_agua_manha() {
        return horas_agua_manha;
    }

    public void setHoras_agua_manha(String horas_agua_manha) {
        this.horas_agua_manha = horas_agua_manha;
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
