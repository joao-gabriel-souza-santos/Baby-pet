package com.example.projetobabypet.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.projetobabypet.R;
import com.example.projetobabypet.adapter.hora.AdapterListaHora;
import com.example.projetobabypet.controller.ControllerHora;
import com.example.projetobabypet.controller.ControllerUsuario;
import com.example.projetobabypet.databinding.FragmentHoraBinding;
import com.example.projetobabypet.interfaces.RecyclerClickHora;
import com.example.projetobabypet.model.Compromisso;
import com.example.projetobabypet.model.Usuario;
import com.example.projetobabypet.notificacao.NotificationService;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class HoraFragment extends Fragment implements RecyclerClickHora {

    FragmentHoraBinding binding;
    NumberPicker numberPickerhora, minutos;
    RadioGroup radioGroup;
    SharedPreferences sp;
    private SharedPreferences.Editor editor;
    String email;
    Usuario usuario;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding = FragmentHoraBinding.inflate(inflater, container, false);
         view = binding.getRoot();

        sp = getActivity().getSharedPreferences("Log", Context.MODE_PRIVATE);
        editor = sp.edit();
        email = sp.getString("email", "");
        usuario = usuario_logado(email);

        try {
//            VerificaNotificacao verificaNotificacao = new VerificaNotificacao();
//            verificaNotificacao.execute();
            Intent serviceIntent = new Intent(getActivity(), NotificationService.class);
            serviceIntent.putExtra("id", usuario.getId());
            NotificationService notificationService = new NotificationService();
            notificationService.onReceive(getContext(), serviceIntent);
        }catch (Exception e){

                AlertDialog.Builder caixademsg = new AlertDialog.Builder(getContext()); //cria uma caixa de alerta
                caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                caixademsg.setMessage(e.getMessage()); //coloca a mensagem da caixa
                caixademsg.show(); //exibe a caixa pro usuario

        }

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewHora.setLayoutManager(manager);
        AdapterListaHora adapterListaHora = new AdapterListaHora(getContext(), usuario.getId(), this);
        binding.recyclerViewHora.setAdapter(adapterListaHora);

        binding.buttonAdicionarHora.setOnClickListener(view1 -> {

            createDialog();
        });



        return view;

    }

    private void createDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
        View sheetView = LayoutInflater.from(getContext()).inflate(
                R.layout.layout_bottom_sheet_dialog_cadastrar_hora, view.findViewById(R.id.bottomSheetInicio)
        );
        numberPickerhora = sheetView.findViewById(R.id.numberPicker_hora);
        minutos = sheetView.findViewById(R.id.numberPicker_minutos);
        radioGroup = sheetView.findViewById(R.id.radioGroup);


        numberPickerhora.setMinValue(0);
        numberPickerhora.setMaxValue(24);

        minutos.setMaxValue(59);
        minutos.setMinValue(0);
        sheetView.findViewById(R.id.button_salvar_hora).setOnClickListener(view2 -> {

            int itemSelect = radioGroup.getCheckedRadioButtonId();

            if (itemSelect != -1) {
                RadioButton escolha = sheetView.findViewById(itemSelect);
                String descricao = escolha.getText().toString();
                String hora = String.format("%02d:%02d", numberPickerhora.getValue(), minutos.getValue());
                Compromisso compromisso = new Compromisso(usuario.getId(), hora, descricao);
                ControllerHora c = ControllerHora.getInstance(getContext());
                c.cadastrarNotificacao(compromisso);
                try {
//                    VerificaNotificacao verificaNotificacao = new VerificaNotificacao();
//                    verificaNotificacao.execute();
                    Intent serviceIntent = new Intent(getActivity(), NotificationService.class);
                    serviceIntent.putExtra("id", usuario.getId());
                    NotificationService notificationService = new NotificationService();
                    notificationService.onReceive(getContext(), serviceIntent);
                } catch (Exception e){
                    AlertDialog.Builder caixademsg = new AlertDialog.Builder(getContext()); //cria uma caixa de alerta
                    caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                    caixademsg.setMessage(e.getMessage()); //coloca a mensagem da caixa
                    caixademsg.show(); //exibe a caixa pro usuario
                }

                AdapterListaHora adapter = new AdapterListaHora(getContext(), usuario.getId(), this);
                binding.recyclerViewHora.setAdapter(adapter);
                dialog.dismiss();
            }

        });


        dialog.setContentView(sheetView);
        dialog.show();
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


    @Override
    public void onClick(Compromisso compromisso) {
        try {
            BottomSheetDialog dialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
            View sheetView = LayoutInflater.from(getContext()).inflate(
                    R.layout.layout_bottom_sheet_dialog_cadastrar_hora, view.findViewById(R.id.bottomSheetInicio)
            );
            numberPickerhora = sheetView.findViewById(R.id.numberPicker_hora);
            minutos = sheetView.findViewById(R.id.numberPicker_minutos);
            radioGroup = sheetView.findViewById(R.id.radioGroup);


            numberPickerhora.setMinValue(0);
            numberPickerhora.setMaxValue(24);

            minutos.setMaxValue(59);
            minutos.setMinValue(0);
            sheetView.findViewById(R.id.button_salvar_hora).setOnClickListener(view2 -> {

                int itemSelect = radioGroup.getCheckedRadioButtonId();

                if (itemSelect != -1) {
                    RadioButton escolha = sheetView.findViewById(itemSelect);
                    String descricao = escolha.getText().toString();
                    String hora = String.format("%02d:%02d", numberPickerhora.getValue(), minutos.getValue());
                    compromisso.setHora(hora);
                    compromisso.setDescricao(descricao);
                    ControllerHora c = ControllerHora.getInstance(getContext());
                    c.atualizarNotificacao(compromisso);
                    VerificaNotificacao verificaNotificacao = new VerificaNotificacao();
                    verificaNotificacao.execute();
                    dialog.dismiss();
                    AdapterListaHora adapterListaHora = new AdapterListaHora(getContext(), usuario.getId(), this);
                    binding.recyclerViewHora.setAdapter(adapterListaHora);
                }

            });


            dialog.setContentView(sheetView);
            dialog.show();

        } catch (Exception a) {

        }
    }

    @Override
    public void onLongClick(Compromisso position) {
        ControllerHora c = ControllerHora.getInstance(getContext());
        c.deletar(position);
        AdapterListaHora adapterListaHora = new AdapterListaHora(getContext(), usuario.getId(), this);
        binding.recyclerViewHora.setAdapter(adapterListaHora);
    }


 class VerificaNotificacao extends AsyncTask{

     @Override
     protected Object doInBackground(Object[] objects) {

         try {
             Intent serviceIntent = new Intent(getActivity(), NotificationService.class);
             serviceIntent.putExtra("id", usuario.getId());
             NotificationService notificationService = new NotificationService();
             notificationService.onReceive(getContext(), serviceIntent);


         } catch (Exception e){
             AlertDialog.Builder caixademsg = new AlertDialog.Builder(getContext(), usuario.getId()); //cria uma caixa de alerta
             caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
             caixademsg.setMessage(e.getMessage()); //coloca a mensagem da caixa
             caixademsg.show(); //exibe a caixa pro usuario
         }

         return true;
     }
 }

}