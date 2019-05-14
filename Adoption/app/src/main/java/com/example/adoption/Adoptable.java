package com.example.adoption;


import java.io.Serializable;

public interface Adoptable {

    public boolean isAvailable = false;

    public int getRating();
    public String getNumber();

}
