package com.example.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class ImprovedPlaceAdapter extends ArrayAdapter <Place> {

    private Context myContext;
    private int myResource;
    private Place[] myPlaces;


    public ImprovedPlaceAdapter(Context context, int resource, Place[] objects) {
        super(context, resource, objects);

        this.myContext = context;
        this.myResource = resource;
        this.myPlaces = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        PlaceHolder holder = null;

        if(row == null){

            LayoutInflater myInflater = LayoutInflater.from(myContext);
            row = myInflater.inflate(myResource,parent,false);// check what happen if true// it crashes the app

            holder = new PlaceHolder();

            holder.myTitle = (TextView) row.findViewById(R.id.titleText);
            holder.myDescription = (TextView) row.findViewById(R.id.descriptionText);
            holder.myImageView = (ImageView)row.findViewById(R.id.logo);

            row.setTag(holder);
        }
        else {
            holder = (PlaceHolder) row.getTag();
        }

        holder.myImageView.setTag(new Integer(position));



        holder.myTitle.setText(myPlaces[position].mNameOfPlace);
        holder.myDescription.setText(String.valueOf(myPlaces[position].mZipCode));
        holder.myImageView.setOnClickListener(myListener);

        try {
            Class res = R.drawable.class;
            Field field = res.getField(myPlaces[position].mNameOfImage);
            int drawableId = field.getInt(null);
            holder.myImageView.setImageResource(drawableId);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }

        return row;
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Integer position = (Integer) v.getTag();
            Toast.makeText(getContext(), myPlaces[position].mNameOfImage,Toast.LENGTH_SHORT).show();
        }
    };

    static class PlaceHolder{
        public TextView myTitle;
        public TextView myDescription;
        public ImageView myImageView;
    }
}
