package HelperClasses;

public class Packet {

    public long adress;
    public long y;
    public long x;

    public Packet(long x, long y, long adress) {
        this.adress=adress;
        this.x=x;
        this.y=y;
    }
}
