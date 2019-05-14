package com.example.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ReturnActivity extends AppCompatActivity {

    public static final String RECEIVE_KEY = "0786";
    public static final int RESULT_RETURN_ACTIVITY = 756096;

    Spinner mySpinner = null;
    Button returnButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);

        Intent myIntent = getIntent();
        if(myIntent.hasExtra(MainActivity.SEND_KEY)) {
            Bundle extras = myIntent.getExtras();

            if (extras.getString(MainActivity.SEND_KEY) != null) {

                String message = extras.getString(MainActivity.SEND_KEY);

                Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
            }
        }

        returnButton = (Button) findViewById(R.id.submitButton);
        mySpinner = (Spinner) findViewById(R.id.spinnerView);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra(RECEIVE_KEY, mySpinner.getSelectedItem().toString());
                setResult(RESULT_OK,i);


                finish();
            }
        });



    }
}
