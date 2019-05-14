package com.example.adoption;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Field;

public abstract class Animals implements Serializable {

    protected String name = "";
    protected String ownerDescription = "";
    protected String image = "";

    public Animals(String name, String ownerDescription, String image) {
        this.name = name;
        this.ownerDescription = ownerDescription;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getOwnerDescription() {
        return ownerDescription;
    }

    public String getImage() {
        return image;
    }

    public int getImageResourceId(Activity myActivity){
        int drawableId = 0;
        try {
            Class res = R.drawable.class;
            Field field = res.getField(this.image);
            drawableId = field.getInt(null);

//            Drawable drawable = getResources().getDrawable(getResources()
//                    .getIdentifier("d002_p00"+j, "drawable", getPackageName()));
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }
        return drawableId;
    }
}

