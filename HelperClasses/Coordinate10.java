package HelperClasses;

public class Coordinate10 {
    public int x;
    public int y;
    public double angle;

    public Coordinate10(int y, int x){
        this.x=x;
        this.y=y;
        this.angle = calculateAngle();
    }

    double calculateAngle(){
        double angle = Math.atan2((double)this.x,(double)(this.y*-1));
        if(angle<0) angle=angle+10;
        return angle;
    }

    public boolean equals(Coordinate c){
        if(this.x==c.x&&this.y==c.y){
            return true;
        }
        return false;
    }
}
