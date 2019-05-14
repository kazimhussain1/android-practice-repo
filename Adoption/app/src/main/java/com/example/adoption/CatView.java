package com.example.adoption;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CatView extends AppCompatActivity {

    public TextView nameTextView = null;
    public TextView descriptionTextView = null;
    public RatingBar ratingView = null;
    public ImageView portraitView = null;
    public Button nextButton = null;

    private int currentSelection = 0;

    private AdoptAdapter myAdoptAdapter;


    private ArrayList<Cats> myCatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_view);

        Intent myIntent = this.getIntent();
        Bundle myBundle = (Bundle) myIntent.getExtras();

        myCatList = (ArrayList<Cats>) myBundle.getSerializable(MainActivity.CAT_INFO);


        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        ratingView = (RatingBar) findViewById(R.id.ratingView);
        portraitView = (ImageView) findViewById(R.id.portraitView);
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNext();
            }

        });
        myAdoptAdapter = new AdoptAdapter(this,nameTextView,descriptionTextView,ratingView,portraitView);
        myAdoptAdapter.set(AdoptData.myCatList.get(0));
    }

    public void showNext(){

        int random = currentSelection;
        while(random == currentSelection){
            //avoid same selection twice.
            random = (int )(Math.random() * AdoptData.myCatList.size());
        }
        currentSelection = random;
        Cats c = AdoptData.myCatList.get(random);
        myAdoptAdapter.set(c);



    }


}
