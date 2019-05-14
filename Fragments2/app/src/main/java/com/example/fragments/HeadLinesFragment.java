package com.example.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.zip.Inflater;

public class HeadLinesFragment extends ListFragment {

    OnLineSelectedListener myCallback;

    public interface OnLineSelectedListener{
        void onHeadlineSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{

            myCallback = (OnLineSelectedListener) getActivity();

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement OnLineSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,Ipsum.Headlines));
    }

    @Override
    public void onStart() {
        super.onStart();

        Fragment f = getActivity().getSupportFragmentManager().findFragmentById(R.id.articlesFragment);
        View v = getListView();

        if (f != null && v != null){
            ((ListView) v).setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        myCallback.onHeadlineSelected(position);
        l.setItemChecked(position,true);
    }
}
