package com.example.jsonreading;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecyclerAdapter extends RecyclerView.Adapter {


    private SimpleViewModel[] data;

    public RecyclerAdapter(String[] data) {

        this.data = new SimpleViewModel[data.length];

        for (int i=0;i<data.length;i++){
            this.data[i] = new SimpleViewModel(data[i]);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View myView = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        return new SimpleViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((SimpleViewHolder)viewHolder).bindData(data[i]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.row;
    }
}
