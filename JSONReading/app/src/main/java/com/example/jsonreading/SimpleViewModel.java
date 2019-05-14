package com.example.jsonreading;

import android.support.annotation.NonNull;

public class SimpleViewModel {


    private String simpleText;

    public SimpleViewModel( final String simpleText) {

        setSimpleText(simpleText);
    }


    public String getSimpleText() {

        return simpleText;
    }

    public void setSimpleText( final String simpleText) {

        this.simpleText = simpleText;
    }
}
