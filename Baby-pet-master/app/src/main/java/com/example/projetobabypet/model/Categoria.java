package com.example.projetobabypet.model;

public class Categoria {

    private int id;
    private String nome;
    private int id_usuario;



    public Categoria(int id, String nome, int id_usuario) {
        this.id = id;
        this.nome = nome;
        this.id_usuario = id_usuario;
    }

    public Categoria(String nome, int id_usuario) {
        this.nome = nome;
        this.id_usuario = id_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
