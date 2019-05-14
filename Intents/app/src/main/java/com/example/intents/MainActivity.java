package com.example.intents;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final  int REQUEST_CODE_ONE = 1;
    public static final  int REQUEST_CODE_TWO = 2;
    public static final String SEND_KEY = "090078601";

    TextView myTextView;
    Button secondActivityButton;
    Button returnActivityButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = (TextView) findViewById(R.id.resultText);
        secondActivityButton = (Button) findViewById(R.id.secondButton);
        returnActivityButton = (Button) findViewById(R.id.returnButton);

        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), SecondActivity.class);

                myIntent.putExtra(SEND_KEY, "Came from First Activity");

                startActivityForResult(myIntent, REQUEST_CODE_ONE);
            }
        });

        returnActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ReturnActivity.class);

                myIntent.putExtra(SEND_KEY, "Came from First Activity");

                startActivityForResult(myIntent, REQUEST_CODE_TWO);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK){
            if(data.hasExtra(SecondActivity.RECEIVE_KEY)) {

                myTextView.setText(data.getStringExtra(SecondActivity.RECEIVE_KEY));
                myTextView.setBackgroundColor(getResources().getColor(R.color.colorSecond));

            }
        }else if(requestCode == 2 && resultCode == RESULT_OK) {
            if (data.hasExtra(ReturnActivity.RECEIVE_KEY)) {

                myTextView.setText(data.getStringExtra(ReturnActivity.RECEIVE_KEY));
                myTextView.setBackgroundColor(getResources().getColor(R.color.colorReturn));
            }

        } else if (requestCode == SecondActivity.REQUEST_CODE_THREE && resultCode == RESULT_OK) {
            if (data.hasExtra(ReturnActivity.RECEIVE_KEY)) {

                myTextView.setText(data.getStringExtra(ReturnActivity.RECEIVE_KEY));
                myTextView.setBackgroundColor(getResources().getColor(R.color.black));

            }
        }
    }
}
