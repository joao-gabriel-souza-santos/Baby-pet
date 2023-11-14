package com.example.projetobabypet.model;

public class Compromisso {

    private int id, id_categoria;
    private String nome, descricao, repeticao, hora, email_usuario;
    private String data;

    public Compromisso(String hora, String descricao){
        this.hora = hora;
        this.descricao = descricao;
    }

    public Compromisso(int id, String email_usuario, int id_categoria, String nome, String descricao, String repeticao, String hora, String data) {
        this.id = id;
        this.email_usuario = email_usuario;
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.repeticao = repeticao;
        this.hora = hora;
        this.data = data;
    }

    public Compromisso(int id, String email_usuario, String nome, String descricao, String repeticao, String hora, String data) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.repeticao = repeticao;
        this.hora = hora;
        this.data = data;
        this.email_usuario = email_usuario;
    }

    public Compromisso(String nome, String descricao, String hora, String data,String email_usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.hora = hora;
        this.email_usuario = email_usuario;
        this.data = data;
    }

    public Compromisso(String email_usuario, String hora, String descricao) {
        this.hora = hora;
        this.descricao = descricao;
        this.email_usuario = email_usuario;
    }

    public Compromisso(String email_usuario, int id_categoria, String nome, String descricao, String hora, String data) {
        this.email_usuario = email_usuario;
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.hora = hora;
        this.data = data;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(String repeticao) {
        this.repeticao = repeticao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
