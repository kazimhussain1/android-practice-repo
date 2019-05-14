package com.example.adoption;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected AdoptData myAdoptData = new AdoptData();
    protected static final String BUNNY_INFO = "Bunny Data";
    protected static final String CAT_INFO = "Cat Data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.goSettings:
                return true;
            case R.id.rateUp:
                return true;
            case R.id.closeApp:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void viewCats(View view){
        Bundle myBundle = new Bundle();
        Intent myIntent = new Intent(this, CatView.class);
        myBundle.putSerializable(CAT_INFO, (ArrayList)myAdoptData.myCatList);
        myIntent.putExtras(myBundle);
        startActivity(myIntent);
    }

    public void viewBunnies(View view){
        Bundle myBundle = new Bundle();
        Intent myIntent = new Intent(this, BunnyView.class);
        myBundle.putSerializable(BUNNY_INFO, (ArrayList)myAdoptData.myBunnyList);
        myIntent.putExtras(myBundle);
        startActivity(myIntent);
    }
    public void openConnectIt(View view){
        Intent myIntent = new Intent(this, LinearLayoutConnectIt.class);
        startActivity(myIntent);
    }
}
