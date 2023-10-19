package com.example.projetobabypet.model;

import java.util.Date;

public class Compromisso {

    private int id, id_usuario;
    private String nome, descricao, repeticao, hora;
    private Date data;

    public Compromisso(String hora, String descricao){
        this.hora = hora;
        this.descricao = descricao;
    }

    public Compromisso(int id,int id_usuario, String nome, String descricao, String repeticao, String hora, Date data) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.repeticao = repeticao;
        this.hora = hora;
        this.data = data;
        this.id_usuario = id_usuario;
    }

    public Compromisso(String nome, String descricao, String hora, int id_usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.hora = hora;
        this.id_usuario = id_usuario;
    }

    public Compromisso(int id_usuario, String hora, String descricao) {
        this.hora = hora;
        this.descricao = descricao;
        this.id_usuario = id_usuario;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
