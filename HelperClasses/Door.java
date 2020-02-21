package HelperClasses;

public class Door extends Item{
    public Coordinate coordinate;
    Key key;
    public String name;

    public Door(Coordinate coordinate, Key key, String name) {
        this.coordinate = coordinate;
        this.key = key;
        this.name = name;
    }
}
