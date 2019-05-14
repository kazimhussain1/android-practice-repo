package com.example.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArticleFragment extends Fragment {

    public static final String POSITION_ARG = "position";
    private int currentPosition = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null)
        {
            currentPosition = savedInstanceState.getInt(POSITION_ARG);

        }

        View fragmentView = inflater.inflate(R.layout.article_fragment, container, false);
        return fragmentView;

    }

    public void updateArticleView(int position){

        View myView = getView();
        TextView myArticleText = (TextView) myView.findViewById(R.id.article);

        myArticleText.setText(Ipsum.Articles[position]);
        currentPosition = position;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle myBundle = getArguments();

        if(myBundle != null) {
            int position = myBundle.getInt(POSITION_ARG);

            updateArticleView(position);

        }else if(currentPosition != -1){
            updateArticleView(currentPosition);
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(POSITION_ARG, currentPosition);
    }
}
