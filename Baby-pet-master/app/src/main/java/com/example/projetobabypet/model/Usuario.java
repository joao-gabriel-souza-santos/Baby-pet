package com.example.projetobabypet.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
    private String nome, cpf, email, senha, stringFoto;
    private Bitmap foto;
    private  int id, qtde_racao, qtde_agua, somaracao, somaagua, maxRacao, maxAgua;
    //Construtores
    public  Usuario(){
        //Construtor default
    }
    public Usuario(String nome, String cpf, String email, String senha, String stringFoto, Bitmap foto, int id, int qtde_racao, int qtde_agua, int somaracao, int somaagua, int maxRacao, int maxAgua) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.stringFoto = stringFoto;
        this.foto = foto;
        this.id = id;
        this.qtde_racao = qtde_racao;
        this.qtde_agua = qtde_agua;
        this.somaracao = somaracao;
        this.somaagua = somaagua;
        this.maxRacao = maxRacao;
        this.maxAgua = maxAgua;
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

    public int getMaxRacao() {
        return maxRacao;
    }

    public void setMaxRacao(int maxRacao) {
        this.maxRacao = maxRacao;
    }

    public int getMaxAgua() {
        return maxAgua;
    }

    public void setMaxAgua(int maxAgua) {
        this.maxAgua = maxAgua;
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

    public int getSomaracao() {
        return somaracao;
    }

    public void setSomaracao(int somaracao) {
        this.somaracao = somaracao;
    }

    public int getSomaagua() {
        return somaagua;
    }

    public void setSomaagua(int somaagua) {
        this.somaagua = somaagua;
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
