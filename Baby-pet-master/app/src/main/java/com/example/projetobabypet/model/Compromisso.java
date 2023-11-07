package com.example.projetobabypet.model;

import java.util.Date;

public class Compromisso {

    private int id, id_usuario, id_categoria;
    private String nome, descricao, repeticao, hora;
    private String data;

    public Compromisso(String hora, String descricao){
        this.hora = hora;
        this.descricao = descricao;
    }

    public Compromisso(int id, int id_usuario, int id_categoria, String nome, String descricao, String repeticao, String hora, String data) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.repeticao = repeticao;
        this.hora = hora;
        this.data = data;
    }

    public Compromisso(int id, int id_usuario, String nome, String descricao, String repeticao, String hora, String data) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.repeticao = repeticao;
        this.hora = hora;
        this.data = data;
        this.id_usuario = id_usuario;
    }

    public Compromisso(String nome, String descricao, String hora, String data,int id_usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.hora = hora;
        this.id_usuario = id_usuario;
        this.data = data;
    }

    public Compromisso(int id_usuario, String hora, String descricao) {
        this.hora = hora;
        this.descricao = descricao;
        this.id_usuario = id_usuario;
    }

    public Compromisso(int id_usuario, int id_categoria, String nome, String descricao, String hora, String data) {
        this.id_usuario = id_usuario;
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

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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
