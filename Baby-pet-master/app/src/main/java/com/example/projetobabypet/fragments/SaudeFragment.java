package com.example.projetobabypet.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.CalendarioActivity;
import com.example.projetobabypet.adapter.categoria.AdapterCategoria;
import com.example.projetobabypet.controller.ControllerCategoria;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.FragmentSaudeBinding;
import com.example.projetobabypet.model.Categoria;
import com.example.projetobabypet.model.Usuario;
import com.example.projetobabypet.util.NotificacaoPorData;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;


public class SaudeFragment extends Fragment {
    NumberPicker numberPickerhora, minutos;
    FragmentSaudeBinding binding;
    EditText editData;

    Calendar calendar = Calendar.getInstance();

    String email;
    Usuario usuario;
    SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSaudeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        sp = getActivity().getSharedPreferences("Log", Context.MODE_PRIVATE);
        editor = sp.edit();
        email = sp.getString("email", "");
        usuario = usuario_logado(email);


        try {
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            binding.recyclerViewAfazeresSaude.setLayoutManager(manager);
            Intent intent = new Intent(getActivity(), NotificacaoPorData.class);
            AdapterCategoria adapterCategoria = new AdapterCategoria(getContext(), usuario, intent);
            binding.recyclerViewAfazeresSaude.setAdapter(adapterCategoria);



        } catch (Exception a) {

        }

        try {
            binding.imageButtonCadastrarAfazerSaude.setOnClickListener(view1 -> {
                BottomSheetDialog dialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
                View sheetView = LayoutInflater.from(getContext())
                        .inflate(R.layout.bottom_dialog_cadastrar_categoria, view.findViewById(R.id.bottomCategoria));

                Button cadastrar = sheetView.findViewById(R.id.button_cadastrar_categoria);
                cadastrar.setOnClickListener(view2 -> {
                    EditText editNome = sheetView.findViewById(R.id.editText_nome_categoria);
                    String nomeCategoria = editNome.getText().toString();
                    ControllerCategoria c = ControllerCategoria.getInstance(getContext());
                    Categoria categoria = new Categoria(nomeCategoria, usuario.getEmail());
                    categoria.setEmail_usuario(usuario.getEmail());
                    c.cadastrar(categoria);
                    Intent intent = new Intent(getActivity(), NotificacaoPorData.class);
                    AdapterCategoria adapterCategoria = new AdapterCategoria(getContext(), usuario, intent);
                    binding.recyclerViewAfazeresSaude.setAdapter(adapterCategoria);
                    dialog.dismiss();
                });

                dialog.setContentView(sheetView);
                dialog.show();
            });
        } catch (Exception e) {

        }


        binding.imageButtonCalendario.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), CalendarioActivity.class);
            startActivity(intent);
        });

        return view;
    }


    private Usuario usuario_logado(String email) {
        try {
            ControllerUsuario c = ControllerUsuario.getInstancia(getContext());
            Usuario usuario = c.buscarPorEmail(email);
            return usuario;
        } catch (Exception e) {
            AlertDialog.Builder caixademsg = new AlertDialog.Builder(getContext()); //cria uma caixa de alerta
            caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
            caixademsg.setMessage("" + e.getMessage()); //coloca a mensagem da caixa
            caixademsg.show(); //exibe a caixa pro usuario
        }
        return null;
    }


}

