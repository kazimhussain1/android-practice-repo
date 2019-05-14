package com.example.emojifyme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 999;
    private static final int REQUEST_LOAD_IMG = 777;
    private String myCurrentPhotoPath;
    private ImageView myImageView;
    private Button myCameraButton;
    private Button myGalleryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myCameraButton = findViewById(R.id.emojify_button1);
        myGalleryButton = findViewById(R.id.emojify_button2);

        myImageView = findViewById(R.id.my_image_view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE){

            Bitmap myBitmap = BitmapUtils.resamplePic(this,myCurrentPhotoPath);

            myImageView.setImageBitmap(myBitmap);


            myImageView.setVisibility(View.VISIBLE);
            myCameraButton.setVisibility(View.GONE);
            myGalleryButton.setVisibility(View.GONE);

        }else if (requestCode == REQUEST_LOAD_IMG){

            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    myImageView.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                }

            }else {
                Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
            }

            myImageView.setVisibility(View.VISIBLE);

            myCameraButton.setVisibility(View.GONE);
            myGalleryButton.setVisibility(View.GONE);

        }



    }

    @Override
    public void onBackPressed() {

        if (myImageView.getVisibility() == View.VISIBLE){

            myImageView.setImageResource(0);
            myImageView.setVisibility(View.GONE);

            myCameraButton.setVisibility(View.VISIBLE);
            myGalleryButton.setVisibility(View.VISIBLE);

        }else {
            super.onBackPressed();
        }
    }

    public void launchCamera(View view) {

        // Create the capture image intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File myFile = null;
            try {
                myFile = createImageFile();
            } catch (IOException e) {

                e.printStackTrace();
            }

            if (myFile != null) {

                Uri myUri = FileProvider.getUriForFile(this,
                        "com.example.fileprovider",
                        myFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, myUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }


        }
    }

    public void openGallery(View view) {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_LOAD_IMG);

    }


    private File createImageFile() throws IOException {

        String fileName =  (new SimpleDateFormat("ddMMyyyy_hh:mm:ss")).format(new Date());
        fileName += "_EmojifyME";

        File storageDir = Environment.getExternalStoragePublicDirectory("EmojifyMe");
        File imageFile = File.createTempFile(
                fileName,
                ".jpg",
                storageDir

        );
        myCurrentPhotoPath = imageFile.getAbsolutePath();
        return imageFile;
    }



}

