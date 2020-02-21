package HelperClasses;

import static java.lang.Math.abs;

public class Coordinate12 {
    public int x;
    public int y;
    public int z;

    public int dx=0;
    public int dy=0;
    public int dz=0;


    public Coordinate12(int x, int y, int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public void move(){
        this.x+=dx;
        this.y+=dy;
        this.z+=dz;
    }

    public int calculateTotalEnergy() {
        return calculatePotentialEnergy()*calculateKineticEnergy();
    }

    private int calculateKineticEnergy() {
        return abs(dx)+abs(dy)+abs(dz);
    }

    private int calculatePotentialEnergy() {
        return abs(x)+abs(y)+abs(z);
    }
}
