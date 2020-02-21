package HelperClasses;

public class Coordinate {
    public int x;
    public int y;
    public int totalSteps;

    public Coordinate(int x, int y, int totalSteps){
        this.x=x;
        this.y=y;
        this.totalSteps = totalSteps;
    }

    public Coordinate(int y, int x){
        this.x=x;
        this.y=y;
    }

    public boolean equals(Coordinate c){
        if(this.x==c.x&&this.y==c.y){
            return true;
        }
        return false;
    }
}
