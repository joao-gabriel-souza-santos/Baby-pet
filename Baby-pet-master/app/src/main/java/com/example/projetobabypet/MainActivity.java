package com.example.projetobabypet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.model.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia();
    public List<Usuario> usuarios = controllerUsuario.buscarTodos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}