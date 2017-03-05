package com.iastemesem.contact.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iastemesem.contact.model.Utente;

import java.util.ArrayList;

/**
 * Created by Gianfranco on 03/03/2017.
 */

public class Databasehandler extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "contact";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_UTENTI = "utenti";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME ="name" ;
    private static final String KEY_PSW ="psw" ;
    private static final String KEY_PHONE = "phone" ;
    private static final String KEY_SURNAME ="surname";


    public Databasehandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTE_TABLE = "CREATE TABLE " + TABLE_UTENTI + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_SURNAME + " TEXT," + KEY_PSW + " TEXT, " +KEY_PHONE+" TEXT " + ")";
        db.execSQL(CREATE_NOTE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UTENTI);
        // Create tables again
        onCreate(db);
    }

    public long addUtente (Utente utente){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, utente.getNome());
        values.put(KEY_SURNAME, utente.getCognome());
        values.put(KEY_PSW, utente.getPsw());
        values.put(KEY_PHONE, utente.getPhone());

        long insert = db.insert(TABLE_UTENTI, null,values);
        db.close();
        return insert; //Con questo posso settare l'id nella classe Utente
    }


    public ArrayList<Utente> getAlUtenti (){
        ArrayList<Utente> utenti = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_UTENTI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Utente utente = new Utente();
               // utente.setId(Integer.parseInt(cursor.getString(0)));
                utente.setNome(cursor.getString(1));
                utente.setPsw(cursor.getString(2));
                //utente.setPhone(cursor.getString(3));
                utenti.add(utente);
            }while (cursor.moveToNext());
        }

        return utenti;
    }


    public boolean controlPhone(String s) {
        String query = "SELECT "+KEY_PHONE+" FROM "+ TABLE_UTENTI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                String phone = cursor.getString(0);
                if (phone.equals(s)){
                    return true;
                }
            }while (cursor.moveToNext());
        }
        return false;
    }

    public ArrayList<Utente> getBySearch(String name) {
        ArrayList<Utente> utenti = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_UTENTI + " WHERE " + KEY_NAME + " =" + "'"+ name+"'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Utente utente = new Utente();
               // utente.setId(Integer.parseInt(cursor.getString(0)));
                utente.setNome(cursor.getString(1));
                utente.setCognome(cursor.getString(2));
                utente.setPsw(cursor.getString(3));
                //utente.setPhone(cursor.getString(4));
                utenti.add(utente);
            }while (cursor.moveToNext());
        }

        return utenti;
    }
}
