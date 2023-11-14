package com.example.projetobabypet.util;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.view.textclassifier.TextLinks;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;

import com.example.projetobabypet.R;
import com.example.projetobabypet.activities.HomeActivity;
import com.example.projetobabypet.controller.ControllerCompromisso;
import com.example.projetobabypet.dao.firebase.FirebaseDB;
import com.example.projetobabypet.model.Compromisso;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotificacaoPorData extends BroadcastReceiver {
    Context context;
    Intent intent;
    String token;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        handler.post(verificaHorarioRunnable);
    }

    private void showNotification(Context context, String racao_agua) {
        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID);
        builder.setSmallIcon(R.drawable.minilogo).
                setContentTitle("Dar ração")
                .setContentText("Vai dar"+ racao_agua +"pro dog caralho")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelID);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelID, "racao/agua", importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        notificationManager.notify(0, builder.build());
    }

    @SuppressLint("ScheduleExactAlarm")
    private void scheduleNextNotification(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, NotificationService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        long interval = 60 * 1000;
        long triggerAtMillis = System.currentTimeMillis() + interval;

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
    }

    private void verificarHorarioParaNotificacao(Context context, Intent intent) {
        FirebaseDB firebaseDB = new FirebaseDB(context);
        int id = intent.getIntExtra("id", 0);
        ControllerCompromisso c = ControllerCompromisso.getInstance(context);
        Calendar calendar = Calendar.getInstance();
        List<Compromisso> compromissos = c.buscar_compromissos_categoria(id);


        for (Compromisso compromisso : compromissos) {
            String input = compromisso.getHora(); // Sua string no formato "HH:MM"

            String[] parts = input.split(":");
            String data = compromisso.getData();
            String[] valores = data.split("/");
            int dia = Integer.parseInt(valores[0]);
            int mes = Integer.parseInt(valores[1]);
            int ano = Integer.parseInt(valores[2]);

            if (parts.length == 2) {
                int hora = Integer.parseInt(parts[0]); // Valor antes dos dois pontos (horas)
                int min = Integer.parseInt(parts[1]); // Valor depois dos dois pontos (minutos)
                int horaAtual = calendar.get(Calendar.HOUR_OF_DAY);
                int minAtual = calendar.get(Calendar.MINUTE);

                int anoAt = calendar.get(Calendar.YEAR);
                int mesAt = calendar.get(Calendar.MONTH); // Janeiro é 0, Fevereiro é 1, ..., Dezembro é 11
                int diaAt = calendar.get(Calendar.DAY_OF_MONTH);


                if (anoAt == ano && mesAt == mes && dia == diaAt) {
                    if (horaAtual == hora && minAtual == min) {
                        AlertDialog.Builder caixademsg = new AlertDialog.Builder(context); //cria uma caixa de alerta
                        caixademsg.setTitle("Erro"); //Coloca o titulo da caixa
                        caixademsg.setMessage(hora + ":" + min + "\n" + horaAtual+ ":" + minAtual + "\n" + ano + "/" + mes + "/" + dia +  "\n" + anoAt+ "/" + mesAt+ "/" + diaAt); //coloca a mensagem da caixa
                        caixademsg.show(); //exibe a caixa pro usuario
                        showNotification(context, compromisso.getNome());

                    }

                }
            }
        }
        scheduleNextNotification(context);
    }

    private Handler handler = new Handler();
    private Runnable verificaHorarioRunnable = new Runnable() {
        @Override
        public void run() {
            verificarHorarioParaNotificacao(context, intent); // Verifique o horário

            handler.postDelayed(this, 60 * 1000); // 60 segundos * 1000 ms
        }
    };

}