package com.example.androidmev20;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myFragment = inflater.inflate(R.layout.fragment_layout_2,container,false);

        final TextView myTextView = myFragment.findViewById(R.id.textView2);

        myFragment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE){
                    myTextView.setText("X: "+String.valueOf(event.getX())+" Y: "+String.valueOf(event.getY()));
                    Log.d("Touch:","I'm here");
                    return true;
                }
                else if(event.getAction() == MotionEvent.ACTION_DOWN){
                    return true;
                }

                return false;
            }
        });

        return  myFragment;
    }
}
