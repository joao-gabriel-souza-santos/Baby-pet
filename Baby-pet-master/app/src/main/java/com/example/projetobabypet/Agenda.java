package com.example.projetobabypet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.HomeActivity;
import com.example.projetobabypet.databinding.ActivityAgendaBinding;


public class Agenda extends AppCompatActivity {

    ActivityAgendaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgendaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding = ActivityAgendaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.bottomNavigationView.setSelectedItemId(R.id.agenda);
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item->{
            switch (item.getItemId()){
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.slide_direita, R.anim.slide_esquerda);
                    finish();
                case R.id.agenda:
                    return true;
                case R.id.artigo:
                    startActivity(new Intent(getApplicationContext(), Artigo.class));
                    overridePendingTransition(R.anim.slide_direita, R.anim.slide_esquerda);
                    finish();
                case R.id.saude:
                    startActivity(new Intent(getApplicationContext(), Saude.class));
                    overridePendingTransition(R.anim.slide_direita, R.anim.slide_esquerda);
                    finish();
            }
            return false;
        });

    }
}