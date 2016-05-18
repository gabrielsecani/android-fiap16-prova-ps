package br.com.fiap.rm72468.provaps.provaps;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import java.util.Calendar;

public class ValidarLoginService extends Service {

    NotificationManager nm;
    Notification.Builder nb;

    public ValidarLoginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle b = intent.getExtras();

        String email = b.getString("email");
        String senha = b.getString("senha");

        Calendar cLimite = Calendar.getInstance();
        cLimite.add(Calendar.YEAR, -18);

        Calendar cUsuario = Calendar.getInstance();
        cUsuario.set(Calendar.YEAR, Integer.parseInt(b.getString("ano")));
        cUsuario.set(Calendar.MONTH, Integer.parseInt(b.getString("mes")));
        cUsuario.set(Calendar.DAY_OF_MONTH, Integer.parseInt(b.getString("dia")));

        String mensagem = "";

        if (!cLimite.after(cUsuario)) {
            mensagem = "Voce deve ter 18 anos!\n";
        }

        if ((email != null) && email.equalsIgnoreCase("PS")
                && (senha != null) && senha.equalsIgnoreCase("10")) {
            mensagem += "Login válido";
        } else {
            mensagem += "Login inválido";
        }

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nb = new Notification.Builder(this);

        nb.setContentTitle("Aviso");
        nb.setSmallIcon(R.mipmap.ic_launcher);
        nb.setContentText(mensagem);

        nb.setContentIntent(
                PendingIntent.getActivity(this, 0,
                        new Intent(this, LoginActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));

        Notification n = nb.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        nm.notify(100, n);

        Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone toque = RingtoneManager.getRingtone(this, som);
        toque.play();

        return 1;
    }
}
