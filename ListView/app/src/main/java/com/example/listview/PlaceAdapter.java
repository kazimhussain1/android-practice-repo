package com.example.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class PlaceAdapter extends ArrayAdapter <Place> {

    private Context myContext;
    private int myResource;
    private Place[] myPlaces;


    public PlaceAdapter(Context context, int resource, Place[] objects) {
        super(context, resource, objects);

        this.myContext = context;
        this.myResource = resource;
        this.myPlaces = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        LayoutInflater myInflater = LayoutInflater.from(myContext);
        row = myInflater.inflate(myResource,parent,false);// check what happen if true// it crashes the app

        TextView myTitle = (TextView) row.findViewById(R.id.titleText);
        TextView myDescription = (TextView) row.findViewById(R.id.descriptionText);
        ImageView myImageView = (ImageView)row.findViewById(R.id.logo);


        myTitle.setText(myPlaces[position].mNameOfPlace);
        myDescription.setText(String.valueOf(myPlaces[position].mZipCode));

        try {
            Class res = R.drawable.class;
            Field field = res.getField(myPlaces[position].mNameOfImage);
            int drawableId = field.getInt(null);
            myImageView.setImageResource(drawableId);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }

        return row;
    }
}
