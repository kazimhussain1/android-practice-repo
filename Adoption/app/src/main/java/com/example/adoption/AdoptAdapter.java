package com.example.adoption;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class AdoptAdapter<Type extends Animals & Adoptable> {

    private Activity activity;
    private TextView name;
    private TextView description;
    private RatingBar ratingBar;
    private ImageView imageView;

    private Type t;

    public AdoptAdapter(Activity aActivity, TextView aName, TextView aDescription, RatingBar aBar, ImageView aImageView){
        this.activity = aActivity;
        this.name = aName;
        this.description = aDescription;
        this.ratingBar = aBar;
        this.imageView = aImageView;
    }

    public void set(Type t) {
        this.t = t;
        updateView();
    }

    public Type get() {
        return t;
    }

    private void updateView(){
        int resID = t.getImageResourceId(this.activity);
        imageView.setImageResource(resID);
        name.setText(t.getName());
        description.setText(t.getOwnerDescription());
        ratingBar.setNumStars(5);
        ratingBar.setRating(t.getRating());
    }

}
