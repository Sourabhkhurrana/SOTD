package com.nougatstudio.sotd.adapters;

/**
 * Created by pradeep on 27/08/2017 AD.
 */

public class Carditem {

    private int drawableId;
    private String name;
    private String location;

    public Carditem(int drawableId, String name, String location) {
        this.drawableId = drawableId;
        this.name = name;
        this.location = location;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
}
