package com.example.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WithRecycling extends AppCompatActivity {

    ListView myListView;
    TextView myTextView;

    Intent implicitIntent = null;

    private Place[] myPlacesArray = new Place[]{
            new Place("Art House", 78701 , "art" ,"This place is tasteful"),
            new Place("Bike Shop", 78702, "bike","Cool bikes"),
            new Place("Camera Fix", 78702, "polaroids","These guys always rip me off"),
            new Place("YETspace", 78702, "radio", "I LOVE this place"),
            new Place("Secret Space Pad", 94103, "rocket","Not very secret, are they?"),
            new Place("Taylor’s Tailor", 60610, "scissors" , "Looking good.."),
            new Place("Boathouse", 78701, "shipwheel" ,"That place is full of pirates!"),
            new Place("Not Apple Store", 78702, "tablet", "Android rules!"),
            new Place("Tool Battleground", 78702, "tools", "That place is dangerous"),
            new Place("Travelpediocity", 78702, "travelerbag" ,"This is where i booked my summer trip"),
            new Place("UFO Pick-a-part", 90210, "ufo","Out of this world stuff here."),
            new Place("Spawrk’s House", 99999, "volume", "The music is always so good"),
            new Place("Art House", 78701 , "art" ,"This place is tasteful"),
            new Place("Bike Shop", 78702, "bike","Cool bikes"),
            new Place("Camera Fix", 78702, "polaroids","These guys always rip me off"),
            new Place("YETspace", 78702, "radio", "I LOVE this place"),
            new Place("Secret Space Pad", 94103, "rocket","Not very secret, are they?"),
            new Place("Taylor’s Tailor", 60610, "scissors" , "Looking good.."),
            new Place("Boathouse", 78701, "shipwheel" ,"That place is full of pirates!"),
            new Place("Not Apple Store", 78702, "tablet", "Android rules!"),
            new Place("Tool Battleground", 78702, "tools", "That place is dangerous"),
            new Place("Travelpediocity", 78702, "travelerbag" ,"This is where i booked my summer trip"),
            new Place("UFO Pick-a-part", 90210, "ufo","Out of this world stuff here."),
            new Place("Spawrk’s House", 99999, "volume", "The music is always so good"),
            new Place("Art House", 78701 , "art" ,"This place is tasteful"),
            new Place("Bike Shop", 78702, "bike","Cool bikes"),
            new Place("Camera Fix", 78702, "polaroids","These guys always rip me off"),
            new Place("YETspace", 78702, "radio", "I LOVE this place"),
            new Place("Secret Space Pad", 94103, "rocket","Not very secret, are they?"),
            new Place("Taylor’s Tailor", 60610, "scissors" , "Looking good.."),
            new Place("Boathouse", 78701, "shipwheel" ,"That place is full of pirates!"),
            new Place("Not Apple Store", 78702, "tablet", "Android rules!"),
            new Place("Tool Battleground", 78702, "tools", "That place is dangerous"),
            new Place("Travelpediocity", 78702, "travelerbag" ,"This is where i booked my summer trip"),
            new Place("UFO Pick-a-part", 90210, "ufo","Out of this world stuff here."),
            new Place("Spawrk’s House", 99999, "volume", "The music is always so good"),
            new Place("Art House", 78701 , "art" ,"This place is tasteful"),
            new Place("Bike Shop", 78702, "bike","Cool bikes"),
            new Place("Camera Fix", 78702, "polaroids","These guys always rip me off"),
            new Place("YETspace", 78702, "radio", "I LOVE this place"),
            new Place("Secret Space Pad", 94103, "rocket","Not very secret, are they?"),
            new Place("Taylor’s Tailor", 60610, "scissors" , "Looking good.."),
            new Place("Boathouse", 78701, "shipwheel" ,"That place is full of pirates!"),
            new Place("Not Apple Store", 78702, "tablet", "Android rules!"),
            new Place("Tool Battleground", 78702, "tools", "That place is dangerous"),
            new Place("Travelpediocity", 78702, "travelerbag" ,"This is where i booked my summer trip"),
            new Place("UFO Pick-a-part", 90210, "ufo","Out of this world stuff here."),
            new Place("Spawrk’s House", 99999, "volume", "The music is always so good"),
            new Place("Art House", 78701 , "art" ,"This place is tasteful"),
            new Place("Bike Shop", 78702, "bike","Cool bikes"),
            new Place("Camera Fix", 78702, "polaroids","These guys always rip me off"),
            new Place("YETspace", 78702, "radio", "I LOVE this place"),
            new Place("Secret Space Pad", 94103, "rocket","Not very secret, are they?"),
            new Place("Taylor’s Tailor", 60610, "scissors" , "Looking good.."),
            new Place("Boathouse", 78701, "shipwheel" ,"That place is full of pirates!"),
            new Place("Not Apple Store", 78702, "tablet", "Android rules!"),
            new Place("Tool Battleground", 78702, "tools", "That place is dangerous"),
            new Place("Travelpediocity", 78702, "travelerbag" ,"This is where i booked my summer trip"),
            new Place("UFO Pick-a-part", 90210, "ufo","Out of this world stuff here."),
            new Place("Spawrk’s House", 99999, "volume", "The music is always so good"),
            new Place("Art House", 78701 , "art" ,"This place is tasteful"),
            new Place("Bike Shop", 78702, "bike","Cool bikes"),
            new Place("Camera Fix", 78702, "polaroids","These guys always rip me off"),
            new Place("YETspace", 78702, "radio", "I LOVE this place"),
            new Place("Secret Space Pad", 94103, "rocket","Not very secret, are they?"),
            new Place("Taylor’s Tailor", 60610, "scissors" , "Looking good.."),
            new Place("Boathouse", 78701, "shipwheel" ,"That place is full of pirates!"),
            new Place("Not Apple Store", 78702, "tablet", "Android rules!"),
            new Place("Tool Battleground", 78702, "tools", "That place is dangerous"),
            new Place("Travelpediocity", 78702, "travelerbag" ,"This is where i booked my summer trip"),
            new Place("UFO Pick-a-part", 90210, "ufo","Out of this world stuff here."),
            new Place("Spawrk’s House", 99999, "volume", "The music is always so good"),
            new Place("Art House", 78701 , "art" ,"This place is tasteful"),
            new Place("Bike Shop", 78702, "bike","Cool bikes"),
            new Place("Camera Fix", 78702, "polaroids","These guys always rip me off"),
            new Place("YETspace", 78702, "radio", "I LOVE this place"),
            new Place("Secret Space Pad", 94103, "rocket","Not very secret, are they?"),
            new Place("Taylor’s Tailor", 60610, "scissors" , "Looking good.."),
            new Place("Boathouse", 78701, "shipwheel" ,"That place is full of pirates!"),
            new Place("Not Apple Store", 78702, "tablet", "Android rules!"),
            new Place("Tool Battleground", 78702, "tools", "That place is dangerous"),
            new Place("Travelpediocity", 78702, "travelerbag" ,"This is where i booked my summer trip"),
            new Place("UFO Pick-a-part", 90210, "ufo","Out of this world stuff here."),
            new Place("Spawrk’s House", 99999, "volume", "The music is always so good"),
            new Place("Art House", 78701 , "art" ,"This place is tasteful"),
            new Place("Bike Shop", 78702, "bike","Cool bikes"),
            new Place("Camera Fix", 78702, "polaroids","These guys always rip me off"),
            new Place("YETspace", 78702, "radio", "I LOVE this place"),
            new Place("Secret Space Pad", 94103, "rocket","Not very secret, are they?"),
            new Place("Taylor’s Tailor", 60610, "scissors" , "Looking good.."),
            new Place("Boathouse", 78701, "shipwheel" ,"That place is full of pirates!"),
            new Place("Not Apple Store", 78702, "tablet", "Android rules!"),
            new Place("Tool Battleground", 78702, "tools", "That place is dangerous"),
            new Place("Travelpediocity", 78702, "travelerbag" ,"This is where i booked my summer trip"),
            new Place("UFO Pick-a-part", 90210, "ufo","Out of this world stuff here."),
            new Place("Spawrk’s House", 99999, "volume", "The music is always so good"),
            new Place("Art House", 78701 , "art" ,"This place is tasteful"),
            new Place("Bike Shop", 78702, "bike","Cool bikes"),
            new Place("Camera Fix", 78702, "polaroids","These guys always rip me off"),
            new Place("YETspace", 78702, "radio", "I LOVE this place"),
            new Place("Secret Space Pad", 94103, "rocket","Not very secret, are they?"),
            new Place("Taylor’s Tailor", 60610, "scissors" , "Looking good.."),
            new Place("Boathouse", 78701, "shipwheel" ,"That place is full of pirates!"),
            new Place("Not Apple Store", 78702, "tablet", "Android rules!"),
            new Place("Tool Battleground", 78702, "tools", "That place is dangerous"),
            new Place("Travelpediocity", 78702, "travelerbag" ,"This is where i booked my summer trip"),
            new Place("UFO Pick-a-part", 90210, "ufo","Out of this world stuff here."),
            new Place("Spawrk’s House", 99999, "volume", "The music is always so good"),
            new Place("Art House", 78701 , "art" ,"This place is tasteful"),
            new Place("Bike Shop", 78702, "bike","Cool bikes"),
            new Place("Camera Fix", 78702, "polaroids","These guys always rip me off"),
            new Place("YETspace", 78702, "radio", "I LOVE this place"),
            new Place("Secret Space Pad", 94103, "rocket","Not very secret, are they?"),
            new Place("Taylor’s Tailor", 60610, "scissors" , "Looking good.."),
            new Place("Boathouse", 78701, "shipwheel" ,"That place is full of pirates!"),
            new Place("Not Apple Store", 78702, "tablet", "Android rules!"),
            new Place("Tool Battleground", 78702, "tools", "That place is dangerous"),
            new Place("Travelpediocity", 78702, "travelerbag" ,"This is where i booked my summer trip"),
            new Place("UFO Pick-a-part", 90210, "ufo","Out of this world stuff here."),
            new Place("Spawrk’s House", 99999, "volume", "The music is always so good"),
            new Place("Art House", 78701 , "art" ,"This place is tasteful"),
            new Place("Bike Shop", 78702, "bike","Cool bikes"),
            new Place("Camera Fix", 78702, "polaroids","These guys always rip me off"),
            new Place("YETspace", 78702, "radio", "I LOVE this place"),
            new Place("Secret Space Pad", 94103, "rocket","Not very secret, are they?"),
            new Place("Taylor’s Tailor", 60610, "scissors" , "Looking good.."),
            new Place("Boathouse", 78701, "shipwheel" ,"That place is full of pirates!"),
            new Place("Not Apple Store", 78702, "tablet", "Android rules!"),
            new Place("Tool Battleground", 78702, "tools", "That place is dangerous"),
            new Place("Travelpediocity", 78702, "travelerbag" ,"This is where i booked my summer trip"),
            new Place("UFO Pick-a-part", 90210, "ufo","Out of this world stuff here."),
            new Place("Spawrk’s House", 99999, "volume", "The music is always so good"),
    };


    private String[] myStringArray = new String[]{
            "Call Phone",
            "Text Someone",
            "Open Browser",
            "Open Camera",
            "Open Maps"
    };

    private ArrayAdapter<String> myArrayAdapter;

    private ImprovedPlaceAdapter myPlaceAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_recycling);




        myTextView = (TextView) findViewById(R.id.myTextView);
        myListView = (ListView) findViewById(R.id.myListView);

        myPlaceAdapter = new ImprovedPlaceAdapter(getApplicationContext(),R.layout.row, myPlacesArray);

        if (myListView != null) {
            myListView.setAdapter(myPlaceAdapter);
        }

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),myPlacesArray[position].mPopup,Toast.LENGTH_LONG).show();

                return false;
            }
        });
    }
}
