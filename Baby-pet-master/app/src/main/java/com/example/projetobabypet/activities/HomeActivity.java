package com.example.projetobabypet.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.cadastro.CadastrarPetActivity;
import com.example.projetobabypet.databinding.ActivityHomeBinding;
import com.example.projetobabypet.fragments.AgendaFragment;
import com.example.projetobabypet.fragments.ArtigoFragment;
import com.example.projetobabypet.fragments.HomeFragment;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityHomeBinding binding;

    ActionBarDrawerToggle drawerToggle;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sp = getSharedPreferences("Log", MODE_PRIVATE);
        editor = sp.edit();


        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout,toolbar, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);


        replaceFragment(new HomeFragment());//chamo o metodo pra realizar a troca

        binding.navView.setNavigationItemSelectedListener(this);


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

            return false;
        });



       binding.imageMenu.setOnClickListener(view -> {
           binding.drawerLayout.openDrawer(GravityCompat.START);
       });

    }



    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void sair(){
        editor.clear();
        editor.apply();
        startActivity(new Intent(this, Login.class));
        this.finish();
    }


    @Override
    public void onBackPressed() {

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.sair_da_tela:{
                sair();

                break;
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}