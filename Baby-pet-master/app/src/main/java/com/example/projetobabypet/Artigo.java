package com.example.projetobabypet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projetobabypet.activities.HomeActivity;
import com.example.projetobabypet.databinding.ActivityArtigoBinding;

public class Artigo extends AppCompatActivity {

    ActivityArtigoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtigoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setSelectedItemId(R.id.artigo);
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.slide_direita, R.anim.slide_esquerda);
                    finish();

                case R.id.agenda:
                    startActivity(new Intent(getApplicationContext(), Agenda.class));
                    overridePendingTransition(R.anim.slide_direita, R.anim.slide_esquerda);
                    finish();
                case R.id.artigo:
                    return true;
                case R.id.saude:
                    startActivity(new Intent(getApplicationContext(), Saude.class));
                    overridePendingTransition(R.anim.slide_direita, R.anim.slide_esquerda);
                    finish();
            }
            return false;
        });
    }
}