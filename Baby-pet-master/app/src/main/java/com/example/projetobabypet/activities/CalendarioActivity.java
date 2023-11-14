package com.example.projetobabypet.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.example.projetobabypet.R;
import com.example.projetobabypet.controller.ControllerCompromisso;
import com.example.projetobabypet.controller.ControllerUsuario;

import com.example.projetobabypet.model.Compromisso;
import com.example.projetobabypet.model.Usuario;
import com.example.projetobabypet.util.DecorarCalendario;
import com.example.projetobabypet.util.NotificacaoPorData;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalendarioActivity extends AppCompatActivity {

    com.example.projetobabypet.databinding.ActivityCalendarioBinding binding;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String email;
    Usuario usuario;
    NumberPicker horas, minuto;
    ControllerCompromisso c;
    int horaSelecionada, minutosSelecionados;
    EditText editTextHora;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.projetobabypet.databinding.ActivityCalendarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences("Log", MODE_PRIVATE);
        editor = sp.edit();
        email = sp.getString("email", "");

        usuario = usuario_logado(email);

        try {
            Intent intent = new Intent(this, NotificacaoPorData.class);
            NotificacaoPorData notificacaoPorData = new NotificacaoPorData();
            intent.putExtra("id", 999);
            notificacaoPorData.onReceive(this, intent);
        }catch (Exception e){

        }
        c =  ControllerCompromisso.getInstance(CalendarioActivity.this);
        binding.imageButtonVoltarCalendario.setOnClickListener(view -> {
            this.finish();
        });

        try {
            destacaDiasCalendario();
        } catch (Exception e) {

        }
        binding.calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int ano = date.getYear();
                int mes = date.getMonth();
                int dia = date.getDay();
                int id = verificaCompromisso(dia, mes, ano);
                if(id<0) {
                    dialogCadastrarCompromisso(date);
                } else {
                    dialogAlterarCompromisso(date, id);
                }
            }
        });


    }

    private void dialogAlterarCompromisso(@NonNull CalendarDay date, int id) {
        binding.imageButtonCadastrarComrpomissoCalendario.setOnClickListener(view -> {

            int ano = date.getYear();
            int mes = date.getMonth();
            int dia = date.getDay();

            BottomSheetDialog dialog = new BottomSheetDialog(CalendarioActivity.this, R.style.BottomSheetDialogTheme);
            View sheetView = LayoutInflater.from(CalendarioActivity.this)
                    .inflate(R.layout.bottom_dialog_calendario,findViewById(R.id.dialog_calendario));
            EditText editNome = sheetView.findViewById(R.id.editText_nome_compromisso_calendario);
            EditText descricao = sheetView.findViewById(R.id.editTextTextMultiLine_descricao_calendario);
            Button buttonSalvar = sheetView.findViewById(R.id.button_salvar_compromisso_calendario);
            Button buttonDeletar = sheetView.findViewById(R.id.button_deletar_data_calendario);
            Compromisso compromisso = c.buscarCompromissoId(id, 999);
            editTextHora = sheetView.findViewById(R.id.editText_hora_calendario);


            editTextHora.setOnClickListener(view1 -> {
                pegaHora();


            });

            editNome.setText(compromisso.getNome());
            descricao.setText(compromisso.getDescricao());
            editTextHora.setText(compromisso.getHora());
            buttonDeletar.setVisibility(View.VISIBLE);
            buttonSalvar.setOnClickListener(view2 -> {
                String hora = editTextHora.getText().toString();

                String data = dia + "/" + mes + "/" + ano;
                compromisso.setNome(editNome.getText().toString());
                compromisso.setDescricao(descricao.getText().toString());
                compromisso.setHora(hora);
                compromisso.setData(data);
                compromisso.setId_categoria(999);
                compromisso.setEmail_usuario(usuario.getEmail());
                c.atualizarCompromisso(compromisso);
                destacaDiasCalendario();
                Intent intent = new Intent(this, NotificacaoPorData.class);
                NotificacaoPorData notificacaoPorData = new NotificacaoPorData();
                intent.putExtra("id", 999);
                notificacaoPorData.onReceive(this, intent);
                dialog.dismiss();
            });
            buttonDeletar.setOnClickListener(view1 -> {
                c.deletar(compromisso);
                destacaDiasCalendario();
                dialog.dismiss();
            });

            dialog.setContentView(sheetView);
            dialog.show();

        });
    }

    private void dialogCadastrarCompromisso(@NonNull CalendarDay date) {
        binding.imageButtonCadastrarComrpomissoCalendario.setOnClickListener(view -> {

            int ano = date.getYear();
            int mes = date.getMonth();
            int dia = date.getDay();

            BottomSheetDialog dialog = new BottomSheetDialog(CalendarioActivity.this, R.style.BottomSheetDialogTheme);
            View sheetView = LayoutInflater.from(CalendarioActivity.this)
                    .inflate(R.layout.bottom_dialog_calendario,findViewById(R.id.dialog_calendario));

            Button button = sheetView.findViewById(R.id.button_salvar_compromisso_calendario);

            editTextHora = sheetView.findViewById(R.id.editText_hora_calendario);

            editTextHora.setOnClickListener(view1 -> {
                pegaHora();
            });


            button.setOnClickListener(view2 -> {
                EditText editNome = sheetView.findViewById(R.id.editText_nome_compromisso_calendario);
                EditText descricao = sheetView.findViewById(R.id.editTextTextMultiLine_descricao_calendario);
                String data = dia + "/" + mes + "/" + ano;
                String hora = editTextHora.getText().toString();
                Compromisso compromisso = new Compromisso(usuario.getEmail(), 999, editNome.getText().toString(), descricao.getText().toString(), hora, data);
                c.cadastrarCompromissoCategoria(compromisso);
                destacaDiasCalendario();
                dialog.dismiss();
            });

            dialog.setContentView(sheetView);
            dialog.show();
            Intent intent = new Intent(this, NotificacaoPorData.class);
            NotificacaoPorData notificacaoPorData = new NotificacaoPorData();
            intent.putExtra("id", 999);
            notificacaoPorData.onReceive(this, intent);
        });
    }

    private void pegaHora() {
        calendar = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hora, int minutos) {
                calendar.set(Calendar.HOUR_OF_DAY, hora);
                calendar.set(Calendar.MINUTE, minutos);
                editTextHora.setText(atualizaHora());
            }
        };

        int horaAtual = calendar.get(Calendar.HOUR_OF_DAY);
        int minutosAtuais = calendar.get(Calendar.MINUTE);

        new TimePickerDialog(CalendarioActivity.this, time, horaAtual, minutosAtuais, true).show();
    }

    private int verificaCompromisso(int dia, int mes, int ano){
        List<Compromisso> compromissos = c.buscar_compromissos_categoria(999);
        for(Compromisso compromisso : compromissos){
            String data = compromisso.getData();
            String[] valores = data.split("/");
            int diaC = Integer.parseInt(valores[0]);
            int mesC = Integer.parseInt(valores[1]);
            int anoC = Integer.parseInt(valores[2]);

            if(diaC == dia && mesC == mes && anoC == ano){
                return compromisso.getId();
            } else {
                return -1;
            }
        //nada
        }
        return -1;
    }

    private void destacaDiasCalendario() {
        binding.calendarView.removeDecorators();
        List<Compromisso> compromissos = new ArrayList<>();
        compromissos = c.buscar_compromissos_categoria(999);
        for (Compromisso compromisso : compromissos) {

            String data = compromisso.getData();
            String[] valores = data.split("/");
            int dia = Integer.parseInt(valores[0]);
            int mes = Integer.parseInt(valores[1]);
            int ano = Integer.parseInt(valores[2]);
            List<CalendarDay> dias = new ArrayList<>();
            dias.add(CalendarDay.from(ano, mes, dia));
            int cor = Color.BLUE;
            binding.calendarView.addDecorator(new DecorarCalendario(cor, dias));
        }
    }

    private Usuario usuario_logado(String email) {
        try {
            ControllerUsuario c = ControllerUsuario.getInstancia(this);
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




    private String atualizaHora() {
        String formato = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.getDefault());
        return sdf.format(calendar.getTime());
    }

}