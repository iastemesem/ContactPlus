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
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * Created by Gianfranco on 03/03/2017.
 */

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    UtentiAdapter adapter;
    Toolbar toolbar;
    EditText search;
    ImageButton searchBtn;

    private static final String NO_USER = "No user founded";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        boolean trovato = false;
        if (v.getId() == R.id.main_search_btn){
            List<Utente> user = new ArrayList<>();
            for (Utente u: RegisterActivity.users){
                Log.d("CONTACT", RegisterActivity.users.toString());
                if (u.getNome().trim().equalsIgnoreCase(search.getText().toString())){
                    user.add(u);
                    adapter.setDataSet(user);
                    trovato = true;
                }
            }
            if (trovato != true){
                Toast.makeText(ContactActivity.this, NO_USER , Toast.LENGTH_SHORT).show();
            }
        }

    }
}
