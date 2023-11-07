package com.example.projetobabypet.adapter.categoria;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetobabypet.R;
import com.example.projetobabypet.adapter.afazer.AdapterAfazer;
import com.example.projetobabypet.controller.ControllerCategoria;
import com.example.projetobabypet.controller.ControllerCompromisso;
import com.example.projetobabypet.model.Categoria;
import com.example.projetobabypet.model.Compromisso;
import com.example.projetobabypet.model.Usuario;
import com.example.projetobabypet.util.NotificacaoPorData;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterCategoria extends RecyclerView.Adapter<HolderCategoria> {

    private Context context;
    private static List<Categoria> categorias;

    ControllerCategoria c;

    Calendar calendar;
    EditText editData;
    NumberPicker numberPickerhora, minutos;
    Usuario usuario;
    Categoria categoria;
    Intent intent;
    public AdapterCategoria(Context context, Usuario usuario, Intent intent){
        this.context = context;
        this.usuario = usuario;
        this.intent = intent;
        c = ControllerCategoria.getInstance(context);
       try{
           categorias = c.buscarCategorias(usuario.getId());
       }catch (Exception e){}

    }
    @NonNull
    @Override
    public HolderCategoria onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_saude, parent, false);
        return new HolderCategoria(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCategoria holder, int position) {

        try {
             categoria = categorias.get(position);
            holder.nome.setText(categoria.getNome());
            AdapterAfazer adapterAfazer = new AdapterAfazer(context, categoria.getId());
            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recycler.setLayoutManager(manager);
            holder.recycler.setAdapter(adapterAfazer);
            NotificacaoPorData notificacaoPorData = new NotificacaoPorData();
            intent.putExtra("id", categoria.getId());
            notificacaoPorData.onReceive(context, intent);

        } catch (Exception e){

        }
            try{
                holder.botao.setOnClickListener(view -> {
                    calendar = Calendar.getInstance();
                    BottomSheetDialog dialog = new BottomSheetDialog(context, R.style.BottomSheetDialogTheme);
                    View sheetView = LayoutInflater.from(context).inflate(
                            R.layout.bottom_dialog_cadastrar_afazer, view.findViewById(R.id.bottomsheetAfazer)
                    );

                    editData = sheetView.findViewById(R.id.editTextDate_data_afazer);

                    pegaData();

                    numberPickerhora = sheetView.findViewById(R.id.numberPicker_hora);
                    minutos = sheetView.findViewById(R.id.numberPicker_minutos);

                    EditText editnome = sheetView.findViewById(R.id.editText_nome_afazer);
                    EditText editDescricao= sheetView.findViewById(R.id.editTextTextMultiLine_descricao);
                    editData =sheetView.findViewById(R.id.editTextDate_data_afazer);
                    numberPickerhora.setMinValue(0);
                    numberPickerhora.setMaxValue(24);

                    minutos.setMaxValue(59);
                    minutos.setMinValue(0);

                    sheetView.findViewById(R.id.button_salvar_afazer).setOnClickListener(view3 -> {
                        String nome = editnome.getText().toString();
                        String descricao = editDescricao.getText().toString();
                        String data = atualizaData();
                        String hora = String.format("%02d:%02d", numberPickerhora.getValue(), minutos.getValue());
                        Compromisso compromisso = new Compromisso(usuario.getId(),categoria.getId(), nome, descricao, hora, data);
                        ControllerCompromisso c = ControllerCompromisso.getInstance(context);
                        c.cadastrarCompromissoCategoria(compromisso);




                        dialog.dismiss();
                        AdapterAfazer adapterAfazer = new AdapterAfazer(context, categoria.getId());
                        holder.recycler.setAdapter(adapterAfazer);
                       NotificacaoPorData notificacaoPorData = new NotificacaoPorData();
                       notificacaoPorData.onReceive(context, intent);
                    });
                    dialog.setContentView(sheetView);
                    dialog.show();
                });
            }catch (Exception e){

            }


            holder.itemView.setOnLongClickListener(view -> {
                Categoria categoria = categorias.get(position);
                c.deletar(categoria.getId());
                atualizarLista();
                return true;
            });
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    private void pegaData() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                calendar.set(Calendar.YEAR, ano);
                calendar.set(Calendar.MONTH, mes);
                calendar.set(Calendar.DAY_OF_MONTH, dia);
                editData.setText(atualizaData());

            }
        };

        editData.setOnClickListener(view2 -> {
            new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }


    private String atualizaData(){
        String formato = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato, Locale.US);
        return dateFormat.format(calendar.getTime());
    }

    private void atualizarLista(){
        categorias.clear();
        categorias = c.buscarCategorias(usuario.getId());
        categorias.addAll(categorias);
        notifyDataSetChanged();
    }
}
