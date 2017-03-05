package com.iastemesem.contact.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iastemesem.contact.R;
import com.iastemesem.contact.db.Databasehandler;
import com.iastemesem.contact.model.Utente;

/**
 * Created by Gianfranco on 03/03/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username,surname, psw, phone;
    Button signIn, login;
   // Databasehandler dbHandler = new Databasehandler(this);



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.register_name_ET);
        psw = (EditText) findViewById(R.id.register_psw_ET);
        phone = (EditText) findViewById(R.id.register_phone_ET);
        surname = (EditText) findViewById(R.id.register_surname_ET);

        signIn = (Button) findViewById(R.id.register_register_btn);
        login = (Button) findViewById(R.id.register_login_btn);

        signIn.setOnClickListener(this);
        login.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        Utente utente;
        if (v.getId() == R.id.register_register_btn){
            Log.d("USERNAME", username.getText().toString());
            if (!username.getText().toString().equals("") && !psw.getText().toString().equals("") && !phone.getText().toString().equals("") && !surname.getText().toString().equals("")){
                Toast.makeText(RegisterActivity.this, "Number phone already used", Toast.LENGTH_LONG).show();
                if(/*dbHandler.controlPhone(phone.getText().toString())*/ false) {
                    phone.setText("");

                } else {
                    //utente = new Utente(username.getText().toString(), psw.getText().toString(), phone.getText().toString(), surname.getText().toString());
                   // dbHandler.addUtente(utente);
                    intent = new Intent(RegisterActivity.this, ContactActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }
}
