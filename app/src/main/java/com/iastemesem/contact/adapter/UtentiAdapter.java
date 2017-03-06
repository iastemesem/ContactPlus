package com.iastemesem.contact.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DialogTitle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iastemesem.contact.R;
import com.iastemesem.contact.activity.ContactActivity;
import com.iastemesem.contact.model.Utente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gianfranco on 03/03/2017.
 */

public class UtentiAdapter extends RecyclerView.Adapter<UtentiAdapter.UtentiViewHolder> implements View.OnClickListener {
    List<Utente> dataSet = new ArrayList<>();

    public void addUtente (Utente utente){
        dataSet.add(utente);
        notifyDataSetChanged();
    }

    public void setDataSet (List<Utente> utenti){
        dataSet = utenti;
        notifyDataSetChanged();
    }

    @Override
    public UtentiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_utenti, parent, false);
        return new UtentiViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UtentiAdapter.UtentiViewHolder holder, int position) {
        Utente utente = dataSet.get(position);
        holder.nome.setText(utente.getNome());
        holder.surname.setText(utente.getCognome());
        holder.nickname.setText(utente.getNickname());
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onClick(View v) {

    }

    public void setDataSetForFirst(List<Utente> dataSetForFirst) {
        this.dataSet = dataSetForFirst;
    }


    class UtentiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome, surname, nickname;
        LinearLayout layout;


        UtentiViewHolder(View v) {
            super(v);
            nome = (TextView)v.findViewById(R.id.item_name_TV);
            surname = (TextView) v.findViewById(R.id.item_surname_TV);
            nickname = (TextView) v.findViewById(R.id.item_nickname_ET);
            layout = (LinearLayout) v.findViewById(R.id.item_layout_linear);
            layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}



