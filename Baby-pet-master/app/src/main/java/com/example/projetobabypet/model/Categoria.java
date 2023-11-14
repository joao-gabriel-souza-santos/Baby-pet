package com.example.projetobabypet.model;

public class Categoria {

    private int id;
    private String nome;
    private String email_usuario;



    public Categoria(int id, String nome, String email_usuario) {
        this.id = id;
        this.nome = nome;
        this.email_usuario = email_usuario;
    }

    public Categoria(String nome, String email_usuario) {
        this.nome = nome;
        this.email_usuario = email_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
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
