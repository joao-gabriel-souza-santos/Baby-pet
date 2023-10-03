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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.projetobabypet.R;
import com.example.projetobabypet.adapter.AdapterRecycleListaPets;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.ActivityHomeBinding;
import com.example.projetobabypet.fragments.ContaFragment;
import com.example.projetobabypet.fragments.ArtigoFragment;
import com.example.projetobabypet.fragments.HomeFragment;
import com.example.projetobabypet.model.Usuario;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityHomeBinding binding;

    private ActionBarDrawerToggle drawerToggle;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private String email;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sp = getSharedPreferences("Log", MODE_PRIVATE);
        editor = sp.edit();
        email = sp.getString("email", "");

        pets_usuario_logado();

        initi_navigation_drawer();


        binding.imageMenu.setOnClickListener(view -> {
            binding.drawerLayout.openDrawer(GravityCompat.START);
        });



    }

    private void initi_navigation_drawer() {
        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);


        replaceFragment(new HomeFragment());//chamo o metodo pra realizar a troca

        binding.navView.setNavigationItemSelectedListener(this);


        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> { //coloco pra escutar o toque na barra de navegação
            switch (item.getItemId()) { //pega o item clicado
                case R.id.home:
                    replaceFragment(new HomeFragment()); //se o item clicado for o home, leva pra tela home
                    break;
                case R.id.conta:
                    replaceFragment(new ContaFragment()); //se o item clicado for agenda, leva pra tela agenda
                    break;
                case R.id.artigo:
                    replaceFragment(new ArtigoFragment()); //se o item clicado for artigo, leva pra tela artigo
                    break;
            }

            return false;
        });
    }

    private void pets_usuario_logado() {
        if (email == null || email.equals("")) {
            AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
            caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
            caixademsg.setMessage("usuario nulo"); //coloca a mensagem da caixa
            caixademsg.show(); //exibe a caixa pro usuario

        } else {
            try {
                usuario = usuario_logado(email);
                View header = binding.navView.getHeaderView(0);
                ImageView imagUser = header.findViewById(R.id.image_usuario_header);
                TextView textView = header.findViewById(R.id.textView_header);
                RecyclerView recycleViewListaPets = header.findViewById(R.id.recycle_view_lista_pets);
                textView.setText("" + usuario.getNome());
                imagUser.setImageBitmap(usuario.getFoto());
                LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                recycleViewListaPets.setLayoutManager(manager);
                recycleViewListaPets.setAdapter(new AdapterRecycleListaPets(HomeActivity.this, usuario.getId()));


            } catch (Exception e) {
                AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
                caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                caixademsg.setMessage(e.getMessage()); //coloca a mensagem da caixa
                caixademsg.show(); //exibe a caixa pro usuario
            }
        }
    }

    private Usuario usuario_logado(String email) {
        try {
            ControllerUsuario c = ControllerUsuario.getInstancia(HomeActivity.this);
            Usuario usuario = c.buscarPorEmail(email);
            return usuario;
        } catch (Exception e) {
            AlertDialog.Builder caixademsg = new AlertDialog.Builder(this); //cria uma caixa de alerta
            caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
            caixademsg.setMessage("" + e.getMessage()); //coloca a mensagem da caixa
            caixademsg.show(); //exibe a caixa pro usuario
        }
        return null;
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void sair() {
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
        switch (menuItem.getItemId()) {
            case R.id.sair_da_tela: {
                sair();
                break;
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}