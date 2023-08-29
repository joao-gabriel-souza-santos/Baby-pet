package com.example.projetobabypet.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.adapter.AdapterRecycleHome;

import com.example.projetobabypet.controller.ControllerHora;
import com.example.projetobabypet.databinding.FragmentHomeBinding;
import com.example.projetobabypet.model.Horas;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;

    int valorProgressoRacao = 0;
    int valorProgressoAgua = 0;
    int qtd_max_racao = 0, qtd_max_agua = 0;
    ControllerHora controllerHora = ControllerHora.getInstancia();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        updateProgressoAgua();
        updateProgressoRacao();

        binding.buttonDarRacao.setOnClickListener(view1 -> {
            adicionarRacao();
            updateProgressoRacao();
            Horas hora = new Horas();
            controllerHora.cadastrar(hora);
            binding.recycleViewHora.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recycleViewHora.setAdapter(new AdapterRecycleHome(getContext().getApplicationContext(),controllerHora.buscarTodos(),1  ));
        });

        binding.buttonDarAgua.setOnClickListener(view1 -> {
            adicionarAgua();
            updateProgressoAgua();
            Horas hora = new Horas();
            controllerHora.cadastrar(hora);
            binding.recycleViewHora.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recycleViewHora.setAdapter(new AdapterRecycleHome(getContext().getApplicationContext(),controllerHora.buscarTodos(), 2 ));
        });
        return view;
    }

    public void updateProgressoRacao() {
        if (qtd_max_racao == 0 && valorProgressoRacao == 0) {
            binding.progressBarRacao.setProgress(valorProgressoRacao);
            binding.progressBarRacao.setMax(200);
            qtd_max_racao = 200;
            binding.textViewExibeRacao.setText(valorProgressoRacao + "/" + binding.progressBarRacao.getMax());
        } else {
            binding.progressBarRacao.setProgress(valorProgressoRacao);
            binding.progressBarRacao.setMax(qtd_max_racao);
            binding.textViewExibeRacao.setText(valorProgressoRacao + "/" + binding.progressBarRacao.getMax());
        }

    }

    public void updateProgressoAgua() {
        if (qtd_max_agua == 0 && valorProgressoAgua == 0) {
            binding.progressBarAgua.setProgress(valorProgressoAgua);
            binding.progressBarAgua.setMax(200);
            qtd_max_agua = 200;
            binding.textViewExibeAgua.setText(valorProgressoRacao + "/" + binding.progressBarAgua.getMax());
        } else {
            binding.progressBarAgua.setProgress(valorProgressoAgua);
            binding.progressBarAgua.setMax(qtd_max_agua);
            binding.textViewExibeAgua.setText(valorProgressoAgua + "/" + binding.progressBarAgua.getMax());
        }

    }

    public void adicionarRacao() {
        if (valorProgressoRacao == 0) {
            updateProgressoRacao();
            if ((binding.progressBarRacao.getMax() == 200) && (valorProgressoRacao < 180)) {

                valorProgressoRacao += 20;
                updateProgressoRacao();
            }
        } else {
            if (valorProgressoRacao <= binding.progressBarRacao.getMax()) {
                valorProgressoRacao += valorProgressoRacao;
                updateProgressoRacao();
            }
        }


    }

    public void adicionarAgua() {
        if (valorProgressoAgua == 0) {
            updateProgressoAgua();
            if ((binding.progressBarAgua.getMax() == 200) && (valorProgressoAgua <= 180)) {

                valorProgressoAgua += 20;
                updateProgressoAgua();
            }
        } else {
            if (valorProgressoAgua <= qtd_max_agua) {
                valorProgressoAgua += valorProgressoAgua;
                updateProgressoAgua();
            }
        }

    }
}