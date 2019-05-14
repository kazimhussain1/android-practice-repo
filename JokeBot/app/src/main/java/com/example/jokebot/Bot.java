package com.example.jokebot;

import android.util.Log;

public class Bot {
    protected String name = "Robot";

    protected static final String creatorName = "Kazim Hussain";

    public Bot() {

    }

    public Bot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void talk(String message)
    {
        Log.e(this.name, message);
    }
}
