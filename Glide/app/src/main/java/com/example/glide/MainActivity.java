package com.example.glide;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_PERMISSION_INTERNET = 101;
    TimerTask myTask;
    Timer myTimer;
    private ImageView myImageView;
    ArrayList<String> myArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImageView = findViewById(R.id.activity_main_imageView);

        myArrayList = new ArrayList(){{

                add("https://pixelz.cc/wp-content/uploads/2019/03/audi-e-tron-quattro-wqhd-1440p-wallpaper.jpg");
                add("https://pixelz.cc/wp-content/uploads/2019/03/audi-e-tron-wqhd-1440p-wallpaper.jpg");
                add("https://pixelz.cc/wp-content/uploads/2019/02/audi-tt-rs-coupe-uhd-4k-wallpaper.jpg");
                add("https://pixelz.cc/wp-content/uploads/2019/03/audi-r18-e-tron-fornt-wqhd-1440p-wallpaper.jpg");
                add("https://pixelz.cc/wp-content/uploads/2019/01/audi-formula-e-uhd-4k-wallpaper.jpg");
                add("https://pixelz.cc/wp-content/uploads/2018/12/audi-r8-white-front-uhd-4k-wallpaper.jpg");
                add("https://pixelz.cc/wp-content/uploads/2018/10/audi-r8-v10-abt-uhd-4k-wallpaper.jpg");


            }
        };



        if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){

            if(shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)){
                Toast.makeText(this,"Internet permission required to get images", Toast.LENGTH_LONG).show();
            }

            requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE }, REQUEST_PERMISSION_INTERNET);

        }

        if (checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {

            int i = 700;

            int index = (int)( Math.random() * myArrayList.size());


            Glide.with(getBaseContext())
                    .load(myArrayList.get(index))
                    .override(i, i)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.mipmap.ic_launcher_round)
                    .into(myImageView);


        } else {
            requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE }, REQUEST_PERMISSION_INTERNET);
        }

    }
}
