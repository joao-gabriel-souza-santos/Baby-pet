package com.example.projetobabypet.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetobabypet.Agenda;
import com.example.projetobabypet.Artigo;
import com.example.projetobabypet.R;
import com.example.projetobabypet.Saude;
import com.example.projetobabypet.databinding.ActivityHomeBinding;


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
        updateProgressoRacao();
        updateProgressoAgua();

        binding.buttonVoltar.setOnClickListener(view -> {
            startActivity(new Intent(this, Login.class));
        });

        binding.bottomNavigationView.setSelectedItemId(R.id.home);
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item->{
            switch (item.getItemId()){
                case R.id.home:
                    return true;
                case R.id.agenda:
                    startActivity(new Intent(getApplicationContext(),Agenda.class));
                    overridePendingTransition(R.anim.slide_direita, R.anim.slide_esquerda);
                    finish();
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

        binding.imageButtonModificarRacao.setOnClickListener(view->{
           final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HomeActivity.this, R.style.BottomSheetDialogTheme);
            View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(
                    R.layout.layout_bottom_sheet_inicio,
                    (ConstraintLayout)findViewById(R.id.bottomSheetInicio)
            );
            bottomSheetView.findViewById(R.id.button_salvar_racao).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText qtde_a_ser_dada = bottomSheetView.findViewById(R.id.editText_qtde_racao);
                    int qtde_dada = Integer.parseInt(qtde_a_ser_dada.getText().toString());
                    EditText max = bottomSheetDialog.findViewById(R.id.editText_qtde_max);
                    qtd_max = Integer.parseInt(max.getText().toString());
                    valorProgressoRacao = qtde_dada;
                    binding.barreProgressoRacao.setMax(qtd_max);
                    updateProgressoRacao();
                    bottomSheetDialog.dismiss();
                }

        });
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });

        binding.imageButtonModificarAgua.setOnClickListener(view->{
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HomeActivity.this, R.style.BottomSheetDialogTheme);
            View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(
                    R.layout.layout_bottom_sheet_inicio,
                    (ConstraintLayout)findViewById(R.id.bottomSheetInicio)
            );
            bottomSheetView.findViewById(R.id.button_salvar_racao).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText qtde_a_ser_dada = bottomSheetView.findViewById(R.id.editText_qtde_racao);
                    int qtde_dada = Integer.parseInt(qtde_a_ser_dada.getText().toString());
                    EditText max = bottomSheetDialog.findViewById(R.id.editText_qtde_max);
                    qtd_max = Integer.parseInt(max.getText().toString());
                    valorProgressoAgua = qtde_dada;
                    binding.progressBarAgua.setMax(qtd_max);
                    updateProgressoAgua();
                    bottomSheetDialog.dismiss();
                }

            });
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }

    private void updateProgressoRacao(){
        if(qtd_max == 0 && valorProgressoRacao == 0 ){
            binding.barreProgressoRacao.setProgress(valorProgressoRacao);
            binding.barreProgressoRacao.setMax(200);
            binding.qtdeRacao.setText(valorProgressoRacao+"/" + binding.barreProgressoRacao.getMax());
        } else{
            binding.barreProgressoRacao.setProgress(valorProgressoRacao);
            binding.barreProgressoRacao.setMax(qtd_max);
            binding.qtdeRacao.setText(valorProgressoRacao+"/" + binding.barreProgressoRacao.getMax());
        }

    }
    private void updateProgressoAgua() {
        if(qtd_max == 0 && valorProgressoAgua == 0 ){
            binding.progressBarAgua.setProgress(valorProgressoAgua);
            binding.progressBarAgua.setMax(200);
            binding.txtQtdeAgua.setText(valorProgressoRacao+"/" + binding.barreProgressoRacao.getMax());
        } else{
            binding.progressBarAgua.setProgress(valorProgressoAgua);
            binding.progressBarAgua.setMax(qtd_max);
            binding.txtQtdeAgua.setText(valorProgressoAgua+"/" + binding.progressBarAgua.getMax());
        }

    }
        public void adicionarRacao(View view) {
            if(valorProgressoRacao == 0){
                updateProgressoRacao();
                if(qtd_max == 200 && valorProgressoRacao<=180){
                    valorProgressoRacao+=20;
                    updateProgressoRacao();
                }
            }else {
                if (valorProgressoRacao<=qtd_max){
                    valorProgressoRacao+=valorProgressoRacao;
                    updateProgressoRacao();
                }
            }


    }
    public void adicionarAgua(View view) {
        if (valorProgressoAgua == 0) {
            updateProgressoAgua();
            if (qtd_max == 200 && valorProgressoAgua <= 180) {
                valorProgressoAgua += 20;
                updateProgressoAgua();
            }
        } else {
            if (valorProgressoAgua <= qtd_max) {
                valorProgressoAgua += valorProgressoAgua;
                updateProgressoAgua();
            }
        }
    }
}