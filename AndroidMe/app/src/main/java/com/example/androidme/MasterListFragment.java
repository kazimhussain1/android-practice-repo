package com.example.androidme;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

public class MasterListFragment extends Fragment {

    private int myImageIndex;
    private List<Integer> myImageIds;
    public static final String IMAGE_ID_LIST = "090078601";
    public static final String IMAGE_INDEX = "YOLO";

    OnItemClickedListener myCallBack;

    public interface OnItemClickedListener{
        void OnItemClicked(int Position);
    }

    public MasterListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View myGridView = inflater.inflate(R.layout.fragment_master_list,container,false);
        MasterListAdapter myListAdapter = new MasterListAdapter(getActivity(),AndroidImageAssets.getAll());

        ((GridView)myGridView).setAdapter(myListAdapter);

        ((GridView) myGridView).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myCallBack.OnItemClicked(position);
            }
        });


        return myGridView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            myCallBack = (OnItemClickedListener) context;
        }catch (ClassCastException e){
            Log.e("MasterListFragment:51","Implement OnItemClickedListener in host Activity");
        }

    }



    public void setMyImageIndex(int myImageIndex) {
        this.myImageIndex = myImageIndex;
    }

    public void setMyImageIds(List<Integer> myImageIds) {
        this.myImageIds = myImageIds;
    }
}
