package com.example.adoption;

import java.io.Serializable;

public class Bunnies extends Animals implements Adoptable, Serializable {

    public static final int MAX_SHELTER_DAYS = 30;


    private int daysAtShelter = 1;
    private String phoneNumber = "";

    public Bunnies(String aName, String aDescription, String anImageName, int aDaysAtShelter) {
        super(aName, aDescription, anImageName);

        this.daysAtShelter = aDaysAtShelter;
    }

    @Override
    public int getRating() {
        int rating = 5 * daysAtShelter / MAX_SHELTER_DAYS;
        return rating;
    }

    @Override
    public String getNumber() {
        //All bunny adoptions go through one number
        return "+1-202-555-0165";
    }

}
