package com.example.projetobabypet.model;

public class RacaoEAgua {

    private int id;
    private  String aguaouracao, email_usuario, hora;

    public RacaoEAgua(int id, String aguaouracao, String email_usuario, String hora) {
        this.id = id;
        this.aguaouracao = aguaouracao;
        this.email_usuario = email_usuario;
        this.hora = hora;
    }

    public RacaoEAgua(){

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAguaouracao() {
        return aguaouracao;
    }

    public void setAguaouracao(String aguaouracao) {
        this.aguaouracao = aguaouracao;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }
}
