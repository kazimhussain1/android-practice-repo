package com.example.dosomething;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class AddressGetter extends AsyncTask<Address,Integer,String> {

    private double latitiude;
    private double longitude;
    private Geocoder myGeocoder;
    private List<Address> myAddresses;
    private String result;
    private TextView myTextView;

    public AddressGetter(double latitiude, double longitude, Geocoder myGeocoder, TextView myTextView) {
        this.latitiude = latitiude;
        this.longitude = longitude;
        this.myGeocoder = myGeocoder;
        this.myTextView = myTextView;
    }




    @Override
    protected void onPostExecute(String s) {

        myTextView.setVisibility(View.VISIBLE);
        myTextView.setText(s);
    }



    @Override
    protected String doInBackground(Address... addresses) {


        try {
            myAddresses = myGeocoder.getFromLocation(latitiude, longitude, 1);
        }catch (IOException e){
            e.printStackTrace();
        }

        if (myAddresses == null)
            return null;

        Address myAddress = myAddresses.get(0);
        String myString = myAddress.getAddressLine(0) + "\n" +
                myAddress.getLocality() + "\n" +
                myAddress.getCountryName();

        result = myString;
        return myString;
    }
}
