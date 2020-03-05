package com.example.healthy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.healthy.Classes.Account;
import com.example.healthy.Classes.Profile;

public class DatabaseHandler extends SQLiteOpenHelper {

    //DB
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "healthyDB";
    //TABLE ACCOUNT
    private static final String TABLE_ACCOUNT = "account";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    //TABLE PROFILE
    private static final String TABLE_PROFILE = "profile";
    private static final String PROFILE_ID = "id";
    private static final String PROFILE_NOM = "nom";
    private static final String PROFILE_PRENOM = "prenom";
    private static final String PROFILE_ANNIVERSAIRE = "anniversaire";
    private static final String PROFILE_AGE = "age";
    private static final String PROFILE_SEXE = "sexe";
    private static final String PROFILE_POIDS = "poids";
    private static final String PROFILE_TAILLE = "taille";
    private static final String PROFILE_IMC = "imc";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_ACCOUNT + "("
                + KEY_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT ," + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD+ " TEXT" + ")";
        String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE + "("
                + PROFILE_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT ," + PROFILE_NOM + " TEXT,"
                + PROFILE_PRENOM+ " TEXT,"+ PROFILE_ANNIVERSAIRE+ " TEXT,"+ PROFILE_AGE +" INTEGER,"
                + PROFILE_SEXE+ " TEXT,"+ PROFILE_POIDS + " INTEGER,"+PROFILE_TAILLE+" DOUBLE,"+
                PROFILE_IMC+" INTEGER" + ")";

        db.execSQL(CREATE_ACCOUNT_TABLE);
        db.execSQL(CREATE_PROFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        onCreate(db);
    }

    //Account methods
    public void addAccount(Account compte) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, compte.get_email());
        values.put(KEY_PASSWORD, compte.get_password());
        db.insert(TABLE_ACCOUNT, null, values);
        db.close();
    }

    public Account getAccount(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ACCOUNT, new String[] { KEY_ID,
                        KEY_EMAIL,KEY_PASSWORD}, KEY_EMAIL + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Account compte = new Account(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return compte;
    }

    public Boolean checkUserAccount(String email , String pass)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_ACCOUNT+" where "+KEY_EMAIL+"= '"+email+"' and "+KEY_PASSWORD+"= '"+pass+"' ;",null);
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
            return false;
    }


    public  Boolean checkEmailExists(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_ACCOUNT+" where "+KEY_EMAIL+" = '"+email+"';",null);
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
            return false;
    }

    //PROFILE METHODS

    public void addProfile(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROFILE_NOM, profile.get_nom());
        values.put(PROFILE_PRENOM, profile.get_prénom());
        values.put(PROFILE_ANNIVERSAIRE, profile.get_anniversaire());
        values.put(PROFILE_AGE, profile.get_age());
        values.put(PROFILE_SEXE, profile.get_sexe());
        values.put(PROFILE_POIDS, profile.get_poids());
        values.put(PROFILE_TAILLE, profile.get_taille());
        values.put(PROFILE_IMC, profile.get_imc());
        db.insert(TABLE_PROFILE, null, values);
        db.close();
    }

    public Profile getProfile(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROFILE, new String[] { PROFILE_ID,
                        PROFILE_NOM,PROFILE_PRENOM,PROFILE_ANNIVERSAIRE,PROFILE_AGE,PROFILE_SEXE,PROFILE_POIDS,PROFILE_TAILLE,PROFILE_IMC}, PROFILE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Profile profil = new Profile(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)),
                cursor.getString(5),Integer.parseInt(cursor.getString(6)),Integer.parseInt(cursor.getString(7)),Double.parseDouble(cursor.getString(8)));
        return profil;
    }












}
