package com.example.projetobabypet.fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.adapter.AdapterRecycleHome;

import com.example.projetobabypet.controller.ControllerHora;


import com.example.projetobabypet.databinding.FragmentHomeBinding;
import com.example.projetobabypet.model.Horas;

import java.util.ArrayList;
import java.util.List;




public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    public int valorProgressoRacao = 0;
    public int valorProgressoAgua = 0;
    public int qtd_max_racao = 0, qtd_max_agua = 0;
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
            binding.recycleViewHora.setAdapter(new AdapterRecycleHome(getContext().getApplicationContext(), controllerHora.buscarTodos(), 1));

        });

        binding.buttonDarAgua.setOnClickListener(view1 -> {
            adicionarAgua();
            updateProgressoAgua();

            Horas hora = new Horas();
            controllerHora.cadastrar(hora);
            binding.recycleViewHora.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recycleViewHora.setAdapter(new AdapterRecycleHome(getContext().getApplicationContext(), controllerHora.buscarTodos(), 2));

        });

        binding.imageButtonModificarRacao.setOnClickListener(view1 -> {
            BottomSheetDialog dialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
            View sheetView = LayoutInflater.from(getContext())
                    .inflate(R.layout.layout_bottom_sheet_inicio, view.findViewById(R.id.bottomSheetInicio));
            sheetView.findViewById(R.id.button_salvar_racao).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText qtde_a_ser_dada = sheetView.findViewById(R.id.editText_qtde_racao);
                    int qtde_dada = Integer.parseInt(qtde_a_ser_dada.getText().toString());
                    EditText max = sheetView.findViewById(R.id.editText_qtde_max);
                    qtd_max_racao = Integer.parseInt(max.getText().toString());
                    valorProgressoRacao = qtde_dada;
                    binding.progressBarRacao.setMax(qtd_max_racao);
                    updateProgressoRacao();
                    dialog.dismiss();
                }
            });
            dialog.setContentView(sheetView);
            dialog.show();
        });

        binding.imageButtonModificarAgua.setOnClickListener(view2 ->{
            BottomSheetDialog dialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
            View sheetView = LayoutInflater.from(getContext())
                    .inflate(R.layout.layout_bottom_sheet_inicio, view.findViewById(R.id.bottomSheetInicio));
            sheetView.findViewById(R.id.button_salvar_racao).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText qtde_a_ser_dada = sheetView.findViewById(R.id.editText_qtde_racao);
                    int qtde_dada = Integer.parseInt(qtde_a_ser_dada.getText().toString());
                    EditText max = sheetView.findViewById(R.id.editText_qtde_max);
                    qtd_max_agua = Integer.parseInt(max.getText().toString());
                    valorProgressoAgua = qtde_dada;
                    binding.progressBarAgua.setMax(qtd_max_agua);
                    updateProgressoAgua();
                    dialog.dismiss();
                }
            });

            dialog.setContentView(sheetView);
            dialog.show();
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
            public void adicionarAgua () {
                if (valorProgressoAgua == 0) {
                    updateProgressoAgua();
                    if ((binding.progressBarAgua.getMax() == 200) && (valorProgressoAgua <= 180)) {


                        valorProgressoAgua += 20;


                        valorProgressoAgua += valorProgressoAgua;

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


