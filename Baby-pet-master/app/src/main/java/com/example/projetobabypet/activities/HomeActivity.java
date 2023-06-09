package com.example.projetobabypet.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projetobabypet.R;
import com.example.projetobabypet.databinding.ActivityHomeBinding;
import com.example.projetobabypet.fragments.AgendaFragment;
import com.example.projetobabypet.fragments.ArtigoFragment;
import com.example.projetobabypet.fragments.HomeFragment;


public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    int valorProgressoRacao = 0;
    int valorProgressoAgua = 0;
    int qtd_max=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());//chamo o metodo pra realizar a troca
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item ->{ //coloco pra escutar o toque na barra de navegação
            switch (item.getItemId()){ //pega o item clicado
                case R.id.home:
                    replaceFragment(new HomeFragment()); //se o item clicado for o home, leva pra tela home
                    break;
                case R.id.agenda:
                    replaceFragment(new AgendaFragment()); //se o item clicado for agenda, leva pra tela agenda
                    break;
                case R.id.artigo:
                    replaceFragment(new ArtigoFragment()); //se o item clicado for artigo, leva pra tela artigo
                    break;
            }

            return true;
        });

        binding.buttonVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, Login.class));
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}