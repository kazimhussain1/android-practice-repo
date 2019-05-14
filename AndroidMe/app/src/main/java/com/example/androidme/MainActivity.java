package com.example.androidme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnItemClickedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (savedInstanceState == null && findViewById(R.id.containerLandscape) == null) {

            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setMyImageIds(AndroidImageAssets.getHeads());
            headFragment.setMyImageIndex(0);
            BodyPartFragment torsoFragment = new BodyPartFragment();
            torsoFragment.setMyImageIds(AndroidImageAssets.getBodies());
            torsoFragment.setMyImageIndex(0);
            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setMyImageIds(AndroidImageAssets.getLegs());
            legFragment.setMyImageIndex(0);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.headContainer, headFragment)
                    .add(R.id.torsoContainer, torsoFragment)
                    .add(R.id.legsContainer, legFragment)
                    .commit();
        }
        else {
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setMyImageIds(AndroidImageAssets.getHeads());
            headFragment.setMyImageIndex(0);
            BodyPartFragment torsoFragment = new BodyPartFragment();
            torsoFragment.setMyImageIds(AndroidImageAssets.getBodies());
            torsoFragment.setMyImageIndex(0);
            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setMyImageIds(AndroidImageAssets.getLegs());
            legFragment.setMyImageIndex(0);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.headContainer, headFragment)
                    .add(R.id.torsoContainer, torsoFragment)
                    .add(R.id.legsContainer, legFragment)
                    .commit();
        }
    }

    @Override
    public void OnItemClicked(int Position) {

        int bodyPartNumber = Position/12;
        int listIndex = Position % 12;

        switch (bodyPartNumber){
            case 0:
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setMyImageIds(AndroidImageAssets.getHeads());
                headFragment.setMyImageIndex(listIndex);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.headContainer, headFragment)
                        .commit();
                break;
            case 1:
                BodyPartFragment torsoFragment = new BodyPartFragment();
                torsoFragment.setMyImageIds(AndroidImageAssets.getBodies());
                torsoFragment.setMyImageIndex(listIndex);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.torsoContainer, torsoFragment)
                        .commit();
                break;
            case 2:
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setMyImageIds(AndroidImageAssets.getLegs());
                legFragment.setMyImageIndex(listIndex);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.legsContainer, legFragment)
                        .commit();
        }

    }
}
