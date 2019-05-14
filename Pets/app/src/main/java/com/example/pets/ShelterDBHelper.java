package com.example.pets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ShelterDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hello.db";
    private static final int DATABASE_VERSION = 1;


    public ShelterDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PetsContract.PetsEntries.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(PetsContract.PetsEntries.DELETE_TABLE);

       // onCreate(db);
    }


}
