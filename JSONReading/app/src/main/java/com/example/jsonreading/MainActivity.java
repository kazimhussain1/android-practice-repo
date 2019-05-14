package com.example.jsonreading;

import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String[] myPosts = null;
    RecyclerView myListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Retrofit myRetrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonPlaceholderApi myAPI = myRetrofit.create(JsonPlaceholderApi.class);

        Call<List<Post>> call = myAPI.getPosts(1,2,"id", "desc");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {

                    Toast.makeText(getBaseContext(),"Error:" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                List<Post> posts = response.body();

                myPosts = new String[posts.size()];

                int i =0;
                for(Post p: posts){

                    String temp = "ID: " + p.getId() + "\n" +
                            "User ID: " + p.getUserId() + "\n" +
                            "Title: " + p.getTitle() + "\n" +
                            "Body: " + p.getText();

                    myPosts[i] = temp;
                    i++;
                }

                RecyclerAdapter myArrayAdapter = new RecyclerAdapter(myPosts);

                StaggeredGridLayoutManager myGrid = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);


                myListView.setLayoutManager(myGrid);
                myListView.setAdapter(myArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {


                Toast.makeText(getBaseContext(),"Failed", Toast.LENGTH_LONG).show();
            }
        });

        myListView = findViewById(R.id.recyclerView);

//        String s =  readJSONFile("sampleJSON");
//
//        try {
//            if (s != null) {
//                JSONObject myJSONObject = new JSONObject(s);
//
//                JSONArray myJSONArray = myJSONObject.getJSONArray("topping");
//                myToppings = new String[myJSONArray.length()];
//
//                for (int i = 0; i < myJSONArray.length(); i++) {
//
//                    myToppings[i] = myJSONArray.getJSONObject(i).getString("type");
//
//                }
//
//            }else{
//                Log.e("onCreate","String s is empty");
//            }
//
//        }catch (JSONException e){
//            Log.d("onCreate", "JSON Object could not be made");
//            Log.d("onCreate", e.getStackTrace().toString());
//        }
//
//        if (myToppings!= null) {
//    }




    }




    public String readJSONFile(String fileName) {

        InputStream myStream = null;

        String content = null;
        try {
            myStream = getResources().openRawResource(R.raw.samplejson);

            int size = myStream.available();
            byte[] buffer = new byte[size];

            myStream.read(buffer);
            myStream.close();

            content = new String(buffer, "UTF-8");

        } catch (FileNotFoundException e) {
            Log.d("readTextFile", "File not found");
            Log.d("readTextFile", e.getStackTrace().toString());
        } catch (IOException e) {
            Log.d("readTextFile", "File cannot be read or written to");
            Log.d("readTextFile", e.getStackTrace().toString());
        } finally {
            try {
                if (myStream != null) {
                    myStream.close();
                }
            } catch (IOException e) {
                Log.d("readTextFile", "cannot close file");
                Log.d("readTextFile", e.getStackTrace().toString());
            }

        }
        return content;
    }
}
