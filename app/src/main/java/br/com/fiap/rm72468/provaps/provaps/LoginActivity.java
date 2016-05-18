package br.com.fiap.rm72468.provaps.provaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail = null;
    private EditText edtSenha = null;
    private DatePicker dtNascimento = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        dtNascimento = (DatePicker) findViewById(R.id.dtNascimento);

        Calendar c = Calendar.getInstance();
        Date d = new Date();
        //c.set(d.getYear(),d.getMonth(),d.getDay());
        Log.i("Data do Login", "Hoje - 18anos" + c.getTimeInMillis());
        c.add(Calendar.YEAR, -17);
        Log.i("Data do Login", "Hoje - 18anos" + c.getTimeInMillis());
        dtNascimento.setMaxDate(c.getTimeInMillis());

    }

    public void validarLogin(View v) {

        Intent i = new Intent(this, ValidarLoginService.class);

        Log.d("LoginActivity",
                edtEmail.getText() + "/ " + edtSenha.getText() + "/ " +
                        dtNascimento.getDayOfMonth() + "/" +
                        dtNascimento.getMonth() + "/" +
                        dtNascimento.getYear());

        i.putExtra("email", String.valueOf(edtEmail.getText()));
        i.putExtra("senha", String.valueOf(edtSenha.getText()));
        i.putExtra("ano", String.valueOf(dtNascimento.getYear()));
        i.putExtra("mes", String.valueOf(dtNascimento.getMonth()));
        i.putExtra("dia", String.valueOf(dtNascimento.getDayOfMonth()));

        startService(i);

    }

}
