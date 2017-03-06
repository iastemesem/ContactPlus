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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iastemesem.contact.R;
import com.iastemesem.contact.db.Databasehandler;
import com.iastemesem.contact.model.Utente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * Created by Gianfranco on 03/03/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username,surname, psw, phone, nickname, email;
    Button signIn, login;

    private final static String KEY_CHILD_NOME = "nome";
    private final static String KEY_CHILD_COGNOME = "cognome";
    private final static String KEY_CHILD_PSW = "psw";
    private final static String KEY_CHILD_EMAIL = "email";
    private final static String KEY_CHILD_PHONE = "phone";
    private final static String KEY_NICKNAME = "nickname";

    public static List<Utente> users = new ArrayList<>();
    public static List<Utente> users2 = new ArrayList<>();

    private  static DatabaseReference mDatabase;

    private  static  final String KEY_CHILD_UTENTI = "utenti";
    private  static final String TAG = "user";
    private DatabaseReference mDb;


    public static void setUsersFirebase () {

        users.clear();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(KEY_CHILD_UTENTI).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               // Log.v("User : ", "DATA:" + dataSnapshot.getValue());
                HashMap<String, Object> utentiJson = (HashMap<String, Object>) dataSnapshot.getValue();
               // Log.d(TAG, utentiJson.toString());
                List<String> keys = new ArrayList<>();
                for (String key : utentiJson.keySet()){
                    keys.add(key);
                }
              //  Log.d(TAG, keys.toString());
                for (int i = 0; i < utentiJson.size(); i++){
                    HashMap<String, Object> utente = (HashMap<String, Object>) utentiJson.get(keys.get(i));
                    Utente user = Utente.fromHashMap(utente);
                    Log.d("UTENTE", user.toString());
                    users.add(user);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "cancellato");
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setUsersFirebase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.register_name_ET);
        psw = (EditText) findViewById(R.id.register_psw_ET);
        phone = (EditText) findViewById(R.id.register_phone_ET);
        surname = (EditText) findViewById(R.id.register_surname_ET);
        nickname = (EditText) findViewById(R.id.register_nickname_ET);
        email = (EditText) findViewById(R.id.register_email_ET);

        signIn = (Button) findViewById(R.id.register_register_btn);
        login = (Button) findViewById(R.id.register_login_btn);

        signIn.setOnClickListener(this);
        login.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        Utente utente;
        boolean correct = false;

        if (v.getId() == R.id.register_register_btn){
            Log.d("USERNAME", username.getText().toString());
            if (!email.getText().toString().equals("") && !username.getText().toString().equals("") && !psw.getText().toString().equals("") && (!phone.getText().toString().equals("") && phone.getText().toString().length()==10 ) && !surname.getText().toString().equals("") && !nickname.getText().toString().equals("")){
               for (Utente u: users){
                    if (u.getPhone().equals(phone.getText().toString()) || u.getNickname().equals(nickname.getText().toString())){
                        Toast.makeText(RegisterActivity.this, "Number or Nickname already used", Toast.LENGTH_LONG).show();
                        phone.setText("");
                        nickname.setText("");
                        correct = true;
                        break;
                    }
                }
                if (correct != true){
                    mDb = mDatabase.child(KEY_CHILD_UTENTI).push();
                    mDb.child(KEY_CHILD_NOME).setValue(username.getText().toString());
                    mDb.child(KEY_CHILD_COGNOME).setValue(surname.getText().toString());
                    mDb.child(KEY_CHILD_EMAIL).setValue(email.getText().toString());
                    mDb.child(KEY_CHILD_PHONE).setValue(phone.getText().toString());
                    mDb.child(KEY_CHILD_PSW).setValue(psw.getText().toString());
                    mDb.child(KEY_NICKNAME).setValue(nickname.getText().toString());
                    setUsersFirebase();
                    intent = new Intent(RegisterActivity.this, ContactActivity.class);
                    startActivity(intent);
                    finish();
                }
            }else {
                Toast.makeText(RegisterActivity.this, "Field not complete", Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.register_login_btn){
            intent = new Intent(RegisterActivity.this, ContactActivity.class);
            startActivity(intent);
        }
    }
}
