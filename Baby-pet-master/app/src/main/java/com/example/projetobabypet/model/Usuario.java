package com.example.projetobabypet.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
    private String nome, cpf, email, senha, stringFoto;
    private Bitmap foto;
    private  int id;
    //Construtores
    public  Usuario() {
        //Construtor default
    }

    public Usuario(int id, String nome, String cpf, String email, String senha, Bitmap foto) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.foto = foto;

    }


    public Usuario(String nome, String cpf, String email, Bitmap foto) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.foto = foto;
    }

    public Usuario(String nome, String cpf, String email, String senha, String stringFoto) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.stringFoto = stringFoto;
    }

    public Usuario(String nome, String cpf, String email, String foto, Bitmap fotoBitpmap) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.stringFoto = stringFoto;
        this.foto = fotoBitpmap;
    }
    public String getStringFoto() {
        return stringFoto;
    }

    public void setStringFoto(String stringFoto) {
        this.stringFoto = stringFoto;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return getId() == usuario.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", id=" + id +
                '}';
    }
}
