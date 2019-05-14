package com.example.adoption;

import java.util.ArrayList;
import java.util.List;

public class AdoptData {

    public static List<Bunnies> myBunnyList = new ArrayList<Bunnies>(){
        {
            add(new Bunnies("Weston",
                    "Weston was a big rockstar in the 90’s, but has since settled down and lived a quite life of digging holes and chewing shoes.",
                    "bunny1",
                    5));
            add(new Bunnies("Gizmo",
                    "So adorable that this dog won the 2014 award for \"Most Likely To Break Your Heart And Pee A Little While Doing It\"",
                    "bunny2",
                    11));
            add(new Bunnies("MogWai",
                    "This dog basically is an artist at heart, he knows how to paint with his tail, but often just craps on the floor",
                    "bunny3",
                    2));
            add(new Bunnies("Pepper",
                    "She is a little skeptical of cats, Rubik’s cubes, and people using cameras",
                    "bunny4",
                    7));
        }
    };

    public static List<Cats> myCatList = new ArrayList<Cats>(){
        {
            add(new Cats("Barry & Levon","Barry and Levon are quite a pair, they love pudding!","cat1",20));
            add(new Cats("Flash Gordon","We think this cat is sleeping, but its also possible he’s stuck. ","cat2",12));
            add(new Cats("Kif","This cat is very nice and very judgemental. (note: box not included)","cat3",8));
            add(new Cats("Mochi","This cat needs a very special diet of only the finest minced tuna and caviar.","cat4",11));
        }
    };
}

