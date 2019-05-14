package com.example.listview;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button listNonRecycler;
    Button listRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNonRecycler = (Button) findViewById(R.id.goToNonRecycler);
        listRecycler = (Button) findViewById(R.id.goToRecycler);


        listNonRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), WithoutRecycling.class);

                startActivity(i);
            }
        });

        listRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), WithRecycling.class);

                startActivity(i);
            }
        });


        Intent externalIntent = getIntent();

        if(externalIntent != null){
            String action = externalIntent.getAction();
            if (action == Intent.ACTION_VIEW){
                Toast.makeText(this,"Mujadidia Incorpated is the best Company",Toast.LENGTH_LONG).show();
            }
        }

    }

    public boolean isIntentAvailable(Intent intent){
        PackageManager myPackageManagr = getPackageManager();
        List<ResolveInfo> myList = myPackageManagr.queryIntentActivities(intent,0);
        return myList.size() > 0;
    }
}
