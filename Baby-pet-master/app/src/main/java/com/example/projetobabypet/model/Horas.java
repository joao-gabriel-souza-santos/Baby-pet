package com.example.projetobabypet.model;

import java.util.Calendar;

public class Horas {
    private String horas;
    private int id;

    public Horas(String horas, int id) {
        this.horas = horas;
        this.id = id;
    }

    public Horas(String horas) {
        this.horas = horas;
    }
    public Horas(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoras() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        horas = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE));

        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }
}
