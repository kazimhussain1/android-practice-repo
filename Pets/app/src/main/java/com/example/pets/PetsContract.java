package com.example.pets;

import android.provider.BaseColumns;

public final class PetsContract {

    private PetsContract(){}

    public static class PetsEntries implements BaseColumns {

        public static final String TABLE_NAME = "pets";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BREED = "breed";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_WEIGHT = "weight";

        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
        public static final int GENDER_UNKONWN = 0;


        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_BREED + " TEXT, " +
                COLUMN_GENDER + " INTEGER NOT NULL, " +
                COLUMN_WEIGHT + " INTEGER DEFAULT 0);";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }



}
