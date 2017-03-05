package com.iastemesem.contact.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iastemesem.contact.R;
import com.iastemesem.contact.adapter.UtentiAdapter;
import com.iastemesem.contact.db.Databasehandler;
import com.iastemesem.contact.model.Utente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Gianfranco on 03/03/2017.
 */

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    UtentiAdapter adapter;
    Databasehandler dbHandler = new Databasehandler(this);
    Toolbar toolbar;
    EditText search;
    ImageButton searchBtn;


    DatabaseReference mDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Read from the database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               Utente utente = dataSnapshot.child("utenti").child("0").getValue(Utente.class);
             Log.d("EHIIIIIII", "Value is: " + utente.getNome());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("EHIIIIII", "Failed to read value.", error.toException());
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        search = (EditText) findViewById(R.id.main_search_ET);
        searchBtn = (ImageButton) findViewById(R.id.main_search_btn);
        searchBtn.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.utenti_RV);
        layoutManager = new LinearLayoutManager(this);
        adapter = new UtentiAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //adapter.setDataSet(dbHandler.getBySearch(search.getText().toString()));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_group) {

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        mDatabase.child("utenti").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Utente> utenti = new ArrayList<Utente>();
                int x =0;
                String i = String.valueOf(x);
                Utente utente;
                do {
                    try {
                        utente = dataSnapshot.child(i).getValue(Utente.class);
                        if (utente != null){
                            utenti.add(utente);
                            x++;
                            i = String.valueOf(x);
                            Log.d("OKKKKKK", utente.getNome());
                        }
                        adapter.setDataSet(utenti);
                    }catch (NullPointerException e){
                        Log.d("ERRRRRRR", e.getMessage());
                        break;
                    }

                }while (utente != null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("user", "cancellato");
            }
        });
    }
}
