/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pets.PetsContract.PetsEntries;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

     public ShelterDBHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);



        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new ShelterDBHelper(getApplicationContext());
        SQLiteDatabase myDB = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PetsEntries._ID,1);
        values.put(PetsEntries.COLUMN_NAME, "Garfeild");
        values.put(PetsEntries.COLUMN_BREED, "Tabby");
        values.put(PetsEntries.COLUMN_GENDER, PetsEntries.GENDER_MALE);
        values.put(PetsEntries.COLUMN_WEIGHT, 14);

        //long newRowID = myDB.insert(PetsEntries.TABLE_NAME,null, values);
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();

        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet("Toto", "Terrier", PetsEntries.GENDER_MALE, 7);
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:

                deleteAllPets();

                displayDatabaseInfo();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                PetsEntries._ID,
                PetsEntries.COLUMN_NAME,
                PetsEntries.COLUMN_BREED,
                PetsEntries.COLUMN_GENDER,
                PetsEntries.COLUMN_WEIGHT };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                PetsEntries.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(PetsEntries._ID + " - " +
                    PetsEntries.COLUMN_NAME + " - " +
                    PetsEntries.COLUMN_BREED + " - " +
                    PetsEntries.COLUMN_GENDER + " - " +
                    PetsEntries.COLUMN_WEIGHT + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(PetsEntries._ID);
            int nameColumnIndex = cursor.getColumnIndex(PetsEntries.COLUMN_NAME);
            int breedColumnIndex = cursor.getColumnIndex(PetsEntries.COLUMN_BREED);
            int genderColumnIndex = cursor.getColumnIndex(PetsEntries.COLUMN_GENDER);
            int weightColumnIndex = cursor.getColumnIndex(PetsEntries.COLUMN_WEIGHT);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBreed = cursor.getString(breedColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentWeight = cursor.getInt(weightColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentBreed + " - " +
                        currentGender + " - " +
                        currentWeight));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    public void insertPet(String name, String breed, int gender, int weight){

        SQLiteDatabase myDB = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PetsEntries.COLUMN_NAME, name);
        values.put(PetsEntries.COLUMN_BREED, breed);
        values.put(PetsEntries.COLUMN_GENDER, gender);
        values.put(PetsEntries.COLUMN_WEIGHT, weight);

        long newRowId = myDB.insert(PetsEntries.TABLE_NAME,null, values);

        myDB.close();

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
        displayDatabaseInfo();

    }

    private void deleteAllPets(){

        SQLiteDatabase myDB = mDbHelper.getWritableDatabase();




        long myBug = myDB.delete(PetsEntries.TABLE_NAME,null,null);

        String statement = "delete from sqlite_sequence where name= " + "'" + PetsEntries.TABLE_NAME + "';";
        myDB.execSQL(statement);

        if (myBug ==0){
            Toast.makeText(this, "Error: No Rows Deleted", Toast.LENGTH_LONG).show();
        }

        myDB.close();


    }


}
