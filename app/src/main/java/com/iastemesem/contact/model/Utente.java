package com.iastemesem.contact.model;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Gianfranco on 03/03/2017.
 */

public class Utente  {
    private String nome, cognome, nickname;
    private String psw;
    private String phone;
    private String email;
    private int idGroup;

    private final static String KEY_CHILD_NOME = "nome";
    private final static String KEY_CHILD_COGNOME = "cognome";
    private final static String KEY_CHILD_PSW = "psw";
    private final static String KEY_CHILD_EMAIL = "email";
    private final static String KEY_CHILD_PHONE = "phone";
    private final static String KEY_NICKNAME = "nickname";

    public static Utente fromHashMap(HashMap<String, Object> map) {
        return new Utente(
                (String) map.get(KEY_CHILD_NOME),
                (String) map.get(KEY_CHILD_COGNOME),
                (String) map.get(KEY_CHILD_PHONE),
                (String) map.get(KEY_CHILD_EMAIL),
                (String) map.get(KEY_CHILD_PSW),
                (String) map.get(KEY_NICKNAME)
        );
    }

    public Utente(){
    }

    //Constructor
    public Utente(String nome, String cognome, String phone, String email, String psw, String nickname) {
        //this.id = id; Inizializzato nel DB
        this.nome = nome;
        this.psw = psw;
        this.phone = phone;
        this.email = email;
        this.cognome = cognome;
        this.nickname = nickname;
        //SI POTRA' UNIRE AL GRUPPO DOPO ESSERSI REGISTRATO
    }

    //Getter and Setter
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
