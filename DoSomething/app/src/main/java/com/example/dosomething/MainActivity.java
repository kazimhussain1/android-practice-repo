package com.example.dosomething;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CAMERA = 101;
    public static final int REQUEST_PHONE = 202;
    public static final int REQUEST_SMS = 303;
    public static final int REQUEST_LAST_LOCATION = 404;
    public static final int REQUEST_LOCATION_UPDATES = 505;

    public static final int REQUEST_CHECK_SETTINGS = 1010;


    Spinner mySpinner;
    Button myButton;
    TextView myTextView;
    private FusedLocationProviderClient myFusedLocationClient;
    private LocationSettingsRequest.Builder myBuilder;
    private LocationCallback myCallback;
    private LocationCallback startupCallBack;
    AddressGetter myAddressGetter;
    LocationRequest myLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySpinner = findViewById(R.id.activity_main_spinner);
        myButton = findViewById(R.id.activity_main_launchActivity);
        myTextView = findViewById(R.id.activity_main_result);

        myFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        myBuilder = new LocationSettingsRequest.Builder();

        myLocationRequest = new LocationRequest();
        myLocationRequest.setInterval(2000);
        myLocationRequest.setFastestInterval(1000);
        myLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        myBuilder.addLocationRequest(myLocationRequest);

        myCallback = new LocationCallback(){

            @Override
            public void onLocationResult(LocationResult locationResult) {

                if (locationResult != null){

                    myTextView.setVisibility(View.VISIBLE);

                    for (Location location:locationResult.getLocations()){
                        myTextView.append( "X: " + String.valueOf(location.getLatitude()) +" Y:" + String.valueOf(location.getLongitude()) + "\n");
                    }
                }
            }
        };

        startupCallBack = new LocationCallback(){

            @Override
            public void onLocationResult(LocationResult locationResult) {

                myFusedLocationClient.removeLocationUpdates(this);
            }
        };







        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selection = mySpinner.getSelectedItem().toString();

                myFusedLocationClient.removeLocationUpdates(myCallback);

                Intent myIntent;
                if (selection == null)
                    return;

                switch (selection) {
                    case "Call Phone":

                        myIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:090078601"));

                        launchExternalActivity(Manifest.permission.CALL_PHONE, REQUEST_PHONE, myIntent);
                        break;

                    case "Send a Text":

                        myIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:03112246903"));
                        myIntent.putExtra("sms_body", "hello mate!!");

                        launchExternalActivity(Manifest.permission.SEND_SMS, REQUEST_SMS, myIntent);
                        break;
                    case "Open Camera":

                        myIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        launchExternalActivity(Manifest.permission.CAMERA, REQUEST_CAMERA, myIntent);
                        break;
                    case "Get Approx. Location":

                        performLocationUpdatesRequest(startupCallBack);
                        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {

                            }

                            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LAST_LOCATION);

                        } else {

                            try {
                                myFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {


                                        if (location != null) {

                                            myTextView.setText("X: " + String.valueOf(location.getLatitude()) + " Y:" + String.valueOf(location.getLongitude()));
                                            myTextView.setVisibility(View.VISIBLE);

                                        } else {
                                            Toast.makeText(getBaseContext(), "Could not get Location for some reason", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                        break;
                    case "Get Exact Locaton":

                        break;

                    case "Get Location Updates":

                        performLocationUpdatesRequest(myCallback);
                        break;

                    case "Get Full Address":

                        performLocationUpdatesRequest(startupCallBack);
                        Task<Location> myLocation = myFusedLocationClient.getLastLocation();

                        myLocation.addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    Geocoder myGeo = new Geocoder(getBaseContext(), Locale.getDefault());
                                    myAddressGetter = new AddressGetter(location.getLatitude(), location.getLongitude(), myGeo, myTextView);
                                    myAddressGetter.execute();
                                }else {
                                    Log.e("location" ,"notfound");
                                }
                            }
                        });

                        break;

                    default:
                        Toast.makeText(getBaseContext(), "Please Make a Selection", Toast.LENGTH_SHORT).show();
                    }
                }

        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Intent myIntent;

        if (grantResults.length > 0){
            switch (requestCode) {

                case REQUEST_PHONE:
                    myIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:090078601"));
                    launchExternalActivity(Manifest.permission.CALL_PHONE,REQUEST_PHONE,myIntent);
                    break;
                case REQUEST_SMS:
                    myIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:03112246903"));
                    myIntent.putExtra("sms_body", "hello mate!!");

                    launchExternalActivity(Manifest.permission.SEND_SMS, REQUEST_SMS, myIntent);
                    break;
                case REQUEST_CAMERA:
                    myIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    launchExternalActivity(Manifest.permission.CAMERA, REQUEST_CAMERA, myIntent);
                    break;
                case REQUEST_LAST_LOCATION:
                    try{
                        myFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null){

                                    myTextView.setText( "X: " + String.valueOf(location.getLatitude()) +" Y:" + String.valueOf(location.getLongitude()));
                                    myTextView.setVisibility(View.VISIBLE);

                                }else {
                                    Toast.makeText(getBaseContext(),"Could not get Location for some reason",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }catch (SecurityException e){
                        e.printStackTrace();
                    }
                    break;

                case REQUEST_LOCATION_UPDATES:

                    performLocationUpdatesRequest(myCallback);
                    break;

            }

        }

    }

    public void launchExternalActivity(String permission, int requestCode, Intent implicitIntent) {

        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                Toast.makeText(getBaseContext(), "You need this permission(" + Manifest.permission.CALL_PHONE + ") to carry out the requested activity", Toast.LENGTH_LONG).show();
            }
            requestPermissions(new String[]{permission}, requestCode);

        } else {


            try {
                startActivity(implicitIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public void performLocationUpdatesRequest(final LocationCallback callback){

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(getBaseContext(), "Location permission is needed for current task.", Toast.LENGTH_LONG).show();
            }
                try {
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_UPDATES);



        }else {

            SettingsClient client = LocationServices.getSettingsClient(this);
            Task<LocationSettingsResponse> task = client.checkLocationSettings(myBuilder.build());

            task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                @Override
                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                    // All location settings are satisfied. The client can initialize
                    // location requests here.
                    // ...
                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        myFusedLocationClient.requestLocationUpdates(myLocationRequest, callback, null);
                    }
                }
            });

            task.addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ResolvableApiException) {
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(MainActivity.this,
                                    REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException sendEx) {
                            // Ignore the error.
                        }
                        finally {
                            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                                myFusedLocationClient.requestLocationUpdates(myLocationRequest, callback, null);
                            }
                        }
                    }
                }
            });


        }



    }

}







