package com.example.fragmentlifcycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by learnovate on 3/27/14.
 */
public class MainFragment extends Fragment {

    String status = "";
    int order = 0;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        status = "Fragment Attached";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status = "Fragment Created";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        status = "Fragment Create View";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        status = "Fragment View Created";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        status = "Fragment Activity Created";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        status = "Fragment Started";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        status = "Fragment State Restored";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        status = "Fragment Resumed";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPause() {
        super.onPause();
        status = "Fragment Paused";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        status = "Fragment Saving State";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        status = "Fragment Stopped";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        status = "Fragment View Destroyed";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        status = "Fragment Destroyed";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        status = "Fragment Detached";
        Toast.makeText(getActivity().getApplicationContext(), status, Toast.LENGTH_SHORT).show();
    }

    public void displayStatus(){
        order++;
        String message = String.valueOf(order) + ": " + status;
        View v = getView();
        if(v != null){
            message = message + " [+View]";
        }
        //t.setText(status);
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }



}