package com.example.maupi.parkking;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "parking_meter.db";
    private static final String TABLE_NAME = "client";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_EMAIL = "email";
    SQLiteDatabase db;


    // Create table command
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + "NOT NULL PRIMARY KEY AUTO_INCREMENT " +
            COLUMN_UNAME + " NOT NULL " +
            COLUMN_PASS + " NOT NULL " +
            COLUMN_EMAIL + " NOT NULL);" ;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(drop);
        this.onCreate(db);
    }
}
