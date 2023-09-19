package com.example.projetobabypet.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.Button;

import com.example.projetobabypet.R;

public class Bottom extends BottomSheetDialogFragment {

    BottomSheetBehavior behavior;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog)  super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.layout_bottom_sheet_inicio, null);

       Button btn = view.findViewById(R.id.button_salvar_racao);

        dialog.setContentView(view);
        behavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
