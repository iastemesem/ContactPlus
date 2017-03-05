package com.iastemesem.contact.model;

import java.util.Objects;

/**
 * Created by Gianfranco on 03/03/2017.
 */

public class Utente  {
    private long id;
    private String nome, cognome;
    private String psw;
    private String phone;
    private String email;
    private int idGroup;

    public Utente(){
    }

    //Constructor
    public Utente(String nome, String cognome, String phone, String email, String psw, long id) {
        //this.id = id; Inizializzato nel DB
        this.id = id;
        this.nome = nome;
        this.psw = psw;
        this.phone = phone;
        this.email = email;
        this.cognome = cognome;
        //SI POTRA' UNIRE AL GRUPPO DOPO ESSERSI REGISTRATO
    }



    //Getter and Setter
    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
}
