package com.example.intents;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    public static final String RECEIVE_KEY = "007";
    public static final int REQUEST_CODE_THREE = 3;

    Button firstActivityButton;
    Button returnActivityButton;

    Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        firstActivityButton = (Button) findViewById(R.id.firstActivityButton);
        returnActivityButton = (Button) findViewById(R.id.returnButtonFromSecondActivity);

        myIntent = getIntent();
        if(myIntent.hasExtra(MainActivity.SEND_KEY)) {
            Bundle extras = myIntent.getExtras();

            if (extras.getString(MainActivity.SEND_KEY) != null) {

                String message = extras.getString(MainActivity.SEND_KEY);

                Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
            }
        }


        firstActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();

                i.putExtra(RECEIVE_KEY,"YOLO"+"Came from Second Activity");
                setResult(RESULT_OK,i);

                finish();
            }
        });

        returnActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ReturnActivity.class);

                i.putExtra(MainActivity.SEND_KEY,"Came from Second Activity");
                //i.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);// cannot be used when activity also requests for result
                startActivityForResult(i, REQUEST_CODE_THREE);
                finish();


            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Intent i = data;

        setResult(RESULT_OK, i);
        finish();
    }
}
