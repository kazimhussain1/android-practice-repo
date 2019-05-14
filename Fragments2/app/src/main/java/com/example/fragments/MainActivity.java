package com.example.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements HeadLinesFragment.OnLineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.container) != null){
            if (savedInstanceState != null){
                return;
            }

            HeadLinesFragment myFragment = new HeadLinesFragment();

            myFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, myFragment)
                    .commit();
        }

    }

    @Override
    public void onHeadlineSelected(int position) {

        ArticleFragment myArticleFragment = (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.articlesFragment);

        if (myArticleFragment !=null) {

            myArticleFragment.updateArticleView(position);

        }else{
            myArticleFragment = new ArticleFragment();

            Bundle myBundle = new Bundle();
            myBundle.putInt(ArticleFragment.POSITION_ARG, position);

            myArticleFragment.setArguments(myBundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,myArticleFragment)
                    .addToBackStack(null)
                    .commit();

        }
    }
}
