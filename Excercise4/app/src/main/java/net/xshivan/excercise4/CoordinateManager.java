package net.xshivan.excercise4;

import java.util.ArrayList;

public class CoordinateManager {
    private static ArrayList<Coordinates> coordinates = new ArrayList<>();

    public static void Store(Coordinates coords) {
        coordinates.add(coords);

        
    }
}
