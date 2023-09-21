package com.example.projetobabypet.fragments;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.adapter.AdapterRecycleHome;


import com.example.projetobabypet.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class HomeFragment extends Fragment {

    public static List<String> horas = new ArrayList<>();
    FragmentHomeBinding binding;

    public int valorProgressoRacao = 0;
    public int valorProgressoAgua = 0;
    public int qtd_max_racao = 0, qtd_max_agua = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        updateProgressoAgua();
        updateProgressoRacao();
        binding.recycleViewHora.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleViewHora.setAdapter(new AdapterRecycleHome(getContext().getApplicationContext()));

        binding.buttonDarRacao.setOnClickListener(view1 -> {
            adicionarRacao();
            updateProgressoRacao();

            horas.add(getHoras());
            AdapterRecycleHome.atualizarLista(1);
            binding.recycleViewHora.setAdapter(new AdapterRecycleHome(getContext().getApplicationContext()));
        });

        binding.buttonDarAgua.setOnClickListener(view1 -> {
            adicionarAgua();
            updateProgressoAgua();

            horas.add(getHoras());
            AdapterRecycleHome.atualizarLista(2);
            binding.recycleViewHora.setAdapter(new AdapterRecycleHome(getContext().getApplicationContext()));

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
                try {
                    TimeUnit.SECONDS.sleep((long)0.5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            if (valorProgressoRacao <= binding.progressBarRacao.getMax()) {
                valorProgressoRacao += valorProgressoRacao;
                updateProgressoRacao();
                try {
                    TimeUnit.SECONDS.sleep((long)0.5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
                        try {
                            TimeUnit.SECONDS.sleep((long)0.5);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    if (valorProgressoAgua <= qtd_max_agua) {
                        valorProgressoAgua += valorProgressoAgua;
                        updateProgressoAgua();
                        try {
                            TimeUnit.SECONDS.sleep((long)0.5);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }

            public String getHoras(){
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());

               String hora = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE));

                return hora;
            }

}


