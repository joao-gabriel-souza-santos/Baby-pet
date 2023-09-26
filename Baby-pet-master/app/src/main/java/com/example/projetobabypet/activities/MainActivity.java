package com.example.projetobabypet.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityMainBinding;
import com.example.projetobabypet.model.Usuario;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}