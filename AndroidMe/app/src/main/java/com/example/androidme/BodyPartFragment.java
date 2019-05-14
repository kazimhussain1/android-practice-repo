package com.example.androidme;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    private int myImageIndex;
    private List<Integer> myImageIds;
    public static final String IMAGE_ID_LIST = "090078601";
    public static final String IMAGE_INDEX = "YOLO";

    public BodyPartFragment() {


    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null){

            myImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            myImageIndex = savedInstanceState.getInt(IMAGE_INDEX);
        }

        View rootView = (ImageView) inflater.inflate(R.layout.fragment_body_parts,container, false);

        final ImageView myImageView = (ImageView) rootView.findViewById(R.id.bodyPartImageView);
        if (myImageIds != null) {
            myImageView.setImageResource(myImageIds.get(myImageIndex));
        }else {
            Log.e("BodyPartFragment:","Image Id's not set");
        }

        myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myImageIndex = (myImageIndex + 1) % myImageIds.size();

                myImageView.setImageResource(myImageIds.get(myImageIndex));
            }
        });

        return rootView;


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(IMAGE_INDEX, myImageIndex);
        outState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) myImageIds);
    }

    public void setMyImageIndex(int myImageIndex) {
        this.myImageIndex = myImageIndex;
    }

    public void setMyImageIds(List<Integer> myImageIds) {
        this.myImageIds = myImageIds;
    }
}
