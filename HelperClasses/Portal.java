package HelperClasses;

import java.util.HashMap;

public class Portal {
    public Portal teleport;
    public Coordinate15 coordinate;
    public String name;
    public HashMap<Portal,Integer> destinations = new HashMap<Portal,Integer>();
    public int smallestDistance = Integer.MAX_VALUE;
    public Portal predecessor;
    public boolean isInside;
    public int level;

    public Portal(Coordinate15 coordinate, String name, boolean isInside) {
        this.coordinate = coordinate;
        this.name = name;
        this.isInside = isInside;
    }

    public Portal(Coordinate15 coordinate, String name, boolean isInside, int level) {
        this.coordinate = coordinate;
        this.name = name;
        this.isInside = isInside;
        this.level=level;
    }

    public int getDistance(){
        return this.smallestDistance;
    }
}
