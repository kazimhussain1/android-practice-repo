package com.example.androidmev20;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Fragment3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View myFragment =  inflater.inflate(R.layout.fragment_layout_3,container,false);

        myFragment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {

                    Toast.makeText(getActivity(),"Finger Up", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Toast.makeText(getActivity(),"Finger Down", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });

        return myFragment;
    }


}
