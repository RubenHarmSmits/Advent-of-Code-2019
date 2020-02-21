package HelperClasses;

public class Coordinate15 {
    public int x;
    public int y;
    public Coordinate15(int y, int x){
        this.y=y;
        this.x=x;
    }

    public boolean isEqual(Coordinate15 c){
        if(c.x==this.x&&c.y==this.y) return true;
        return false;
    }

    public void print() {
        System.out.println("y="+y+" x="+x);
    }
}
