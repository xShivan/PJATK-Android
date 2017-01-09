package net.xshivan.excercise4;

import android.location.Location;

public class Coordinates {
    public double latitude;

    public double longtitude;

    public String name;

    public String description;

    public Coordinates() { }

    public Coordinates(Location location, String name, String description) {
        this.name = name;
        this.description = description;

        this.latitude = location.getLatitude();
        this.longtitude = location.getLongitude();
    }
}
