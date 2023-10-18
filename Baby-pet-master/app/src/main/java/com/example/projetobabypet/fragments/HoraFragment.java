package com.example.projetobabypet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.example.projetobabypet.R;
import com.example.projetobabypet.databinding.FragmentHoraBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class HoraFragment extends Fragment {

    FragmentHoraBinding binding;
    NumberPicker numberPickerhora;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding = FragmentHoraBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.buttonAdicionarHora.setOnClickListener(view1 -> {
            BottomSheetDialog dialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
            View sheetView= LayoutInflater.from(getContext()).inflate(
                    R.layout.layout_bottom_sheet_dialog_cadastrar_hora, view.findViewById(R.id.bottomSheetInicio)

            );
            sheetView.findViewById(R.id.button_salvar_hora).setOnClickListener(view2 -> {

            });

            numberPickerhora = sheetView.findViewById(R.id.numberPicker_hora);
            numberPickerhora.setMinValue(0);
            numberPickerhora.setMaxValue(24);

            dialog.setContentView(sheetView);
            dialog.show();
        });

        return view;

    }
}