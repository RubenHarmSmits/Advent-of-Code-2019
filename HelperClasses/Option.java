package HelperClasses;

import java.util.ArrayList;

public class Option {
    public Key destination;
    public int distance;
    public ArrayList<Key> requirements;
    public ArrayList<Key> keysInbetween;

    public Option(Key destination, int distance, ArrayList<Key> requirements, ArrayList<Key> keysInbetween) {
        this.destination = destination;
        this.distance = distance;
        this.requirements = requirements;
        this.keysInbetween = keysInbetween;
    }
}
