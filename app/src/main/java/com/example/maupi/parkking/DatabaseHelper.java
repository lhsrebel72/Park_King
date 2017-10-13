package com.example.maupi.parkking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "parking_meter.db";

    /*********************************************************************************************
     * Creating the client table in the database and handling the following functions            *
     *      - Inserting a new account in the table                                               *
     *      - Checking user information to authenticate login                                    *
     *      - Checking the uniqueness of the user name a user enters while creating a new account*
     *********************************************************************************************/

    private static final String TABLE_NAME = "client";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_EMAIL = "email";
    SQLiteDatabase db;


    // Create table command
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_UNAME + " VARCHAR(255) NOT NULL, " +
            COLUMN_PASS + " VARCHAR(255) NOT NULL, " +
            COLUMN_EMAIL + " VARCHAR(255) NOT NULL);" ;

    public DatabaseHelper(Context context){
        super(context , DATABASE_NAME , null , DATABASE_VERSION);
    }

    // Creating the client table in the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        this.db = db;
    }

    // Inserting a new account in the client table
    public void insertContact(client c){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_UNAME , c.getUname());
        values.put(COLUMN_PASS , c.getPass());
        values.put(COLUMN_EMAIL , c.getEmail());

        db.insert(TABLE_NAME , null , values);
        db.close();
    }

    // Checking user information to authenticate login
    public String searchInfo(String uname){

        db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_UNAME + " , " + COLUMN_PASS + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query , null);
        String u , p = "not found";
        if(cursor.moveToFirst()){

            do{
                u = cursor.getString(cursor.getColumnIndex(COLUMN_UNAME));

                if(u.equals(uname)) {
                    p = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
                    break;
                }
            } while(cursor.moveToNext());
        }

        return p;
    }

    // Checking the username entered by the user while creating a new account to make sure it's unique
    public boolean uniqueUname(String uname){

        db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_UNAME + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query , null);
        String user;
        boolean unique = true;

        if(cursor.moveToFirst()){

            do{
                user = cursor.getString(cursor.getColumnIndex(COLUMN_UNAME));

                if(user.equals(uname)){
                    unique = false;
                } else{
                    unique = true;
                }
            }while(cursor.moveToNext());
        }

        return unique;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(drop);
        this.onCreate(db);
    }
}
